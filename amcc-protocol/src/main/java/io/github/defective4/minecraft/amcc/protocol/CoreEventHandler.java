package io.github.defective4.minecraft.amcc.protocol;

import java.io.IOException;

import io.github.defective4.minecraft.amcc.protocol.event.ClientEventListener;
import io.github.defective4.minecraft.amcc.protocol.event.EventHandler;
import io.github.defective4.minecraft.amcc.protocol.event.state.LoginSuccessEvent;

public class CoreEventHandler implements ClientEventListener {

    private final MinecraftClient client;

    public CoreEventHandler(MinecraftClient client) {
        this.client = client;
    }

    @EventHandler
    public void onLoginSuccess(LoginSuccessEvent e) throws IOException {
        client.setServerSideProfile(e.getProfile());
        client.getExecutor().acknowledgeLogin(client);
    }
}
