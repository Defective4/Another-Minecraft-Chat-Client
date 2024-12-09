package io.github.defective4.minecraft.amcc.protocol;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.abstr.ProtocolExecutor;
import io.github.defective4.minecraft.amcc.protocol.abstr.ProtocolSet;
import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;
import io.github.defective4.minecraft.amcc.protocol.data.GameState;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.amcc.protocol.packets.HandshakePacket;
import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public class MinecraftClient implements AutoCloseable {
    private final PlayerProfile clientSideProfile;
    private boolean connected;
    private GameState currentGameState = GameState.HANDSHAKE;
    private final ProtocolExecutor executor;
    private final String host;

    private DataInputStream in;
    private OutputStream out;
    private final int port;
    private final ProtocolSet protocol;
    private PlayerProfile serverSideProfile;
    private final Socket socket = new Socket();

    public MinecraftClient(String host, int port, PlayerProfile profile, ProtocolSet protocol) {
        this.host = host;
        this.port = port;
        clientSideProfile = profile;
        this.protocol = protocol;
        executor = protocol.getExecutor();
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }

    public void connect() throws IOException {
        socket.connect(new InetSocketAddress(host, port));
        in = new DataInputStream(socket.getInputStream());
        out = socket.getOutputStream();
        connected = true;

        sendPacket(new HandshakePacket(protocol.getVersionNumber(), host, port, 2));
        currentGameState = GameState.LOGIN;
        sendPacket(executor.createLoginPacket(clientSideProfile));

        while (!socket.isClosed()) {
            int len = DataTypes.readVarInt(in);
            int id = in.readByte();
            byte[] data = new byte[len - 1];
            in.readFully(data);
            try (DataInputStream wrapper = new DataInputStream(new ByteArrayInputStream(data))) {
                PacketFactory<?> factory = protocol.getPacketRegistry().getPacket(currentGameState, id);
                if (factory != null) {
                    ClientboundPacket packet = factory.decode(wrapper);
                    protocol.getReceiver().receivePacket(packet, this);
                }
            }
        }
    }

    public PlayerProfile getClientProfile() {
        return clientSideProfile;
    }

    public PlayerProfile getServerProfile() {
        return serverSideProfile;
    }

    public boolean isClosed() {
        return socket.isClosed();
    }

    public boolean isConnected() {
        return connected;
    }

    public void sendPacket(ServerboundPacket packet) throws IOException {
        if (!isConnected()) throw new IOException("Client not connected");
        out.write(packet.getData());
    }
}
