package io.github.defective4.minecraft.amcc.protocol.event.state;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;

public class ConfigurationRedoEvent extends ClientEvent {
    public ConfigurationRedoEvent(MinecraftClient client) {
        super(client);
    }
}
