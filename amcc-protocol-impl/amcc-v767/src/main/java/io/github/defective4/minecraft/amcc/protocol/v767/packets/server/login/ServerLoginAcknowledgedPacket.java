package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login;

import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public class ServerLoginAcknowledgedPacket extends ServerboundPacket {

    public ServerLoginAcknowledgedPacket() {
        super(0x03);
    }

}
