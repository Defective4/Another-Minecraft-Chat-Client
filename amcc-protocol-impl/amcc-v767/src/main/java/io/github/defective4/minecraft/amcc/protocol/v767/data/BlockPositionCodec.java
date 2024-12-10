package io.github.defective4.minecraft.amcc.protocol.v767.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import io.github.defective4.minecraft.amcc.protocol.abstr.Codec;
import io.github.defective4.minecraft.amcc.protocol.data.BlockPosition;

public class BlockPositionCodec implements Codec<BlockPosition> {

    public static final BlockPositionCodec CODEC = new BlockPositionCodec();

    private BlockPositionCodec() {}

    @Override
    public BlockPosition read(DataInput in) throws IOException {
        long l = in.readLong(); // TODO
        return new BlockPosition(0, 0, 0);
    }

    @Override
    public void write(DataOutput out, BlockPosition val) throws IOException {
        out.writeLong(0); // TODO
    }

}
