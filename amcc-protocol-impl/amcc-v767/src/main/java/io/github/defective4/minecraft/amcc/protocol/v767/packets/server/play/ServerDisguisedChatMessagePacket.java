package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;
import io.github.defective4.minecraft.chatlib.nbt.tag.NBTParser;
import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

public class ServerDisguisedChatMessagePacket extends ClientboundPacket {
    public static final PacketFactory<ServerDisguisedChatMessagePacket> FACTORY = in -> {
        Tag message = NBTParser.parse(in, false);
        DataTypes.readVarInt(in);
        Tag sender = NBTParser.parse(in, false);
        return new ServerDisguisedChatMessagePacket(message, sender);
    };

    private final Tag message, senderName;

    protected ServerDisguisedChatMessagePacket(Tag message, Tag senderName) {
        this.message = message;
        this.senderName = senderName;
    }

    public ChatComponent getMessage() {
        return ChatComponent.fromNBT(message);
    }

    public Tag getMessageTag() {
        return message;
    }

    public ChatComponent getSenderName() {
        return ChatComponent.fromNBT(senderName);
    }

    public Tag getSenderNameTag() {
        return senderName;
    }

}
