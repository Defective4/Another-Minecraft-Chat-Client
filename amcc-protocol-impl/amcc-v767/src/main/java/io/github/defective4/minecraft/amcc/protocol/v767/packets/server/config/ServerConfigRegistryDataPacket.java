package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;
import io.github.defective4.minecraft.amcc.protocol.data.Identifier;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.chatlib.nbt.tag.NBTParser;
import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

public class ServerConfigRegistryDataPacket extends ClientboundPacket {

    public static final PacketFactory<ServerConfigRegistryDataPacket> FACTORY = in -> {
        Identifier id = Identifier.CODEC.read(in);
        int num = DataTypes.readVarInt(in);
        Map<Identifier, Tag> data = new LinkedHashMap<>();
        for (int i = 0; i < num; i++) {
            Identifier dID = Identifier.CODEC.read(in);
            Tag tag = in.readBoolean() ? NBTParser.parse(in, false) : null;
            data.put(dID, tag);
        }
        return new ServerConfigRegistryDataPacket(id, data);
    };

    private final Identifier id;
    private final Map<Identifier, Tag> data;

    protected ServerConfigRegistryDataPacket(Identifier id, Map<Identifier, Tag> data) {
        this.id = id;
        this.data = Collections.unmodifiableMap(data);
    }

    public Identifier getId() {
        return id;
    }

    public Map<Identifier, Tag> getData() {
        return data;
    }

}
