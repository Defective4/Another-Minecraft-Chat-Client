package io.github.defective4.minecraft.amcc.protocol.v767.packets.client.login;

import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public class ClientLoginAcknowledgedPacket extends ServerboundPacket {

    public ClientLoginAcknowledgedPacket() {
        super(0x03);
    }

}
