package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;
import io.github.defective4.minecraft.chatlib.nbt.tag.NBTParser;
import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

public class ServerDisconnectPacket extends ClientboundPacket {
    public static final PacketFactory<ServerDisconnectPacket> FACTORY = in -> new ServerDisconnectPacket(
            NBTParser.parse(in, false));

    private final Tag message;

    protected ServerDisconnectPacket(Tag message) {
        this.message = message;
    }

    public ChatComponent getMessage() {
        return ChatComponent.fromNBT(message);
    }

    public Tag getMessageTag() {
        return message;
    }

}
