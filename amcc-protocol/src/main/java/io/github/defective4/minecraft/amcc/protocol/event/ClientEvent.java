package io.github.defective4.minecraft.amcc.protocol.event;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;

public class ClientEvent {

    private final MinecraftClient client;

    protected ClientEvent(MinecraftClient client) {
        this.client = client;
    }

    public MinecraftClient getClient() {
        return client;
    }

}
