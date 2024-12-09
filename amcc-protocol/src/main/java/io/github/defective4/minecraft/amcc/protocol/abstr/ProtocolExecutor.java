package io.github.defective4.minecraft.amcc.protocol.abstr;

import java.io.IOException;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;

public interface ProtocolExecutor {
    void acknowledgeConfigFinish(MinecraftClient client) throws IOException;

    void acknowledgeLogin(MinecraftClient client) throws IOException;

    ServerboundPacket createLoginPacket(PlayerProfile profile);
}
