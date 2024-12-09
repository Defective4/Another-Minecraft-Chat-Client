package io.github.defective4.minecraft.amcc.protocol.v767;

import java.util.Map;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.abstr.PacketRegistry;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config.ServerConfigFinishPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config.ServerConfigKnownPacksPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginSuccessPacket;

public class V767PacketRegistry extends PacketRegistry {

    @Override
    protected void initConfigPackets(Map<Integer, PacketFactory<?>> map) {
        map.put(0x03, in -> new ServerConfigFinishPacket());
        map.put(0x0e, ServerConfigKnownPacksPacket.FACTORY);
    }

    @Override
    protected void initLoginPackets(Map<Integer, PacketFactory<?>> map) {
        map.put(0x02, ServerLoginSuccessPacket.FACTORY);
    }

    @Override
    protected void initPlayPackets(Map<Integer, PacketFactory<?>> map) {}

}
