package io.github.defective4.minecraft.amcc.protocol;

import static io.github.defective4.minecraft.amcc.protocol.data.DataTypes.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import io.github.defective4.minecraft.amcc.protocol.data.StatusResponse;
import io.github.defective4.minecraft.amcc.protocol.packets.HandshakePacket;

public class MinecraftStat {
    public static StatusResponse serverListPing(String host, int port) throws IOException {
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
