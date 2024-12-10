package io.github.defective4.minecraft.amcc.protocol.event.game;

import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;

public class ChatMessageEvent extends ClientEvent {
    public enum Source {
        OTHER, PLAYER, SYSTEM
    }

    private final ChatComponent message;
    private final Source sourceType;

    public ChatMessageEvent(Source sourceType, ChatComponent message) {
        this.sourceType = sourceType;
        this.message = message;
    }

    public ChatComponent getMessage() {
        return message;
    }

    public Source getSourceType() {
        return sourceType;
    }

}
