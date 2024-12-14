package io.github.defective4.minecraft.amcc.protocol.v767.packets.client.config;

import java.io.IOException;

import io.github.defective4.minecraft.amcc.protocol.data.ClientSettings;
import io.github.defective4.minecraft.amcc.protocol.data.ClientSettings.MainHand;
import io.github.defective4.minecraft.amcc.protocol.packets.ServerboundPacket;
import io.github.defective4.minecraft.amcc.protocol.v767.V767Codecs;

public class ClientConfigSettingsPacket extends ServerboundPacket {

    public ClientConfigSettingsPacket(ClientSettings settings) {
        super(0x00);
        try {
            writeVarString(settings.getLocale());
            writeByte(settings.getViewDistance());
            V767Codecs.CHAT_MODE.write(this, settings.getChatMode());
            writeBoolean(settings.areChatColorsEnabled());
            writeByte(settings.getSkinPartsMask());
            MainHand.codec().write(this, settings.getMainHand());
            writeBoolean(false);
            writeBoolean(settings.isAllowServerListings());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}