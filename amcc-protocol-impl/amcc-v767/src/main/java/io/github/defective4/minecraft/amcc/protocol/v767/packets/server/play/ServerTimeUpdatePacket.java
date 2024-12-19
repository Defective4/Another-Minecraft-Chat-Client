package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;

public class ServerTimeUpdatePacket extends ClientboundPacket {
    public static final PacketFactory<ServerTimeUpdatePacket> FACTORY = in -> new ServerTimeUpdatePacket(in.readLong(),
            in.readLong());

    private final long worldAge, time;

    protected ServerTimeUpdatePacket(long worldAge, long time) {
        this.worldAge = worldAge;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public long getWorldAge() {
        return worldAge;
    }

}
