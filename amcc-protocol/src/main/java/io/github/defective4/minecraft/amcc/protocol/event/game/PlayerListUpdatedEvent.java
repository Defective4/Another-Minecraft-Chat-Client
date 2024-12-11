package io.github.defective4.minecraft.amcc.protocol.event.game;

import java.util.Collections;
import java.util.List;

import io.github.defective4.minecraft.amcc.protocol.data.PlayerInfoAction;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerInfoItem;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;

public class PlayerListUpdatedEvent extends ClientEvent {
    private final List<PlayerInfoAction> actions;
    private final List<PlayerInfoItem> items;

    public PlayerListUpdatedEvent(List<PlayerInfoAction> actions, List<PlayerInfoItem> items) {
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
