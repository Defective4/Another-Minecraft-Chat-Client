package io.github.defective4.minecraft.amcc.protocol.v767.packets.client.play;

import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public class ClientPluginMessagePacket extends ServerboundPacket {

    public ClientPluginMessagePacket(String channel, byte[] data) {
        super(0x12);
        writeVarString(channel);
        write(data);
    }

}
