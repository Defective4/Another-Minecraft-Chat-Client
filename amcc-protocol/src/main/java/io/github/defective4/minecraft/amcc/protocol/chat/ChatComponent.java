package io.github.defective4.minecraft.amcc.protocol.chat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ChatComponent {
    public static final ChatComponent EMPTY = new ChatComponent();
    private static final Gson PARSER = new Gson();

    private transient ChatComponent[] extra, with;
    private String text, color, translate;

    private ChatComponent() {

    }

    private ChatComponent(String text) {
        this.text = text;
    }

    public List<ChatComponent> flatten() {
        List<ChatComponent> list = new ArrayList<>();
        list.add(shallowClone());

        if (hasExtra()) for (ChatComponent el : getExtra()) {
            list.addAll(el.flatten());
        }
        return Collections.unmodifiableList(list);
    }

    public List<ChatComponent> getArguments() {
        return hasArguments() ? Collections.unmodifiableList(Arrays.asList(with)) : Collections.emptyList();
    }

    public Color getColor() {
        if (color == null) return null;
        NamedChatColor named = NamedChatColor.getForName(color);
        if (named == null) try {
            return Color.decode(color);
        } catch (Exception e) {
            return null;
        }
        return named.getColor();
    }

    public List<ChatComponent> getExtra() {
        return hasExtra() ? Collections.unmodifiableList(Arrays.asList(extra)) : Collections.emptyList();
    }

    public String getText() {
        return text;
    }

    public boolean hasArguments() {
        return with != null && with.length > 0;
    }

    public boolean hasExtra() {
        return extra != null && extra.length > 0;
    }

    public boolean hasText() {
        return text != null;
    }

    public boolean hasTranslate() {
        return translate != null;
    }

    public String toPlainString() {
        StringBuilder builder = new StringBuilder();
        for (ChatComponent cpt : flatten()) {
            if (cpt.hasText()) builder.append(cpt.text);
            if (cpt.hasTranslate()) builder.append(ChatTranslator.translate(cpt.translate, cpt.getArguments()));
        }
        return builder.toString();
    }

    private ChatComponent shallowClone() {
        ChatComponent cpt = new ChatComponent();
        cpt.text = text;
        cpt.color = color;
        cpt.translate = translate;
        cpt.with = with;
        return cpt;
    }

    private String toShallowString() {
        String txt = text;
        if (txt == null) return "";
        return txt;
    }

    public static ChatComponent fromJson(JsonElement element) {
        if (element == null) return EMPTY;
        if (element.isJsonPrimitive()) return new ChatComponent(element.getAsString());
        if (element.isJsonObject()) {
            ChatComponent cpt = PARSER.fromJson(element, ChatComponent.class);
            JsonObject obj = element.getAsJsonObject();
            JsonElement extra = obj.get("extra");
            if (extra instanceof JsonArray) {
                JsonArray extras = extra.getAsJsonArray();
                ChatComponent[] parsedExtra = new ChatComponent[extras.size()];
                for (int i = 0; i < parsedExtra.length; i++) parsedExtra[i] = ChatComponent.fromJson(extras.get(i));
                cpt.extra = parsedExtra;
            }

            JsonElement with = obj.get("with");
            if (with instanceof JsonArray) {
                JsonArray extras = with.getAsJsonArray();
                ChatComponent[] parsedWith = new ChatComponent[extras.size()];
                for (int i = 0; i < parsedWith.length; i++) parsedWith[i] = ChatComponent.fromJson(extras.get(i));
                cpt.with = parsedWith;
            }

            return cpt;
        }
        return EMPTY;
    }

}
