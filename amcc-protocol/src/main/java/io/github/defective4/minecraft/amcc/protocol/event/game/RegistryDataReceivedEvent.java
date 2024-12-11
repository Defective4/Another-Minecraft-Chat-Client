package io.github.defective4.minecraft.amcc.protocol.event.game;

import java.util.Collections;
import java.util.Map;

import io.github.defective4.minecraft.amcc.protocol.data.Identifier;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;
import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

public class RegistryDataReceivedEvent extends ClientEvent {
    private final Identifier registryId;
    private final Map<Identifier, Tag> registryData;

    public RegistryDataReceivedEvent(Identifier registryId, Map<Identifier, Tag> registryData) {
        this.registryId = registryId;
        this.registryData = Collections.unmodifiableMap(registryData);
    }

    public Identifier getRegistryId() {
        return registryId;
    }

    public Map<Identifier, Tag> getRegistryData() {
        return registryData;
    }

}
