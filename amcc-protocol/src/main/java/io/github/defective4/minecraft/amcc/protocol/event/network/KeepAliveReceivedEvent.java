package io.github.defective4.minecraft.amcc.protocol.event.network;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;

public class KeepAliveReceivedEvent extends ClientEvent {
    private final long keepAliveID;

    public KeepAliveReceivedEvent(long keepAliveID, MinecraftClient client) {
        super(client);
        this.keepAliveID = keepAliveID;
    }

    public long getKeepAliveID() {
        return keepAliveID;
    }

}
