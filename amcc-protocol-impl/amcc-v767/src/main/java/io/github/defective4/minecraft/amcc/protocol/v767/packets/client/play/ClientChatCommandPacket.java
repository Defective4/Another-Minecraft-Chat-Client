package io.github.defective4.minecraft.amcc.protocol.v767.packets.client.play;

import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public class ClientChatCommandPacket extends ServerboundPacket {
    public ClientChatCommandPacket(String command) {
        super(0x04);
        writeVarString(command);
    }
}
