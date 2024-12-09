package io.github.defective4.minecraft.amcc.protocol.abstr;

public class ProtocolSet {

    private final ProtocolExecutor clientPacketFactory;
    private final PacketRegistry packetRegistry;
    private final int versionNumber;

    protected ProtocolSet(int versionNumber, ProtocolExecutor clientPacketFactory, PacketRegistry packetRegistry) {
        this.packetRegistry = packetRegistry;
        this.versionNumber = versionNumber;
        this.clientPacketFactory = clientPacketFactory;
    }

    public ProtocolExecutor getExecutor() {
        return clientPacketFactory;
    }

    public PacketRegistry getPacketRegistry() {
        return packetRegistry;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

}
