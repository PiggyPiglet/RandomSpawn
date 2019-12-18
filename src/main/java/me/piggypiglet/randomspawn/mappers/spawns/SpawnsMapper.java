package me.piggypiglet.randomspawn.mappers.spawns;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.randomspawn.data.spawn.Spawn;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SpawnsMapper implements ObjectMapper<Map<String, Map<String, Object>>, Map<String, Spawn>> {
    private static final SpawnMapper SPAWN_MAPPER = new SpawnMapper();

    @Override
    public Map<String, Spawn> dataToType(Map<String, Map<String, Object>> map) {
        return map.entrySet().stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), SPAWN_MAPPER.dataToType(e)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Map<String, Object>> typeToData(Map<String, Spawn> map) {
        return map.values().stream()
                .map(SPAWN_MAPPER::typeToData)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
