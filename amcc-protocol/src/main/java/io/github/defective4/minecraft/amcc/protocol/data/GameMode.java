package io.github.defective4.minecraft.amcc.protocol.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import io.github.defective4.minecraft.amcc.protocol.abstr.Codec;

public enum GameMode {
    ADVENTURE(2), CREATIVE(1), SPECTATOR(3), SURVIVAL(0), UNKNOWN(-1);

    private final int id;

    private GameMode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Codec<GameMode> codec() {
        return new Codec<GameMode>() {

            @Override
            public GameMode read(DataInput in) throws IOException {
                return GameMode.getForID(in.readByte());
            }

            @Override
            public void write(DataOutput out, GameMode val) throws IOException {
                out.writeByte(val.getId());
            }
        };
    }

    public static GameMode getForID(int id) {
        for (GameMode gm : values()) if (gm.id == id) return gm;
        return UNKNOWN;
    }
}
