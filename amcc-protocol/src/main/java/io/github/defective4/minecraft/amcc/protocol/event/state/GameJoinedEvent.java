package io.github.defective4.minecraft.amcc.protocol.event.state;

import java.util.List;

import io.github.defective4.minecraft.amcc.protocol.data.GameMode;
import io.github.defective4.minecraft.amcc.protocol.data.Identifier;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;

public class GameJoinedEvent extends ClientEvent {
    private final Identifier dimension;
    private final List<Identifier> dimensions;
    private final boolean enforcesSecureChat;
    private final int entityID;
    private final GameMode gameMode, previousGameMode;
    private final boolean hardcore;
    private final long hashedSeed;
    private final boolean reducedDebugInfo;
    private final int viewDistance, simDistance;

    public GameJoinedEvent(int entityID, boolean hardcore, List<Identifier> dimensions, int viewDistance,
            int simDistance, boolean reducedDebugInfo, Identifier dimension, long hashedSeed, GameMode gameMode,
            GameMode previousGameMode, boolean enforcesSecureChat) {
        this.entityID = entityID;
        this.hardcore = hardcore;
        this.dimensions = dimensions;
        this.viewDistance = viewDistance;
        this.simDistance = simDistance;
        this.reducedDebugInfo = reducedDebugInfo;
        this.dimension = dimension;
        this.hashedSeed = hashedSeed;
        this.gameMode = gameMode;
        this.previousGameMode = previousGameMode;
        this.enforcesSecureChat = enforcesSecureChat;
    }

    public Identifier getDimension() {
        return dimension;
    }

    public List<Identifier> getDimensions() {
        return dimensions;
    }

    public int getEntityID() {
        return entityID;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public long getHashedSeed() {
        return hashedSeed;
    }

    public GameMode getPreviousGameMode() {
        return previousGameMode;
    }

    public int getSimDistance() {
        return simDistance;
    }

    public int getViewDistance() {
        return viewDistance;
    }

    public boolean isEnforcesSecureChat() {
        return enforcesSecureChat;
    }

    public boolean isHardcore() {
        return hardcore;
    }

    public boolean isReducedDebugInfo() {
        return reducedDebugInfo;
    }

}
