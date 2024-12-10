package io.github.defective4.minecraft.amcc.protocol.v767;

import java.io.IOException;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.abstr.PacketHandler;
import io.github.defective4.minecraft.amcc.protocol.abstr.PacketReceiver;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.event.network.CompressionThresholdChangeEvent;
import io.github.defective4.minecraft.amcc.protocol.event.network.KeepAliveReceivedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.ConfigurationFinishEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.GameJoinedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.KickEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.LoginSuccessEvent;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.config.ClientConfigKnownPacksPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config.ServerConfigFinishPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config.ServerConfigKnownPacksPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginCompressionPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginDisconnectPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginSuccessPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play.ServerGameJoinPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play.ServerKeepAlivePacket;

@SuppressWarnings("unused")
public class V767PacketReceiver extends PacketReceiver {

    @PacketHandler
    public void onConfigFinish(ServerConfigFinishPacket e, MinecraftClient client) {
        client.dispatchEvent(new ConfigurationFinishEvent());
    }

    @PacketHandler
    public void onConfigPacks(ServerConfigKnownPacksPacket e, MinecraftClient client) throws IOException {
        client.sendPacket(new ClientConfigKnownPacksPacket(e.getDatapacks()));
    }

    @PacketHandler
    public void onGameJoin(ServerGameJoinPacket e, MinecraftClient client) {
        client
                .dispatchEvent(new GameJoinedEvent(e.getEntityID(), e.isHardcore(), e.getDimensions(),
                        e.getViewDistance(), e.getSimDistance(), e.isReducedDebugInfo(), e.getDimension(),
                        e.getHashedSeed(), e.getGameMode(), e.getPreviousGameMode(), e.isEnforcesSecureChat()));
    }

    @PacketHandler
    public void onKeepAliveReceived(ServerKeepAlivePacket e, MinecraftClient client) {
        client.dispatchEvent(new KeepAliveReceivedEvent(e.getId()));
    }

    @PacketHandler
    public void onLoginCompressionSet(ServerLoginCompressionPacket e, MinecraftClient client) {
        client.dispatchEvent(new CompressionThresholdChangeEvent(e.getThreshold(), client.getCompressionThreshold()));
    }

    @PacketHandler
    public void onLoginDisconnect(ServerLoginDisconnectPacket e, MinecraftClient client) {
        client.dispatchEvent(new KickEvent(e.getMessage()));
    }

    @PacketHandler
    public void onLoginSuccess(ServerLoginSuccessPacket e, MinecraftClient client) {
        client.dispatchEvent(new LoginSuccessEvent(new PlayerProfile(e.getName(), e.getUuid())));
    }
}
