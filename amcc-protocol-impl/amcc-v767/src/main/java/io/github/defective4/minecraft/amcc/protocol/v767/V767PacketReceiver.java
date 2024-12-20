package io.github.defective4.minecraft.amcc.protocol.v767;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.abstr.PacketHandler;
import io.github.defective4.minecraft.amcc.protocol.abstr.PacketReceiver;
import io.github.defective4.minecraft.amcc.protocol.data.GameProfile;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerInfoAction;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerInfoItem;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.data.ProfileProperties;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;
import io.github.defective4.minecraft.amcc.protocol.event.game.ActionBarMessageEvent;
import io.github.defective4.minecraft.amcc.protocol.event.game.ChatMessageEvent;
import io.github.defective4.minecraft.amcc.protocol.event.game.ChatMessageEvent.Source;
import io.github.defective4.minecraft.amcc.protocol.event.game.PlayerListUpdatedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.game.RegistryDataReceivedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.game.TimeUpdatedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.network.CompressionThresholdChangeEvent;
import io.github.defective4.minecraft.amcc.protocol.event.network.KeepAliveReceivedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.network.PluginMessageReceivedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.ConfigurationFinishEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.ConfigurationRedoEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.GameJoinedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.KickEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.LoginSuccessEvent;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.config.ClientConfigKnownPacksPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config.ServerConfigDisconnectPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config.ServerConfigFinishPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config.ServerConfigKnownPacksPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.config.ServerConfigRegistryDataPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginCompressionPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginDisconnectPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginSuccessPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play.*;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;

@SuppressWarnings("unused")
public class V767PacketReceiver extends PacketReceiver {

    @PacketHandler
    public void onActionBarText(ServerActionBarTextPacket e, MinecraftClient client) {
        client.dispatchEvent(new ActionBarMessageEvent(e.getText(), client));
    }

    @PacketHandler
    public void onConfigDisconnect(ServerConfigDisconnectPacket e, MinecraftClient client) {
        client.dispatchEvent(new KickEvent(e.getMessage(), client));
    }

    @PacketHandler
    public void onConfigFinish(ServerConfigFinishPacket e, MinecraftClient client) {
        client.dispatchEvent(new ConfigurationFinishEvent(client));
    }

    @PacketHandler
    public void onConfigPacks(ServerConfigKnownPacksPacket e, MinecraftClient client) throws IOException {
        client.sendPacket(new ClientConfigKnownPacksPacket(e.getDatapacks()));
    }

    @PacketHandler
    public void onConfigRegistryData(ServerConfigRegistryDataPacket e, MinecraftClient client) {
        client.dispatchEvent(new RegistryDataReceivedEvent(e.getId(), e.getData(), client));
    }

    @PacketHandler
    public void onConfigurationRedo(ServerRedoConfigurationPacket e, MinecraftClient client) {
        client.dispatchEvent(new ConfigurationRedoEvent(client));
    }

    @PacketHandler
    public void onDisguisedMessageReceived(ServerDisguisedChatMessagePacket e, MinecraftClient client) {
        client
                .dispatchEvent(new ChatMessageEvent(e.getMessage(), Source.OTHER, null, e.getSenderName(),
                        e.getTargetName(), e.getChatType(), client));
    }

    @PacketHandler
    public void onGameJoin(ServerGameJoinPacket e, MinecraftClient client) {
        client
                .dispatchEvent(new GameJoinedEvent(e.getEntityID(), e.isHardcore(), e.getDimensions(),
                        e.getViewDistance(), e.getSimDistance(), e.isReducedDebugInfo(), e.getDimension(),
                        e.getHashedSeed(), e.getGameMode(), e.getPreviousGameMode(), e.isEnforcesSecureChat(), client));
    }

    @PacketHandler
    public void onKeepAliveReceived(ServerKeepAlivePacket e, MinecraftClient client) {
        client.dispatchEvent(new KeepAliveReceivedEvent(e.getId(), client));
    }

    @PacketHandler
    public void onLoginCompressionSet(ServerLoginCompressionPacket e, MinecraftClient client) {
        client
                .dispatchEvent(new CompressionThresholdChangeEvent(e.getThreshold(), client.getCompressionThreshold(),
                        client));
    }

    @PacketHandler
    public void onLoginDisconnect(ServerLoginDisconnectPacket e, MinecraftClient client) {
        client.dispatchEvent(new KickEvent(e.getMessage(), client));
    }

    @PacketHandler
    public void onLoginSuccess(ServerLoginSuccessPacket e, MinecraftClient client) {
        client.dispatchEvent(new LoginSuccessEvent(new PlayerProfile(e.getName(), e.getUuid()), client));
    }

    @PacketHandler
    public void onPlayDisconnect(ServerDisconnectPacket e, MinecraftClient client) {
        client.dispatchEvent(new KickEvent(e.getMessage(), client));
    }

    @PacketHandler
    public void onPlayerInfoUpdate(ServerPlayerInfoUpdatePacket e, MinecraftClient client) {
        client.dispatchEvent(new PlayerListUpdatedEvent(e.getActions(), e.getItems(), client));
    }

    @PacketHandler
    public void onPlayerItemRemove(ServerPlayerInfoRemovePacket e, MinecraftClient client) {
        List<PlayerInfoAction> actions = Collections.singletonList(PlayerInfoAction.REMOVE_PLAYER);
        List<PlayerInfoItem> items = e
                .getIds()
                .stream()
                .map(u -> new PlayerInfoItem(u, new GameProfile(null, u, new ProfileProperties()), null, false, 0,
                        null))
                .collect(Collectors.toList());
        client.dispatchEvent(new PlayerListUpdatedEvent(actions, items, client));
    }

    @PacketHandler
    public void onPlayerMessage(ServerPlayerChatMessagePacket e, MinecraftClient client) {
        client
                .dispatchEvent(new ChatMessageEvent(new ChatComponent(e.getMessage()), e.getSender(), e.getSenderName(),
                        e.getTargetName(), e.getChatType(), client));
    }

    @PacketHandler
    public void onPluginMessage(ServerPluginMessagePacket e, MinecraftClient client) {
        client.dispatchEvent(new PluginMessageReceivedEvent(client, e.getChannel(), e.getData()));
    }

    @PacketHandler
    public void onSystemChatMessage(ServerSystemChatMessagePacket e, MinecraftClient client) {
        ClientEvent event;
        if (e.isActionBar()) {
            event = new ActionBarMessageEvent(e.getMessage(), client);
        } else {
            event = new ChatMessageEvent(e.getMessage(), client);
        }
        client.dispatchEvent(event);
    }

    @PacketHandler
    public void onTimePacket(ServerTimeUpdatePacket e, MinecraftClient client) {
        client.dispatchEvent(new TimeUpdatedEvent(client, e.getWorldAge(), e.getTime()));
    }
}
