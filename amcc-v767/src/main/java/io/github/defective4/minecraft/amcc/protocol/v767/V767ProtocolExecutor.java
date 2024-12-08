package io.github.defective4.minecraft.amcc.protocol.v767;

import io.github.defective4.minecraft.amcc.protocol.abstr.ProtocolExecutor;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerProfile;
import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.packets.client.login.ClientLoginStartPacket;

public class V767ProtocolExecutor implements ProtocolExecutor {

    @Override
    public ServerboundPacket createLoginPacket(PlayerProfile profile) {
        return new ClientLoginStartPacket(profile.getName(), profile.getUuid());
    }

}
