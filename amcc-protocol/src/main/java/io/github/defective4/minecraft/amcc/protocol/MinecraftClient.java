package io.github.defective4.minecraft.amcc.protocol;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.abstr.ProtocolExecutor;
import io.github.defective4.minecraft.amcc.protocol.abstr.ProtocolSet;
import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;
import io.github.defective4.minecraft.amcc.protocol.data.GameState;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEventListener;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.amcc.protocol.packets.HandshakePacket;
import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public class MinecraftClient implements AutoCloseable {
    private final PlayerProfile clientSideProfile;
    private int compressionThreshold = -1;
    private boolean connected;
    private GameState currentGameState = GameState.HANDSHAKE;
    private final ProtocolExecutor executor;

    private final String host;
    private DataInputStream in;
    private final Inflater inflater = new Inflater();
    private final List<ClientEventListener> listeners = new CopyOnWriteArrayList<>();
    private OutputStream out;
    private final int port;
    private final ProtocolSet protocol;
    private PlayerProfile serverSideProfile;
    private final Socket socket = new Socket();

    public MinecraftClient(String host, int port, PlayerProfile profile, ProtocolSet protocol) {
        listeners.add(new CoreEventHandler(this));
        this.host = host;
        this.port = port;
        clientSideProfile = profile;
        this.protocol = protocol;
        executor = protocol.getExecutor();
    }

    public void addListener(ClientEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }

    public void connect() throws IOException, DataFormatException {
        socket.connect(new InetSocketAddress(host, port));
        in = new DataInputStream(socket.getInputStream());
        out = socket.getOutputStream();
        connected = true;

        sendPacket(new HandshakePacket(protocol.getVersionNumber(), host, port, 2));
        currentGameState = GameState.LOGIN;
        sendPacket(executor.createLoginPacket(clientSideProfile));

        try {
            while (!socket.isClosed()) {
                int len = DataTypes.readVarInt(in);
                int id;
                byte[] data;
                if (compressionThreshold > -1) {
                    int dataLen = DataTypes.readVarInt(in);
                    if (dataLen == 0) {
                        id = in.read();
                        data = new byte[len - 2];
                        in.readFully(data);
                    } else {
                        byte[] uncompressed = new byte[dataLen];
                        data = new byte[len - DataTypes.getVarIntSize(dataLen)];
                        in.readFully(data);
                        inflater.reset();
                        inflater.setInput(data);
                        int read = inflater.inflate(uncompressed);
                        id = uncompressed[0];
                        data = Arrays.copyOfRange(uncompressed, 1, uncompressed.length);
                    }
                } else {
                    id = in.read();
                    data = new byte[len - 1];
                    in.readFully(data);
                }
                try (DataInputStream wrapper = new DataInputStream(new ByteArrayInputStream(data))) {
                    PacketFactory<?> factory = protocol.getPacketRegistry().getPacket(currentGameState, id);
                    if (factory != null) {
                        ClientboundPacket packet = factory.decode(wrapper);
                        protocol.getReceiver().receivePacket(packet, this);
                    }
                }
            }
        } catch (Exception e) {
            if (!isClosed()) {
                close();
                throw e;
            }
        }
    }

    public void dispatchEvent(ClientEvent event) {
        listeners.forEach(ls -> ls.dispatchEvent(event));
    }

    public PlayerProfile getClientProfile() {
        return clientSideProfile;
    }

    public int getCompressionThreshold() {
        return compressionThreshold;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public List<ClientEventListener> getListeners() {
        return Collections.unmodifiableList(listeners);
    }

    public PlayerProfile getServerProfile() {
        return serverSideProfile;
    }

    public PlayerProfile getServerSideProfile() {
        return serverSideProfile;
    }

    public boolean isClosed() {
        return socket.isClosed();
    }

    public boolean isConnected() {
        return connected;
    }

    public void removeListener(ClientEventListener listener) {
        listeners.remove(listener);
    }

    public void sendMessage(String message) throws IOException {
        protocol.getExecutor().sendChatMessage(this, message);
    }

    public void sendPacket(ServerboundPacket packet) throws IOException {
        if (!isConnected()) throw new IOException("Client not connected");
        out.write(packet.getData(compressionThreshold));
    }

    protected ProtocolExecutor getExecutor() {
        return executor;
    }

    protected void setCompressionThreshold(int compressionThreshold) {
        this.compressionThreshold = compressionThreshold;
    }

    protected void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    protected void setServerSideProfile(PlayerProfile serverSideProfile) {
        this.serverSideProfile = serverSideProfile;
    }
}
