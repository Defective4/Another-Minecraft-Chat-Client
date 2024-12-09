package io.github.defective4.minecraft.amcc.protocol.packets;

import java.io.DataInput;
import java.io.IOException;
import java.util.UUID;

import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;

public class ClientboundPacket implements DataInput {
    private final byte[] data;
    private DataInput input;

    public ClientboundPacket(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean readBoolean() {
        try {
            return input.readBoolean();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public byte readByte() {
        try {
            return input.readByte();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public char readChar() {
        try {
            return input.readChar();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public double readDouble() {
        try {
            return input.readDouble();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public float readFloat() {
        try {
            return input.readFloat();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void readFully(byte[] b) {
        try {
            input.readFully(b);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void readFully(byte[] b, int off, int len) {
        try {
            input.readFully(b, off, len);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int readInt() {
        try {
            return input.readInt();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public long readLong() {
        try {
            return input.readLong();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public short readShort() {
        try {
            return input.readShort();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int readUnsignedByte() {
        try {
            return input.readUnsignedByte();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int readUnsignedShort() {
        try {
            return input.readUnsignedShort();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String readUTF() {
        try {
            return input.readUTF();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public UUID readUUID() {
        return new UUID(readLong(), readLong());
    }

    public int readVarInt() {
        try {
            return DataTypes.readVarInt(input);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String readVarString() {
        try {
            return DataTypes.readVarString(input);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int skipBytes(int n) {
        try {
            return input.skipBytes(n);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
