package me.piggypiglet.randomspawn.mappers.spawns.types;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.mappers.spawns.data.SpawnDataMapper;
import me.piggypiglet.randomspawn.mappers.spawns.location.LocationMapper;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SetSpawnMapper implements ObjectMapper<Map.Entry<String, Map<String, Object>>, SetSpawn> {
    private static final SpawnDataMapper SPAWN_DATA_MAPPER = new SpawnDataMapper();

    @SuppressWarnings("unchecked")
    @Override
    public SetSpawn dataToType(Map.Entry<String, Map<String, Object>> entry) {
        final Spawn spawn = SPAWN_DATA_MAPPER.dataToType(entry);
        final LocationMapper locationMapper = new LocationMapper(spawn.getWorld());
        final Map<String, Object> data = entry.getValue();

        return new SetSpawn(
                spawn.getName(), spawn.getType(), spawn.getPermission(), spawn.isEnabled(), spawn.getWorld(), spawn.getOptions(),
                ((List<String>) data.getOrDefault("locations", new ArrayList<>())).stream().map(locationMapper::dataToType).collect(Collectors.toSet())
        );
    }

    @Override
    public Map.Entry<String, Map<String, Object>> typeToData(SetSpawn spawn) {
        final LocationMapper locationMapper = new LocationMapper(spawn.getWorld());
        final Map.Entry<String, Map<String, Object>> data = SPAWN_DATA_MAPPER.typeToData(spawn);

        return new AbstractMap.SimpleEntry<>(
                data.getKey(),
                Maps.of(data.getValue())
                        .key("locations").value(spawn.getLocations().stream().map(locationMapper::typeToData).collect(Collectors.toList()))
                        .build()
        );
    }
}
