package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;
import io.github.defective4.minecraft.chatlib.nbt.tag.NBTParser;
import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

public class ServerSystemChatMessagePacket extends ClientboundPacket {

    public static final PacketFactory<ServerSystemChatMessagePacket> FACTORY = in -> new ServerSystemChatMessagePacket(
            NBTParser.parse(in, false), in.readBoolean());

    private final boolean actionBar;
    private final Tag message;

    protected ServerSystemChatMessagePacket(Tag message, boolean actionBar) {
        this.message = message;
        this.actionBar = actionBar;
    }

    public Tag getContent() {
        return message;
    }

    public ChatComponent getMessage() {
        return ChatComponent.fromNBT(message);
    }

    public boolean isActionBar() {
        return actionBar;
    }

}
