package io.github.defective4.minecraft.amcc.protocol.packets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import io.github.defective4.minecraft.amcc.protocol.data.DataTypes;

public class ServerboundPacket implements DataOutput {
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private final DataOutputStream wrapper = new DataOutputStream(buffer);

    protected ServerboundPacket(int id) {
        writeByte(id);
    }

    public byte[] getData() {
        try (ByteArrayOutputStream finalBuffer = new ByteArrayOutputStream();
                DataOutputStream finalWrapper = new DataOutputStream(finalBuffer)) {
            byte[] rawData = buffer.toByteArray();
            DataTypes.writeVarInt(finalWrapper, rawData.length);
            finalWrapper.write(rawData);
            return finalBuffer.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void write(byte[] b) {
        try {
            wrapper.write(b);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) {
        try {
            wrapper.write(b, off, len);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void write(int b) {
        try {
            wrapper.write(b);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void writeBoolean(boolean v) {
        try {
            wrapper.writeBoolean(v);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void writeByte(int v) {
        try {
            wrapper.writeByte(v);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void writeBytes(String s) {
        try {
            wrapper.writeBytes(s);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void writeChar(int v) {
        try {
            wrapper.writeChar(v);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void writeChars(String s) {
        try {
            wrapper.writeChars(s);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void writeDouble(double v) {
        try {
            wrapper.writeDouble(v);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void writeFloat(float v) {
        try {
            wrapper.writeFloat(v);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void writeInt(int v) {
        try {
            wrapper.writeInt(v);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void writeLong(long v) {
        try {
            wrapper.writeLong(v);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void writeShort(int v) {
        try {
            wrapper.writeShort(v);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void writeUTF(String s) {
        try {
            wrapper.writeUTF(s);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void writeUUID(UUID uuid) {
        writeLong(uuid.getMostSignificantBits());
        writeLong(uuid.getLeastSignificantBits());
    }

    public void writeVarInt(int value) {
        try {
            DataTypes.writeVarInt(wrapper, value);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void writeVarString(String value) {
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        writeVarInt(data.length);
        write(data);
    }
}
