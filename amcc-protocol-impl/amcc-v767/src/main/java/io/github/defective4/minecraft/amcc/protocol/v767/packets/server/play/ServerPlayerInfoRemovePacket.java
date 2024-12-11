package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;

public class ServerPlayerInfoRemovePacket extends ClientboundPacket {

    public static final PacketFactory<ServerPlayerInfoRemovePacket> FACTORY = in -> {
        List<UUID> ids = new ArrayList<>();
        int num = DataTypes.readVarInt(in);
        for (int i = 0; i < num; i++) ids.add(DataTypes.readUUID(in));
        return new ServerPlayerInfoRemovePacket(ids);
    };

    private final List<UUID> ids;

    protected ServerPlayerInfoRemovePacket(List<UUID> ids) {
        this.ids = Collections.unmodifiableList(ids);
    }

    public List<UUID> getIds() {
        return ids;
    }

}
