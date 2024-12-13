package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;

public class ServerPluginMessagePacket extends ClientboundPacket {

    public static final PacketFactory<ServerPluginMessagePacket> FACTORY = in -> {
        String channel = DataTypes.readVarString(in);
        byte[] data = new byte[in.available()];
        in.readFully(data);
        return new ServerPluginMessagePacket(channel, data);
    };

    private final String channel;
    private final byte[] data;

    public ServerPluginMessagePacket(String channel, byte[] data) {
        this.channel = channel;
        this.data = data;
    }

    public String getChannel() {
        return channel;
    }

    public byte[] getData() {
        return data;
    }
}
