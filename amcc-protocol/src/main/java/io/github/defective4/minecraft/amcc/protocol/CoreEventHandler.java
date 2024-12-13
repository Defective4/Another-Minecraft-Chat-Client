package io.github.defective4.minecraft.amcc.protocol;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map.Entry;

import io.github.defective4.minecraft.amcc.protocol.data.GameMode;
import io.github.defective4.minecraft.amcc.protocol.data.GameProfile;
import io.github.defective4.minecraft.amcc.protocol.data.GameState;
import io.github.defective4.minecraft.amcc.protocol.data.Identifier;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerInfoAction;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerInfoItem;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEventListener;
import io.github.defective4.minecraft.amcc.protocol.event.EventHandler;
import io.github.defective4.minecraft.amcc.protocol.event.game.PlayerListUpdatedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.game.RegistryDataReceivedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.network.CompressionThresholdChangeEvent;
import io.github.defective4.minecraft.amcc.protocol.event.network.KeepAliveReceivedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.network.PluginMessageReceivedEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.ConfigurationFinishEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.ConfigurationRedoEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.KickEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.LoginSuccessEvent;
import io.github.defective4.minecraft.amcc.protocol.registry.ChatType;
import io.github.defective4.minecraft.amcc.protocol.registry.Registries;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;
import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

@SuppressWarnings("unused")
public class CoreEventHandler implements ClientEventListener {

    private final MinecraftClient client;

    protected CoreEventHandler(MinecraftClient client) {
        this.client = client;
    }

    @EventHandler
    public void onCompressionThrChange(CompressionThresholdChangeEvent e) {
        client.setCompressionThreshold(e.getNewThreshold());
    }

    @EventHandler
    public void onConfigFinish(ConfigurationFinishEvent e) throws IOException {
        client.setCurrentGameState(GameState.PLAY);
        client.getExecutor().acknowledgeConfigFinish(client);
    }

    @EventHandler
    public void onConfigRedo(ConfigurationRedoEvent e) {
        client.setCurrentGameState(GameState.CONFIGURATION);
    }

    @EventHandler
    public void onDisconnect(KickEvent e) throws IOException {
        client.close();
    }

    @EventHandler
    public void onKeepAlive(KeepAliveReceivedEvent e) throws IOException {
        client.getExecutor().respondToKeepAlive(client, e.getKeepAliveID());
    }

    @EventHandler
    public void onLoginSuccess(LoginSuccessEvent e) throws IOException {
        client.setCurrentGameState(GameState.CONFIGURATION);
        client.setServerSideProfile(e.getProfile());
        client.getExecutor().acknowledgeLogin(client);
        byte[] brand = e.getClient().getBrand().getBytes(StandardCharsets.UTF_8);
        byte[] data = new byte[brand.length + 1];
        System.arraycopy(brand, 0, data, 1, brand.length);
        data[0] = (byte) brand.length;
        client.getExecutor().sendPluginMessage(client, "minecraft:brand", data);
    }

    @EventHandler
    public void onPlayerListUpdate(PlayerListUpdatedEvent e) {
        List<PlayerInfoAction> actions = e.getActions();
        for (PlayerInfoItem item : e.getItems()) {
            if (actions.contains(PlayerInfoAction.REMOVE_PLAYER)) {
                client.removePlayer(item);
            } else {
                PlayerInfoItem existing = client.getPlayerItem(item.getUuid());
                if (existing == null && !actions.contains(PlayerInfoAction.ADD_PLAYER)) break;
                if (existing == null) existing = new PlayerInfoItem(item.getUuid(), null, null, false, 0, null);
                GameProfile profile = existing.getProfile();
                if (actions.contains(PlayerInfoAction.ADD_PLAYER)) {
                    profile = item.getProfile();
                }

                ChatComponent displayName = existing.getDisplayName();
                if (actions.contains(PlayerInfoAction.UPDATE_DISPLAY_NAME)) displayName = item.getDisplayName();

                GameMode gameMode = existing.getGameMode();
                if (actions.contains(PlayerInfoAction.UPDATE_GAMEMODE)) gameMode = item.getGameMode();

                int ping = existing.getPing();
                if (actions.contains(PlayerInfoAction.UPDATE_LATENCY)) ping = item.getPing();

                boolean listed = existing.isListed();
                if (actions.contains(PlayerInfoAction.UPDATE_LISTED)) listed = item.isListed();

                client.addPlayer(new PlayerInfoItem(item.getUuid(), profile, gameMode, listed, ping, displayName));
            }
        }
    }

    @EventHandler
    public void onPluginMessage(PluginMessageReceivedEvent e) throws IOException {
        if ("minecraft:register".equals(e.getChannel())) {
            client.sendPluginMessage(e.getChannel(), e.getData());
            String[] channels = new String(e.getData(), StandardCharsets.UTF_8).split("\0");
            for (String channel : channels) client.registerPluginChannel(channel);
        } else if ("minecraft:unregister".equals(e.getChannel())) {
            String[] channels = new String(e.getData(), StandardCharsets.UTF_8).split("\0");
            for (String channel : channels) client.registerPluginChannel(channel);
        }
    }

    @EventHandler
    public void onRegistryDataReceived(RegistryDataReceivedEvent e) {
        Identifier reg = e.getRegistryId();
        if ("minecraft".equals(reg.getNamespace())) {
            switch (reg.getValue()) {
                case "chat_type": {
                    int index = 0;
                    for (Entry<Identifier, Tag> entry : e.getRegistryData().entrySet()) {
                        Identifier sub = entry.getKey();
                        if (!"minecraft".equals(sub.getNamespace())) {
                            index++;
                            continue;
                        }
                        ChatType type;
                        try {
                            type = ChatType.valueOf(sub.getValue().toUpperCase());
                        } catch (Exception e2) {
                            type = null;
                        }
                        if (type != null) Registries.CHAT.register(index, type);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }
}
