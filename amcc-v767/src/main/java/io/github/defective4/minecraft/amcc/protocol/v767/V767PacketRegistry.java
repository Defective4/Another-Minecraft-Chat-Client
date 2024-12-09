package io.github.defective4.minecraft.amcc.protocol.v767;

import java.util.Map;

import io.github.defective4.minecraft.amcc.protocol.abstr.PacketFactory;
import io.github.defective4.minecraft.amcc.protocol.abstr.PacketRegistry;

public class V767PacketRegistry extends PacketRegistry {

    @Override
    protected void initConfigPackets(Map<Integer, PacketFactory<?>> map) {}

    @Override
    protected void initLoginPackets(Map<Integer, PacketFactory<?>> map) {}

    @Override
    protected void initPlayPackets(Map<Integer, PacketFactory<?>> map) {}

}
