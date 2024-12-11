package io.github.defective4.minecraft.amcc.protocol.data;

import java.util.UUID;

public class GameProfile {
    private final String name;
    private final ProfileProperties properties;
    private final UUID uuid;

    public GameProfile(String name, UUID uuid, ProfileProperties properties) {
        this.name = name;
        this.uuid = uuid;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public ProfileProperties getProperties() {
        return properties;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "GameProfile [name=" + name + ", uuid=" + uuid + ", properties=" + properties + "]";
    }

}
