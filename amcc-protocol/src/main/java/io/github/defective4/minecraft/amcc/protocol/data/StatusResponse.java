package io.github.defective4.minecraft.amcc.protocol.data;

import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.defective4.minecraft.chatlib.chat.ChatComponent;

public class StatusResponse {
    public static class PlayerSample {
        private String id, name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "PlayerSample [id=" + id + ", name=" + name + "]";
        }

    }

    static class Players {
        int online, max;
        private final List<PlayerSample> sample = Collections.emptyList();

        @Override
        public String toString() {
            return "Players [online=" + online + ", max=" + max + ", sample=" + sample + "]";
        }

    }

    static class Version {
        String name;
        int protocol;

        @Override
        public String toString() {
            return "Version [name=" + name + ", protocol=" + protocol + "]";
        }

    }

    transient ChatComponent description = ChatComponent.EMPTY;
    Players players = new Players();
    Version version = new Version();
    private String favicon; // TODO

    protected StatusResponse() {}

    public ChatComponent getDescription() {
        return description;
    }

    public int getMaxPlayers() {
        return players.max;
    }

    public int getOnlinePlayers() {
        return players.online;
    }

    public List<PlayerSample> getPlayersList() {
        return players.sample;
    }

    public int getProtocol() {
        return version.protocol;
    }

    public String getVersionName() {
        return version.name;
    }

    public boolean isLegacy() {
        return this instanceof LegacyStatusResponse;
    }

    @Override
    public String toString() {
        return "StatusResponse [players=" + players + ", version=" + version + ", description="
                + getDescription().toPlainString() + "]";
    }

    public static StatusResponse fromJson(String json) {
        JsonElement el = JsonParser.parseString(json);
        StatusResponse resp = new Gson().fromJson(el, StatusResponse.class);
        if (el.isJsonObject()) {
            JsonObject obj = el.getAsJsonObject();
            JsonElement desc = obj.get("description");
            if (desc != null) resp.description = ChatComponent.fromJson(desc);
        }
        return resp;
    }

}
