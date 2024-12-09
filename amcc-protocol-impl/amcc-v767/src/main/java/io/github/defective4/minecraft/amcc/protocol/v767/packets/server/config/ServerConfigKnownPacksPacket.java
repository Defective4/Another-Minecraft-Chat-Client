package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config;

import static io.github.defective4.minecraft.amcc.protocol.data.DataTypes.readVarString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.data.DataPack;

public class ServerConfigKnownPacksPacket extends ClientboundPacket {

    public static final PacketFactory<ServerConfigKnownPacksPacket> FACTORY = in -> {
        int len = DataTypes.readVarInt(in);
        List<DataPack> packs = new ArrayList<>();
        for (int i = 0; i < len; i++) packs.add(new DataPack(readVarString(in), readVarString(in), readVarString(in)));
        return new ServerConfigKnownPacksPacket(packs);
    };

    private final List<DataPack> datapacks;

    protected ServerConfigKnownPacksPacket(List<DataPack> datapacks) {
        this.datapacks = Collections.unmodifiableList(datapacks);
    }

    public List<DataPack> getDatapacks() {
        return datapacks;
    }

}
