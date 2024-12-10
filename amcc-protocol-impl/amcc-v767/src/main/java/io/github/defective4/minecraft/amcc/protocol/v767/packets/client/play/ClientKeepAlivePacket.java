package io.github.defective4.minecraft.amcc.protocol.v767.packets.client.play;

import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public class ClientKeepAlivePacket extends ServerboundPacket {
    public ClientKeepAlivePacket(long id) {
        super(0x18);
        writeLong(id);
    }
}
