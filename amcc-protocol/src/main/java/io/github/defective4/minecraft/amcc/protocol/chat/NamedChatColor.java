package io.github.defective4.minecraft.amcc.protocol.chat;

import java.awt.Color;

public enum NamedChatColor {
    AQUA('b', 5636095),
    BLACK('0', 0),
    BLUE('9', 5592575),
    BOLD('l', -1),
    DARK_AQUA('3', 43690),
    DARK_BLUE('1', 170),
    DARK_GRAY('8', 5592405),
    DARK_GREEN('2', 43520),
    DARK_PURPLE('5', 11141290),
    DARK_RED('4', 11141120),
    GOLD('6', 16755200),
    GRAY('7', 11184810),
    GREEN('a', 5635925),
    ITALIC('o', -1),
    LIGHT_PURPLE('d', 16733695),
    OBFUSCATED('k', -1),
    RED('c', 16733525),
    RESET('r', -1),
    STRIKETHROUGH('m', -1),
    UNDERLINE('n', -1),
    WHITE('f', 16777215),
    YELLOW('e', 16777045);

    private final char code;
    private final int rgb;

    private NamedChatColor(char code, int rgb) {
        this.code = code;
        this.rgb = rgb;
    }

    public char getCode() {
        return code;
    }

    public Color getColor() {
        return new Color(rgb);
    }

    public int getRgb() {
        return rgb;
    }

    public static NamedChatColor getForName(String name) {
        for (NamedChatColor color : values()) if (color.name().equalsIgnoreCase(name)) return color;
        return null;
    }
}
