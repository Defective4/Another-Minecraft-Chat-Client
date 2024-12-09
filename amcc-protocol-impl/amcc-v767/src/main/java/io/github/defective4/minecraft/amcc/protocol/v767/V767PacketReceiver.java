package io.github.defective4.minecraft.amcc.protocol.v767;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.abstr.PacketHandler;
import io.github.defective4.minecraft.amcc.protocol.abstr.PacketReceiver;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.event.state.LoginSuccessEvent;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.server.login.ServerLoginSuccessPacket;

public class V767PacketReceiver extends PacketReceiver {

    @PacketHandler
    public void onLoginSuccess(ServerLoginSuccessPacket e, MinecraftClient client) {
        client.dispatchEvent(new LoginSuccessEvent(new PlayerProfile(e.getName(), e.getUuid())));
    }
}
