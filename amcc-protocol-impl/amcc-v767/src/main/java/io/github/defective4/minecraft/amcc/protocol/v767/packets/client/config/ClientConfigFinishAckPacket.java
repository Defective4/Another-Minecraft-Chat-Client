package io.github.defective4.minecraft.amcc.protocol.v767.packets.client.config;

import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public class ClientConfigFinishAckPacket extends ServerboundPacket {

    public ClientConfigFinishAckPacket() {
        super(0x03);
    }

}
