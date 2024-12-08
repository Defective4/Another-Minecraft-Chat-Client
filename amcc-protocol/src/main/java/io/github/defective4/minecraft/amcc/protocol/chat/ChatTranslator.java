package io.github.defective4.minecraft.amcc.protocol.chat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ChatTranslator {
    private static final Map<String, String> values = new HashMap<>();

    public static void clearValues() {
        values.clear();
    }

    public static Set<Entry<String, String>> entrySet() {
        return values.entrySet();
    }

    public static String getValue(Object key) {
        return values.get(key);
    }

    public static void loadValues(File file) throws FileNotFoundException, IOException {
        try (Reader reader = new FileReader(file)) {
            loadValues(reader);
        }
    }

    public static void loadValues(Reader reader) {
        JsonElement el = JsonParser.parseReader(reader);
        if (el.isJsonObject()) {
            for (Map.Entry<String, JsonElement> e : el.getAsJsonObject().entrySet()) {
                JsonElement value = e.getValue();
                if (value.isJsonPrimitive()) putValue(e.getKey(), value.getAsString());
            }
        }
    }

    public static String putValue(String key, String value) {
        return values.put(key, value);
    }

    public static void putValues(Map<String, String> values) {
        ChatTranslator.values.putAll(values);
    }

    public static String removeValue(Object key) {
        return values.remove(key);
    }

    public static String translate(String key, ChatComponent... args) {
        if (args == null) args = new ChatComponent[0];
        return translate(key, Arrays.asList(args));
    }

    public static String translate(String key, List<ChatComponent> args) {
        if (args == null) args = Collections.emptyList();
        if (values.containsKey(key)) {
            Object[] with = new String[args.size()];
            for (int i = 0; i < with.length; i++) with[i] = args.get(i).toPlainString();
            return String.format(values.get(key), with);
        }
        return key;
    }
}
