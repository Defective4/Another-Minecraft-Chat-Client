package io.github.defective4.minecraft.amcc.protocol.data;

import com.google.gson.JsonPrimitive;

import io.github.defective4.minecraft.amcc.protocol.chat.ChatComponent;

public class LegacyStatusResponse extends StatusResponse {
    public LegacyStatusResponse(int protocol, String version, String description, int onlinePlayers, int maxPlayers) {
        this.version = new Version();
        this.version.protocol = protocol;
        this.version.name = version;
        this.description = ChatComponent.fromJson(new JsonPrimitive(description));
        players = new Players();
        players.online = onlinePlayers;
        players.max = maxPlayers;
    }
}
