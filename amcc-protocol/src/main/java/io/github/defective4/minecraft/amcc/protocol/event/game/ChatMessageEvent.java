package io.github.defective4.minecraft.amcc.protocol.event.game;

import java.util.UUID;

import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;

public class ChatMessageEvent extends ClientEvent {
    public enum Source {
        OTHER, PLAYER, SYSTEM
    }

    private final ChatComponent message;
    private final int registryIndex;
    private final UUID sender;
    private final ChatComponent senderName, targetName;
    private final Source sourceType;

    public ChatMessageEvent(ChatComponent message) {
        this(message, Source.SYSTEM, null, null, null, -1);
    }

    public ChatMessageEvent(ChatComponent message, Source sourceType, UUID sender, ChatComponent senderName,
            ChatComponent targetName, int registryIndex) {
        this.message = message;
        this.targetName = targetName;
        this.sourceType = sourceType;
        this.sender = sender;
        this.senderName = senderName;
        this.registryIndex = registryIndex;
    }

    public ChatMessageEvent(ChatComponent message, UUID sender, ChatComponent senderName, ChatComponent targetName,
            int registryIndex) {
        this(message, Source.PLAYER, sender, senderName, targetName, registryIndex);
    }

    public ChatComponent getMessage() {
        return message;
    }

    public int getRegistryIndex() {
        return registryIndex;
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
