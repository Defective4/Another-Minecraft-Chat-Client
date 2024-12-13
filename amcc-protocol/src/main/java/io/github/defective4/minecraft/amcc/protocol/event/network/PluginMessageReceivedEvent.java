package io.github.defective4.minecraft.amcc.protocol.event.network;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;

public class PluginMessageReceivedEvent extends ClientEvent {
    private final String channel;
    private final byte[] data;

    public PluginMessageReceivedEvent(MinecraftClient client, String channel, byte[] data) {
        super(client);
        this.channel = channel;
        this.data = data;
    }

    public String getChannel() {
        return channel;
    }

    public byte[] getData() {
        return data;
    }
}
