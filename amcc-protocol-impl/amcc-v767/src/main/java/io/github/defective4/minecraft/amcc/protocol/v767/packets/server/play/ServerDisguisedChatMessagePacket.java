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
        Tag target = in.readBoolean() ? NBTParser.parse(in, false) : null;
        return new ServerDisguisedChatMessagePacket(message, sender, target);
    };

    private final Tag message, senderName, targetName;

    protected ServerDisguisedChatMessagePacket(Tag message, Tag senderName, Tag targetName) {
        this.message = message;
        this.senderName = senderName;
        this.targetName = targetName;
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

    public ChatComponent getTargetName() {
        return ChatComponent.fromNBT(targetName);
    }

    public Tag getTargetNameTag() {
        return targetName;
    }

}
