package io.github.defective4.minecraft.amcc.protocol.abstr;

public class ProtocolSet {

    private final ProtocolExecutor clientPacketFactory;
    private final int versionNumber;

    protected ProtocolSet(int versionNumber, ProtocolExecutor clientPacketFactory) {
        this.versionNumber = versionNumber;
        this.clientPacketFactory = clientPacketFactory;
    }

    public ProtocolExecutor getExecutor() {
        return clientPacketFactory;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

}
