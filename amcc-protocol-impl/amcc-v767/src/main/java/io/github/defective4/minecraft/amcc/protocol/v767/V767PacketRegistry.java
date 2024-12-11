package io.github.defective4.minecraft.amcc.protocol.v767;

import java.util.Map;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.abstr.PacketRegistry;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config.ServerConfigFinishPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config.ServerConfigKnownPacksPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginCompressionPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginDisconnectPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginSuccessPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play.ServerActionBarTextPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play.ServerDisguisedChatMessagePacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play.ServerGameJoinPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play.ServerKeepAlivePacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play.ServerPlayerChatMessagePacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play.ServerPlayerInfoUpdatePacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play.ServerSystemChatMessagePacket;

public class V767PacketRegistry extends PacketRegistry {

    @Override
    protected void initConfigPackets(Map<Integer, PacketFactory<?>> map) {
        map.put(0x03, in -> new ServerConfigFinishPacket());
        map.put(0x0e, ServerConfigKnownPacksPacket.FACTORY);
    }

    @Override
    protected void initLoginPackets(Map<Integer, PacketFactory<?>> map) {
        map.put(0x00, ServerLoginDisconnectPacket.FACTORY);
        map.put(0x02, ServerLoginSuccessPacket.FACTORY);
        map.put(0x03, ServerLoginCompressionPacket.FACTORY);
    }

    @Override
    protected void initPlayPackets(Map<Integer, PacketFactory<?>> map) {
        map.put(0x1E, ServerDisguisedChatMessagePacket.FACTORY);
        map.put(0x26, ServerKeepAlivePacket.FACTORY);
        map.put(0x2B, ServerGameJoinPacket.FACTORY);
        map.put(0x39, ServerPlayerChatMessagePacket.FACTORY);
        map.put(0x3E, ServerPlayerInfoUpdatePacket.FACTORY);
        map.put(0x4C, ServerActionBarTextPacket.FACTORY);
        map.put(0x6C, ServerSystemChatMessagePacket.FACTORY);
    }

}
