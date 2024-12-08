package io.github.defective4.minecraft.amcc.protocol;

import static io.github.defective4.minecraft.amcc.protocol.data.DataTypes.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import io.github.defective4.minecraft.amcc.protocol.data.LegacyStatusResponse;
import io.github.defective4.minecraft.amcc.protocol.data.StatusResponse;
import io.github.defective4.minecraft.amcc.protocol.packets.HandshakePacket;

public class MinecraftStat {

    public static StatusResponse autoServerListPing(String host, int port) throws UnknownHostException, IOException {
        try {
            return modernServerListPing(host, port);
        } catch (IOException e) {
            return legacyServerListPing(host, port);
        }
    }

    public static StatusResponse legacyServerListPing(String host, int port) throws UnknownHostException, IOException {
        try (Socket socket = new Socket(host, port)) {
            OutputStream os = socket.getOutputStream();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            os.write(new byte[] {
                    (byte) 0xfe, 0x01
            });
            int id = in.read();
            if (id != 0xff) throw new IOException("Invalid legacy status response ID: 0x" + Integer.toHexString(id));
            int len = in.readShort();
            if (len <= 0) throw new IOException("Invalid legacy status response length: " + len);

            StringBuilder statusBuilder = new StringBuilder();
            try (Reader reader = new InputStreamReader(in, StandardCharsets.UTF_16BE)) {
                reader.read(new char[3]);
                while (true) {
                    int read = reader.read();
                    if (read == -1) break;
                    char c = (char) read;
                    statusBuilder.append(c);
                }
            }
            String[] args = statusBuilder.toString().split("\0");
            try {
                int protocol = Integer.parseInt(args[0]);
                String version = args[1];
                String desc = args[2];
                int online = Integer.parseInt(args[3]);
                int max = Integer.parseInt(args[4]);
                return new LegacyStatusResponse(protocol, version, desc, online, max);
            } catch (Exception e) {
                throw new IOException("Invalid legacy status response received");
            }
        }
    }

    public static StatusResponse modernServerListPing(String host, int port) throws UnknownHostException, IOException {
        try (Socket socket = new Socket(host, port)) {
            OutputStream os = socket.getOutputStream();
            DataInputStream is = new DataInputStream(socket.getInputStream());
            byte[] hsData = new HandshakePacket(767, host, port, 1).getData();
            os.write(hsData);
            os.write(1);
            os.write(0);

            int len = readVarInt(is);
            if (len <= 1) throw new IOException("Invalid status response length: " + len);

            int id = is.read();
            if (id != 0) throw new IOException("Invalid status response ID: 0x" + Integer.toHexString(id));

            String json = readVarString(is);
            return StatusResponse.fromJson(json);
        }
    }
}
