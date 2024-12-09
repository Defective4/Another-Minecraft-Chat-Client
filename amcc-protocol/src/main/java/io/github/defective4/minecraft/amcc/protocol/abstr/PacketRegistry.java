package io.github.defective4.minecraft.amcc.protocol.abstr;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.github.defective4.minecraft.amcc.protocol.data.GameState;

public abstract class PacketRegistry {
    private final Map<GameState, Map<Integer, PacketFactory<?>>> registry = new HashMap<>();

    public PacketRegistry() {
        Map<Integer, PacketFactory<?>> map;

        map = new HashMap<>();
        initLoginPackets(map);
        registry.put(GameState.LOGIN, Collections.unmodifiableMap(map));

        map = new HashMap<>();
        initConfigPackets(map);
        registry.put(GameState.CONFIGURATION, Collections.unmodifiableMap(map));

        map = new HashMap<>();
        initPlayPackets(map);
        registry.put(GameState.PLAY, Collections.unmodifiableMap(map));
    }

    public PacketFactory<?> getPacket(GameState state, int id) {
        return registry.containsKey(state) ? registry.get(state).get(id) : null;
    }

    protected abstract void initConfigPackets(Map<Integer, PacketFactory<?>> map);

    protected abstract void initLoginPackets(Map<Integer, PacketFactory<?>> map);

    protected abstract void initPlayPackets(Map<Integer, PacketFactory<?>> map);
}
