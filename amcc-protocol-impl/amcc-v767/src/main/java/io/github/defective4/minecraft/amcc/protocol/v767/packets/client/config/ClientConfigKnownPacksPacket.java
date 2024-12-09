package io.github.defective4.minecraft.amcc.protocol.v767.packets.client.config;

import java.util.List;

import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.data.DataPack;

public class ClientConfigKnownPacksPacket extends ServerboundPacket {

    public ClientConfigKnownPacksPacket(List<DataPack> packs) {
        super(0x07);
        writeVarInt(packs.size());
        for (DataPack pack : packs) {
            writeVarString(pack.getNamespace());
            writeVarString(pack.getId());
            writeVarString(pack.getVersion());
        }
    }

}
