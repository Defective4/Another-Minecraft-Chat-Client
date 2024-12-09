package io.github.defective4.minecraft.amcc.protocol.abstr;

public class ProtocolSet {

    private final ProtocolExecutor clientPacketFactory;
    private final PacketRegistry packetRegistry;
    private final PacketReceiver receiver;
    private final int versionNumber;

    protected ProtocolSet(int versionNumber, ProtocolExecutor clientPacketFactory, PacketRegistry packetRegistry,
            PacketReceiver receiver) {
        this.packetRegistry = packetRegistry;
        this.versionNumber = versionNumber;
        this.clientPacketFactory = clientPacketFactory;
        this.receiver = receiver;
    }

    public ProtocolExecutor getExecutor() {
        return clientPacketFactory;
    }

    public PacketRegistry getPacketRegistry() {
        return packetRegistry;
    }

    public PacketReceiver getReceiver() {
        return receiver;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

}
