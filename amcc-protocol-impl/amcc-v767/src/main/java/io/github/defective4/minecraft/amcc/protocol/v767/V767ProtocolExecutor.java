package io.github.defective4.minecraft.amcc.protocol.v767;

import java.io.IOException;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.abstr.ProtocolExecutor;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.config.ClientConfigFinishAckPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.login.ClientLoginStartPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginAcknowledgedPacket;

public class V767ProtocolExecutor implements ProtocolExecutor {

    @Override
    public void acknowledgeConfigFinish(MinecraftClient client) throws IOException {
        client.sendPacket(new ClientConfigFinishAckPacket());
    }

    @Override
    public void acknowledgeLogin(MinecraftClient client) throws IOException {
        client.sendPacket(new ServerLoginAcknowledgedPacket());
    }

    @Override
    public ServerboundPacket createLoginPacket(PlayerProfile profile) {
        return new ClientLoginStartPacket(profile.getName(), profile.getUuid());
    }

}
