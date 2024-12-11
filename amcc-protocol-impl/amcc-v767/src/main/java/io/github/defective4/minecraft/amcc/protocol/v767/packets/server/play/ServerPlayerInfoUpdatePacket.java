package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play;

import static io.github.defective4.minecraft.amcc.protocol.data.DataTypes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.data.GameMode;
import io.github.defective4.minecraft.amcc.protocol.data.GameProfile;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerInfoAction;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerInfoItem;
import io.github.defective4.minecraft.amcc.protocol.data.ProfileProperties;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.V767Codecs;
import io.github.defective4.minecraft.chatlib.chat.ChatComponent;
import io.github.defective4.minecraft.chatlib.nbt.tag.NBTParser;
import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

public class ServerPlayerInfoUpdatePacket extends ClientboundPacket {

    public static final PacketFactory<ServerPlayerInfoUpdatePacket> FACTORY = in -> {
        List<PlayerInfoAction> actions = V767Codecs.PLAYER_INFO_ACTIONS.read(in);
        int numPlayers = readVarInt(in);
        List<PlayerInfoItem> items = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            UUID uuid = readUUID(in);
            String name = null;
            ProfileProperties properties = new ProfileProperties();
            GameMode gameMode = GameMode.UNKNOWN;
            boolean listed = false;
            int ping = 0;
            Tag displayName = null;

            if (actions.contains(PlayerInfoAction.ADD_PLAYER)) {
                name = readVarString(in);
                int numProperties = readVarInt(in);
                for (int j = 0; j < numProperties; j++) {
                    properties.put(readVarString(in), readVarString(in));
                    if (in.readBoolean()) readVarString(in);
                }
            }

            if (actions.contains(PlayerInfoAction.INITIALIZE_CHAT) && in.readBoolean()) {
                readUUID(in);
                in.readLong();
                in.skipBytes(readVarInt(in));
                in.skipBytes(readVarInt(in));
            }

            if (actions.contains(PlayerInfoAction.UPDATE_GAMEMODE)) gameMode = GameMode.codec().read(in);
            if (actions.contains(PlayerInfoAction.UPDATE_LISTED)) listed = in.readBoolean();
            if (actions.contains(PlayerInfoAction.UPDATE_LATENCY)) ping = readVarInt(in);
            if (actions.contains(PlayerInfoAction.UPDATE_DISPLAY_NAME) && in.readBoolean())
                displayName = NBTParser.parse(in, false);
            items
                    .add(new PlayerInfoItem(uuid, new GameProfile(name, uuid, properties), gameMode, listed, ping,
                            displayName == null ? null : ChatComponent.fromNBT(displayName)));
        }
        return new ServerPlayerInfoUpdatePacket(actions, items);
    };
    private final List<PlayerInfoAction> actions;

    private final List<PlayerInfoItem> items;

    protected ServerPlayerInfoUpdatePacket(List<PlayerInfoAction> actions, List<PlayerInfoItem> items) {
        this.actions = Collections.unmodifiableList(actions);
        this.items = Collections.unmodifiableList(items);
    }

    public List<PlayerInfoAction> getActions() {
        return actions;
    }

    public List<PlayerInfoItem> getItems() {
        return items;
    }

}
