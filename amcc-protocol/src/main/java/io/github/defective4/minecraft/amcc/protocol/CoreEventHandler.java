package io.github.defective4.minecraft.amcc.protocol;

import java.io.IOException;

import io.github.defective4.minecraft.amcc.protocol.data.GameState;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEventListener;
import io.github.defective4.minecraft.amcc.protocol.event.EventHandler;
import io.github.defective4.minecraft.amcc.protocol.event.network.CompressionThresholdChangeEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.ConfigurationFinishEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.KickEvent;
import io.github.defective4.minecraft.amcc.protocol.event.state.LoginSuccessEvent;

@SuppressWarnings("unused")
public class CoreEventHandler implements ClientEventListener {

    private final MinecraftClient client;

    protected CoreEventHandler(MinecraftClient client) {
        this.client = client;
    }

    @EventHandler
    public void onCompressionThrChange(CompressionThresholdChangeEvent e) {
        client.setCompressionThreshold(e.getNewThreshold());
    }

    @EventHandler
    public void onConfigFinish(ConfigurationFinishEvent e) throws IOException {
        client.setCurrentGameState(GameState.PLAY);
        client.getExecutor().acknowledgeConfigFinish(client);
    }

    @EventHandler
    public void onDisconnect(KickEvent e) throws IOException {
        client.close();
    }

    @EventHandler
    public void onLoginSuccess(LoginSuccessEvent e) throws IOException {
        client.setCurrentGameState(GameState.CONFIGURATION);
        client.setServerSideProfile(e.getProfile());
        client.getExecutor().acknowledgeLogin(client);
    }
}
