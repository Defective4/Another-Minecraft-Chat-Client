package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;

public class ServerLoginCompressionPacket extends ClientboundPacket {

    public static final PacketFactory<ServerLoginCompressionPacket> FACTORY = in -> new ServerLoginCompressionPacket(
            DataTypes.readVarInt(in));

    private final int threshold;

    protected ServerLoginCompressionPacket(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return threshold;
    }

}
