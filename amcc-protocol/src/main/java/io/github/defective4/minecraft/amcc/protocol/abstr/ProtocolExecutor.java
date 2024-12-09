package io.github.defective4.minecraft.amcc.protocol.abstr;

import java.io.IOException;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public interface ProtocolExecutor {
    ServerboundPacket createLoginPacket(PlayerProfile profile);

    void acknowledgeLogin(MinecraftClient client) throws IOException;
}
