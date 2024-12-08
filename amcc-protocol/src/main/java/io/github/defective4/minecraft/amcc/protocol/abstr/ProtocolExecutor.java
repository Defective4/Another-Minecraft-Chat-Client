package io.github.defective4.minecraft.amcc.protocol.abstr;

import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public interface ProtocolExecutor {
    ServerboundPacket createLoginPacket(PlayerProfile profile);
}
