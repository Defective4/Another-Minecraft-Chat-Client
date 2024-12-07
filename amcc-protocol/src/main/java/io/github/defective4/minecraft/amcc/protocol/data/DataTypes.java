package io.github.defective4.minecraft.amcc.protocol.data;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DataTypes {
    private static final int CONTINUE_BIT = 0x80;
    private static final int SEGMENT_BITS = 0x7F;

    public static int readVarInt(DataInputStream is) throws IOException {
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

    public static String readVarString(DataInputStream is) throws IOException {
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
