package io.github.defective4.minecraft.amcc.protocol.event.state;

import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.event.ClientEvent;

public class LoginSuccessEvent extends ClientEvent {
    private final PlayerProfile profile;

    public LoginSuccessEvent(PlayerProfile profile) {
        this.profile = profile;
    }

    public PlayerProfile getProfile() {
        return profile;
    }

}
