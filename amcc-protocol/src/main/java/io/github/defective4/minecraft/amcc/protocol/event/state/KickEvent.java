package io.github.defective4.minecraft.amcc.protocol.event.state;

import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;

public class KickEvent extends ClientEvent {
    private final ChatComponent message;

    public KickEvent(ChatComponent message) {
        this.message = message;
    }

    public ChatComponent getMessage() {
        return message;
    }
}
