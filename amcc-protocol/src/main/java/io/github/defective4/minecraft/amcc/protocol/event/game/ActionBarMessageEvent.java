package io.github.defective4.minecraft.amcc.protocol.event.game;

import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;

public class ActionBarMessageEvent extends ClientEvent {
    private final ChatComponent message;

    public ActionBarMessageEvent(ChatComponent message) {
        this.message = message;
    }

    public ChatComponent getMessage() {
        return message;
    }

}
