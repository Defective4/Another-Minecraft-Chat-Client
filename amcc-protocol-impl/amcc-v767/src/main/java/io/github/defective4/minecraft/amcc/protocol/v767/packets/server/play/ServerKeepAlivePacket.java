package io.github.defective4.minecraft.amcc.protocol.v767.packets.server.play;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;

public class ServerKeepAlivePacket extends ClientboundPacket {

    public static final PacketFactory<ServerKeepAlivePacket> FACTORY = in -> new ServerKeepAlivePacket(in.readLong());

    private final long id;

    protected ServerKeepAlivePacket(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
