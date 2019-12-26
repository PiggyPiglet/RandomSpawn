package me.piggypiglet.randomspawn.mappers.spawns.types.rectangle;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.RectangleSpawn;
import me.piggypiglet.randomspawn.mappers.spawns.data.SpawnDataMapper;

import java.util.AbstractMap;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RectangleSpawnMapper implements ObjectMapper<Map.Entry<String, Map<String, Object>>, RectangleSpawn> {
    private static final SpawnDataMapper SPAWN_DATA_MAPPER = new SpawnDataMapper();
    private static final CornerMapper CORNER_MAPPER = new CornerMapper();

    @Override
    public RectangleSpawn dataToType(Map.Entry<String, Map<String, Object>> entry) {
        final Spawn spawn = SPAWN_DATA_MAPPER.dataToType(entry);
        final Map<String, Object> data = entry.getValue();

        return new RectangleSpawn(
                spawn.getName(), spawn.getType(), spawn.getPermission(), spawn.isEnabled(), spawn.getWorld(), spawn.getOptions(),
                CORNER_MAPPER.dataToType((String) data.get("corner1")), CORNER_MAPPER.dataToType((String) data.get("corner2"))
        );
    }

    @Override
    public Map.Entry<String, Map<String, Object>> typeToData(RectangleSpawn spawn) {
        final Map.Entry<String, Map<String, Object>> data = SPAWN_DATA_MAPPER.typeToData(spawn);

        return new AbstractMap.SimpleEntry<>(
                data.getKey(),
                Maps.of(data.getValue(), a -> CORNER_MAPPER.typeToData((int[]) a))
                        .key("corner1").value(spawn.getCorner1())
                        .key("corner2").value(spawn.getCorner2())
                        .build()
        );
    }
}
