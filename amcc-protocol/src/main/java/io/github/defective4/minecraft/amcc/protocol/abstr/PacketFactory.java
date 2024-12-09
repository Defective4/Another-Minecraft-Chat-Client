package io.github.defective4.minecraft.amcc.protocol.abstr;

import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;

public interface PacketFactory<T extends ClientboundPacket> {
    T decode(byte[] data);
}
