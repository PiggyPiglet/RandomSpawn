package me.piggypiglet.randomspawn.mappers.spawns.types;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.RadiusSpawn;
import me.piggypiglet.randomspawn.mappers.spawns.data.SpawnDataMapper;

import java.util.AbstractMap;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RadiusSpawnMapper implements ObjectMapper<Map.Entry<String, Map<String, Object>>, RadiusSpawn> {
    private static final SpawnDataMapper SPAWN_DATA_MAPPER = new SpawnDataMapper();

    @Override
    public RadiusSpawn dataToType(Map.Entry<String, Map<String, Object>> entry) {
        final Spawn spawn = SPAWN_DATA_MAPPER.dataToType(entry);
        final Map<String, Object> data = entry.getValue();

        return new RadiusSpawn(
                spawn.getName(), spawn.getType(), spawn.getPermission(), spawn.isEnabled(), spawn.getWorld(), spawn.getOptions(),
                center((String) data.get("center")), (int) data.get("radius")
        );
    }

    @Override
    public Map.Entry<String, Map<String, Object>> typeToData(RadiusSpawn spawn) {
        final Map.Entry<String, Map<String, Object>> data = SPAWN_DATA_MAPPER.typeToData(spawn);

        return new AbstractMap.SimpleEntry<>(
                data.getKey(),
                Maps.of(data.getValue())
                        .key("center").value(spawn.getCenter()[0] + "," + spawn.getCenter()[1])
                        .key("radius").value(spawn.getRadius())
                        .build()
        );
    }

    private double[] center(String str) {
        final String[] parts = str.split(",");
        return new double[] {Double.parseDouble(parts[0]), Double.parseDouble(parts[1])};
    }
}
