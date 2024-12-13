package io.github.defective4.minecraft.amcc.protocol.v767.packets.client.config;

import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public class ClientConfigPluginMessagePacket extends ServerboundPacket {

    public ClientConfigPluginMessagePacket(String channel, byte[] data) {
        super(0x02);
        writeVarString(channel);
        write(data);
    }

}
