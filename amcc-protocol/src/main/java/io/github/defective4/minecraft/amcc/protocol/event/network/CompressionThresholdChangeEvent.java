package io.github.defective4.minecraft.amcc.protocol.event.network;

import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;

public class CompressionThresholdChangeEvent extends ClientEvent {
    private final int newThreshold, oldThreshold;

    public CompressionThresholdChangeEvent(int newThreshold, int oldThreshold) {
        this.newThreshold = newThreshold;
        this.oldThreshold = oldThreshold;
    }

    public int getNewThreshold() {
        return newThreshold;
    }

    public int getOldThreshold() {
        return oldThreshold;
    }

}
