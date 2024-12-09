package io.github.defective4.minecraft.amcc.protocol.v767.packets.client.login;

import java.util.UUID;

import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public class ClientLoginStartPacket extends ServerboundPacket {
    public ClientLoginStartPacket(String name, UUID uuid) {
        super(0x00);
        writeVarString(name);
        writeUUID(uuid);
    }
}
