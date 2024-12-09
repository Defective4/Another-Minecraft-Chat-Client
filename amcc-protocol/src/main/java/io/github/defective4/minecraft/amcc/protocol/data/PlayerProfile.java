package io.github.defective4.minecraft.amcc.protocol.data;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class PlayerProfile {
    private final String name;
    private final UUID uuid;

    public PlayerProfile(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "PlayerProfile [name=" + name + ", uuid=" + uuid + "]";
    }

    public static PlayerProfile createOfflineProfile(String name) {
        return new PlayerProfile(name,
                UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8)));
    }

}
