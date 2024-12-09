package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login;

import java.util.UUID;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;

public class ServerLoginSuccessPacket extends ClientboundPacket {

    public static final PacketFactory<ClientboundPacket> FACTORY = in -> new ServerLoginSuccessPacket(
            DataTypes.readUUID(in), DataTypes.readVarString(in));

    private final String name;
    private final UUID uuid;

    protected ServerLoginSuccessPacket(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

}
