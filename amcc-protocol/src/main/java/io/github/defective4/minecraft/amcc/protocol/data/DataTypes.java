package io.github.defective4.minecraft.amcc.protocol.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class DataTypes {
    private static final int CONTINUE_BIT = 0x80;
    private static final int SEGMENT_BITS = 0x7F;

    public static int getVarIntSize(int value) {
        int size = 0;
        while (true) {
            if ((value & ~SEGMENT_BITS) == 0) {
                size++;
                return size;
            }
            size++;
            value >>>= 7;
        }
    }

    public static UUID readUUID(DataInput in) throws IOException {
        return new UUID(in.readLong(), in.readLong());
    }

    public static int readVarInt(DataInput is) throws IOException {
        int value = 0;
        int position = 0;
        byte currentByte;
        while (true) {
            currentByte = is.readByte();
            value |= (currentByte & SEGMENT_BITS) << position;
            if ((currentByte & CONTINUE_BIT) == 0) break;
            position += 7;
            if (position >= 32) throw new RuntimeException("VarInt is too big");
        }
        return value;
    }

    public static String readVarString(DataInput is) throws IOException {
        byte[] data = new byte[readVarInt(is)];
        is.readFully(data);
        return new String(data, StandardCharsets.UTF_8);
    }

    public static void writeVarInt(DataOutput out, int value) throws IOException {
        while (true) {
            if ((value & ~SEGMENT_BITS) == 0) {
                out.writeByte(value);
                return;
            }
            out.writeByte(value & SEGMENT_BITS | CONTINUE_BIT);
            value >>>= 7;
        }
    }
}
