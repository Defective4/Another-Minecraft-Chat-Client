package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play;

import static io.github.defective4.minecraft.amcc.protocol.data.DataTypes.readVarInt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.data.BlockPosition;
import io.github.defective4.minecraft.amcc.protocol.data.GameMode;
import io.github.defective4.minecraft.amcc.protocol.data.Identifier;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.data.BlockPositionCodec;

public class ServerGameJoinPacket extends ClientboundPacket {

    public static final PacketFactory<ServerGameJoinPacket> FACTORY = in -> {
        int entityID = in.readInt();
        boolean hardcore = in.readBoolean();
        int dimensionsCount = readVarInt(in);
        List<Identifier> dimensions = new ArrayList<>();
        for (int i = 0; i < dimensionsCount; i++) {
            dimensions.add(Identifier.CODEC.read(in));
        }
        readVarInt(in);
        int viewDistance = readVarInt(in);
        int simDistance = readVarInt(in);
        boolean reducedDebugInfo = in.readBoolean();
        in.readBoolean();
        in.readBoolean();
        readVarInt(in);
        Identifier dimension = Identifier.CODEC.read(in);
        long hashedSeed = in.readLong();
        GameMode gameMode = GameMode.codec().read(in);
        GameMode previousGameMode = GameMode.codec().read(in);
        Identifier deathDimension = null;
        BlockPosition deathLocation = null;
        if (in.readBoolean()) {
            deathDimension = Identifier.CODEC.read(in);
            deathLocation = BlockPositionCodec.CODEC.read(in);
        }
        readVarInt(in);
        boolean enforcesSecureChat = in.readBoolean();
        return new ServerGameJoinPacket(entityID, hardcore, dimensions, viewDistance, simDistance, reducedDebugInfo,
                dimension, hashedSeed, gameMode, previousGameMode, deathDimension, deathLocation, enforcesSecureChat);
    };

    private final Identifier deathDimension;
    private final BlockPosition deathLocation;
    private final Identifier dimension;
    private final List<Identifier> dimensions;
    private final boolean enforcesSecureChat;
    private final int entityID;
    private final GameMode gameMode, previousGameMode;
    private final boolean hardcore;
    private final long hashedSeed;
    private final boolean reducedDebugInfo;
    private final int viewDistance, simDistance;

    protected ServerGameJoinPacket(int entityID, boolean hardcore, List<Identifier> dimensions, int viewDistance,
            int simDistance, boolean reducedDebugInfo, Identifier dimension, long hashedSeed, GameMode gameMode,
            GameMode previousGameMode, Identifier deathDimension, BlockPosition deathLocation,
            boolean enforcesSecureChat) {
        this.entityID = entityID;
        this.hardcore = hardcore;
        this.dimensions = Collections.unmodifiableList(dimensions);
        this.viewDistance = viewDistance;
        this.simDistance = simDistance;
        this.reducedDebugInfo = reducedDebugInfo;
        this.dimension = dimension;
        this.hashedSeed = hashedSeed;
        this.gameMode = gameMode;
        this.previousGameMode = previousGameMode;
        this.deathDimension = deathDimension;
        this.deathLocation = deathLocation;
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
