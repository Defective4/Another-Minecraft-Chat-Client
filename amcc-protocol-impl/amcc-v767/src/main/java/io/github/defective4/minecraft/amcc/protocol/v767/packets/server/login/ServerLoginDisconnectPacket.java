package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login;

import com.google.gson.JsonParser;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;

public class ServerLoginDisconnectPacket extends ClientboundPacket {

    public static final PacketFactory<ServerLoginDisconnectPacket> FACTORY = in -> new ServerLoginDisconnectPacket(
            DataTypes.readVarString(in));

    private final String json;

    public ServerLoginDisconnectPacket(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }

    public ChatComponent getMessage() {
        return ChatComponent.fromJson(JsonParser.parseString(json));
    }
}
