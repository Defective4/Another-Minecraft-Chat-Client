package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play;

import static io.github.defective4.minecraft.amcc.protocol.data.DataTypes.*;

import java.util.UUID;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;
import io.github.defective4.minecraft.chatlib.nbt.tag.NBTParser;
import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

public class ServerPlayerChatMessagePacket extends ClientboundPacket {

    public static final PacketFactory<ServerPlayerChatMessagePacket> FACTORY = in -> {
        UUID sender = DataTypes.readUUID(in);
        if (sender.getLeastSignificantBits() == 0 && sender.getMostSignificantBits() == 0) sender = null;
        readVarInt(in);
        if (in.readBoolean()) in.skipBytes(256);
        String message = readVarString(in);
        in.readLong();
        in.readLong();
        int prev = readVarInt(in);
        for (int i = 0; i < prev; i++) {
            readVarInt(in);
            in.skipBytes(256);
        }
        if (in.readBoolean()) NBTParser.parse(in, false);
        if (readVarInt(in) == 2) {
            int len = readVarInt(in);
            for (int i = 0; i < len; i++) in.readLong();
        }
        int chatType = readVarInt(in);
        Tag senderName = NBTParser.parse(in, false);
        Tag targetName = in.readBoolean() ? NBTParser.parse(in, false) : null;
        return new ServerPlayerChatMessagePacket(sender, message, senderName, targetName, chatType);
    };

    private final int chatType;
    private final String message;
    private final UUID sender;
    private final Tag senderName, targetName;

    protected ServerPlayerChatMessagePacket(UUID sender, String message, Tag senderName, Tag targetName, int chatType) {
        this.sender = sender;
        this.message = message;
        this.senderName = senderName;
        this.targetName = targetName;
        this.chatType = chatType;
    }

    public int getChatType() {
        return chatType;
    }

    public String getMessage() {
        return message;
    }

    public UUID getSender() {
        return sender;
    }

    public ChatComponent getSenderName() {
        return ChatComponent.fromNBT(senderName);
    }

    public Tag getSenderNameNBT() {
        return senderName;
    }

    public ChatComponent getTargetName() {
        return ChatComponent.fromNBT(targetName);
    }

    public Tag getTargetNameTag() {
        return targetName;
    }

}
