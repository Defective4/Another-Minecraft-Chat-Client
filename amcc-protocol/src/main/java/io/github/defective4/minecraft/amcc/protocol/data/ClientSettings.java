package io.github.defective4.minecraft.amcc.protocol.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.github.defective4.minecraft.amcc.protocol.abstr.Codec;

public class ClientSettings {
    public enum ChatMode {
        COMMANDS_ONLY, DISABLED, ENABLED
    }

    public enum MainHand {
        LEFT(0), RIGHT(1);

        private final int i;

        private MainHand(int i) {
            this.i = i;
        }

        public static Codec<MainHand> codec() {
            return new Codec<ClientSettings.MainHand>() {
                @Override
                public MainHand read(DataInput in) throws IOException {
                    int i = DataTypes.readVarInt(in);
                    for (MainHand hand : MainHand.values()) if (hand.i == i) return hand;
                    return null;
                }

                @Override
                public void write(DataOutput out, MainHand val) throws IOException {
                    out.write(val.i);
                }
            };
        }
    }

    public enum SkinPart {
        CAPE(0x01), HAT(0x40), JACKET(0x02), LEFT_PANTS(0x10), LEFT_SLEEVE(0x04), RIGHT_PANTS(0x20), RIGHT_SLEEVE(0x08);

        private final byte mask;

        private SkinPart(int mask) {
            this.mask = (byte) mask;
        }

        public byte getMask() {
            return mask;
        }
    }

    private boolean allowServerListings = true;
    private ChatMode chatMode = ChatMode.ENABLED;
    private boolean enableChatColors = true;
    private final List<SkinPart> enabledSkinParts = Arrays.asList(SkinPart.values());
    private String locale = "en_US";
    private MainHand mainHand = MainHand.RIGHT;
    private int viewDistance = 10;

    public boolean areChatColorsEnabled() {
        return enableChatColors;
    }

    public void disableSkinPart(SkinPart part) {
        Objects.requireNonNull(part);
        enabledSkinParts.remove(part);
    }

    public void enableSkinPart(SkinPart part) {
        Objects.requireNonNull(part);
        if (!enabledSkinParts.contains(part)) enabledSkinParts.add(part);
    }

    public ChatMode getChatMode() {
        return chatMode;
    }

    public List<SkinPart> getEnabledSkinParts() {
        return Collections.unmodifiableList(enabledSkinParts);
    }

    public String getLocale() {
        return locale;
    }

    public MainHand getMainHand() {
        return mainHand;
    }

    public byte getSkinPartsMask() {
        byte mask = 0;
        for (SkinPart part : SkinPart.values()) mask |= part.mask;
        return mask;
    }

    public int getViewDistance() {
        return viewDistance;
    }

    public boolean isAllowServerListings() {
        return allowServerListings;
    }

    public void setAllowServerListings(boolean allowServerListings) {
        this.allowServerListings = allowServerListings;
    }

    public void setChatMode(ChatMode chatMode) {
        Objects.requireNonNull(chatMode);
        this.chatMode = chatMode;
    }

    public void setEnableChatColors(boolean enableChatColors) {
        this.enableChatColors = enableChatColors;
    }

    public void setLocale(String locale) {
        Objects.requireNonNull(locale);
        this.locale = locale;
    }

    public void setMainHand(MainHand mainHand) {
        Objects.requireNonNull(mainHand);
        this.mainHand = mainHand;
    }

    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }

}
