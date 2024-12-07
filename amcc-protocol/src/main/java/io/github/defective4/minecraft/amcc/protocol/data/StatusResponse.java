package io.github.defective4.minecraft.amcc.protocol.data;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.defective4.minecraft.amcc.protocol.chat.ChatComponent;

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

    private static class Players {
        private int online, max;
        private List<PlayerSample> sample;

        @Override
        public String toString() {
            return "Players [online=" + online + ", max=" + max + ", sample=" + sample + "]";
        }

    }

    private static class Version {
        private String name;
        private int protocol;

        @Override
        public String toString() {
            return "Version [name=" + name + ", protocol=" + protocol + "]";
        }

    }

    private transient ChatComponent description = ChatComponent.EMPTY;
    private String favicon; // TODO
    private final Players players = new Players();
    private final Version version = new Version();

    private StatusResponse() {}

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

    @Override
    public String toString() {
        return "StatusResponse [players=" + players + ", version=" + version + ", description=" + getDescription()
                + "]";
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
