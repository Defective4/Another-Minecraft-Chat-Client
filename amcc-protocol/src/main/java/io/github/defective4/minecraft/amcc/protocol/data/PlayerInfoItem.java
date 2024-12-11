package io.github.defective4.minecraft.amcc.protocol.data;

import java.util.UUID;

import io.github.defective4.minecraft.chatlib.chat.ChatComponent;

public class PlayerInfoItem {
    private final ChatComponent displayName;
    private final GameMode gameMode;
    private final boolean listed;
    private final int ping;
    private final GameProfile profile;
    private final UUID uuid;

    public PlayerInfoItem(UUID uuid, GameProfile profile, GameMode gameMode, boolean listed, int ping,
            ChatComponent displayName) {
        this.uuid = uuid;
        this.profile = profile;
        this.gameMode = gameMode;
        this.listed = listed;
        this.ping = ping;
        this.displayName = displayName;
    }

    public ChatComponent getDisplayName() {
        return displayName;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public int getPing() {
        return ping;
    }

    public GameProfile getProfile() {
        return profile;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isListed() {
        return listed;
    }

    @Override
    public String toString() {
        return "PlayerInfoItem [uuid=" + uuid + ", profile=" + profile + ", gameMode=" + gameMode + ", listed=" + listed
                + ", ping=" + ping + ", displayName=" + (displayName == null ? null : displayName.toPlainString())
                + "]";
    }

}
