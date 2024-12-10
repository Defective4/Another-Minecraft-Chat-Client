package io.github.defective4.minecraft.amcc.protocol.abstr;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface Codec<T> {
    T read(DataInput in) throws IOException;

    void write(DataOutput out, T val) throws IOException;
}
