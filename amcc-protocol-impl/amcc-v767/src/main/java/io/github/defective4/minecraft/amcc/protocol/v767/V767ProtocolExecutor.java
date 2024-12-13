package io.github.defective4.minecraft.amcc.protocol.v767;

import java.io.IOException;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.abstr.ProtocolExecutor;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.config.ClientConfigFinishAckPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.config.ClientConfigPluginMessagePacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.login.ClientLoginAcknowledgedPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.login.ClientLoginStartPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.play.ClientChatCommandPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.play.ClientChatMessagePacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.play.ClientKeepAlivePacket;

public class V767ProtocolExecutor implements ProtocolExecutor {

    @Override
    public void sendPluginMessage(MinecraftClient client, String channel, byte[] data) throws IOException {
        switch (client.getCurrentGameState()) {
            case CONFIGURATION:
                client.sendPacket(new ClientConfigPluginMessagePacket(channel, data));
                break;
            default:
                throw new IllegalStateException(
                        "Plugin messages can't be sent during the " + client.getCurrentGameState() + " state");
        }
    }

    @Override
    public void acknowledgeConfigFinish(MinecraftClient client) throws IOException {
        client.sendPacket(new ClientConfigFinishAckPacket());
    }

    @Override
    public void acknowledgeLogin(MinecraftClient client) throws IOException {
        client.sendPacket(new ClientLoginAcknowledgedPacket());
    }

    @Override
    public ServerboundPacket createLoginPacket(PlayerProfile profile) {
        return new ClientLoginStartPacket(profile.getName(), profile.getUuid());
    }

    @Override
    public void respondToKeepAlive(MinecraftClient client, long id) throws IOException {
        client.sendPacket(new ClientKeepAlivePacket(id));
    }

    @Override
    public void sendChatMessage(MinecraftClient client, String message) throws IOException {
        if (message.startsWith("/")) {
            client.sendPacket(new ClientChatCommandPacket(message.substring(1)));
        } else {
            client.sendPacket(new ClientChatMessagePacket(message));
        }
    }

}
