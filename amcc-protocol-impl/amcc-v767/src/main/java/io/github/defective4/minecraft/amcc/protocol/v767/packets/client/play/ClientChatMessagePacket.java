package io.github.defective4.minecraft.amcc.protocol.v767.packets.client.play;

import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public class ClientChatMessagePacket extends ServerboundPacket {
    public ClientChatMessagePacket(String message) {
        super(0x06);
        writeVarString(message);
        writeLong(System.currentTimeMillis());
        writeLong(0);
        writeBoolean(false);
        writeByte(0);
        write(new byte[3]);
    }
}
