package io.github.defective4.minecraft.amcc.protocol.event.game;

import java.util.UUID;

import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;

public class ChatMessageEvent extends ClientEvent {
    public enum Source {
        OTHER, PLAYER, SYSTEM
    }

    private final ChatComponent message;
    private final UUID sender;
    private final ChatComponent senderName, targetName;
    private final Source sourceType;

    public ChatMessageEvent(ChatComponent message) {
        this(message, Source.SYSTEM, null, null, null);
    }

    public ChatMessageEvent(ChatComponent message, Source sourceType, UUID sender, ChatComponent senderName,
            ChatComponent targetName) {
        this.message = message;
        this.targetName = targetName;
        this.sourceType = sourceType;
        this.sender = sender;
        this.senderName = senderName;
    }

    public ChatMessageEvent(ChatComponent message, UUID sender, ChatComponent senderName, ChatComponent targetName) {
        this(message, Source.PLAYER, sender, senderName, targetName);
    }

    public ChatComponent getMessage() {
        return message;
    }

    public UUID getSender() {
        return sender;
    }

    public ChatComponent getSenderName() {
        return senderName;
    }

    public Source getSourceType() {
        return sourceType;
    }

    public ChatComponent getTargetName() {
        return targetName;
    }

}
