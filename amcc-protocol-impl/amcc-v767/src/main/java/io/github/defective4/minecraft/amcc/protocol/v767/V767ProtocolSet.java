package io.github.defective4.minecraft.amcc.protocol.v767;

import io.github.defective4.minecraft.amcc.protocol.abstr.ProtocolSet;

public class V767ProtocolSet extends ProtocolSet {
    public V767ProtocolSet() {
        super(767, new V767ProtocolExecutor(), new V767PacketRegistry(), new V767PacketReceiver());
    }
}
