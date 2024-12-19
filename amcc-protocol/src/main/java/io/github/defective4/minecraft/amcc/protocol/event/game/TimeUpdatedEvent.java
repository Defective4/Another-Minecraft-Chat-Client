package io.github.defective4.minecraft.amcc.protocol.event.game;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;

public class TimeUpdatedEvent extends ClientEvent {

    private final long worldAge, timeOfDay;

    public TimeUpdatedEvent(MinecraftClient client, long worldAge, long timeOfDay) {
        super(client);
        this.worldAge = worldAge;
        this.timeOfDay = timeOfDay % 24000;
    }

    public long getTimeOfDay() {
        return timeOfDay;
    }

    public long getWorldAge() {
        return worldAge;
    }

}
