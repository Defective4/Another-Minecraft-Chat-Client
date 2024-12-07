package io.github.defective4.minecraft.amcc.protocol.packets;

public class HandshakePacket extends ServerboundPacket {
    public HandshakePacket(int protocol, String host, int port, int state) {
        super(0x00);
        writeVarInt(protocol);
        writeVarString(host);
        writeShort(port);
        writeVarInt(state);
    }
}
