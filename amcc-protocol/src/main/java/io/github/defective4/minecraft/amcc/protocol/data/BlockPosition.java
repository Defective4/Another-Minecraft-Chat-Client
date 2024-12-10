package io.github.defective4.minecraft.amcc.protocol.data;

public class BlockPosition {
    private final int x, y, z;

    public BlockPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "BlockPosition [x=" + x + ", y=" + y + ", z=" + z + "]";
    }

}
