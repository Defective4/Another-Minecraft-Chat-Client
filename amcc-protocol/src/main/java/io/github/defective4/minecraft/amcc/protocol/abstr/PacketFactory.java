package io.github.defective4.minecraft.amcc.protocol.abstr;

import java.io.DataInputStream;
import java.io.IOException;

import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;

public interface PacketFactory<T extends ClientboundPacket> {
    T decode(DataInputStream input) throws IOException;
}
