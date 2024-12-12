package io.github.defective4.minecraft.amcc.protocol.registry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Registry<T> {
    private final Map<Integer, T> data = new HashMap<>();

    protected Registry() {}

    public Map<Integer, T> getData() {
        return Collections.unmodifiableMap(data);
    }

    public T getForIndex(int index) {
        return data.get(index);
    }

    public void register(int indexOffset, T value) {
        data.put(data.size() + indexOffset, value);
    }

    public void register(T value) {
        this.register(0, value);
    }

}
