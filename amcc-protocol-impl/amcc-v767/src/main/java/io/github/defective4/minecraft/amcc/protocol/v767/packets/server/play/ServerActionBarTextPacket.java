package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;
import io.github.defective4.minecraft.chatlib.nbt.tag.NBTParser;
import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

public class ServerActionBarTextPacket extends ClientboundPacket {
    public static final PacketFactory<ServerActionBarTextPacket> FACTORY = in -> new ServerActionBarTextPacket(
            NBTParser.parse(in, false));

    private final Tag text;

    protected ServerActionBarTextPacket(Tag text) {
        this.text = text;
    }

    public Tag getContent() {
        return text;
    }

    public ChatComponent getText() {
        return ChatComponent.fromNBT(text);
    }
}
