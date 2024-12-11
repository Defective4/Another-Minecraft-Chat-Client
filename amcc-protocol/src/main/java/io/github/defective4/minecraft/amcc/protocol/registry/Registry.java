package io.github.defective4.minecraft.amcc.protocol.registry;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

public class Registry<T> {
    private final Map<T, Tag> data = new LinkedHashMap<>();

    protected Registry() {}

    public Map<T, Tag> getData() {
        return Collections.unmodifiableMap(data);
    }

    public Map.Entry<T, Tag> getForIndex(int index) {
        if (index >= data.size()) return null;
        Iterator<Entry<T, Tag>> it = data.entrySet().iterator();
        for (int i = 1; i < index; i++) it.next();
        return it.next();
    }

    public void register(T key, Tag value) {
        data.put(key, value);
    }

}
