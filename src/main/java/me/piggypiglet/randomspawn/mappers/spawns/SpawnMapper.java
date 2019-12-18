package me.piggypiglet.randomspawn.mappers.spawns;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.RadiusSpawn;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.data.spawn.types.Spawns;
import me.piggypiglet.randomspawn.mappers.spawns.types.RadiusSpawnMapper;
import me.piggypiglet.randomspawn.mappers.spawns.types.SetSpawnMapper;

import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SpawnMapper implements ObjectMapper<Map.Entry<String, Map<String, Object>>, Spawn> {
    private static final SetSpawnMapper SET_SPAWN_MAPPER = new SetSpawnMapper();
    private static final RadiusSpawnMapper RADIUS_SPAWN_MAPPER = new RadiusSpawnMapper();

    @Override
    public Spawn dataToType(Map.Entry<String, Map<String, Object>> spawn) {
        switch (Spawns.valueOf(((String) spawn.getValue().get("type")).toUpperCase())) {
            case SET:
                return SET_SPAWN_MAPPER.dataToType(spawn);

            case SQUARE:
            case CIRCLE:
                return RADIUS_SPAWN_MAPPER.dataToType(spawn);
        }

        return null;
    }

    @Override
    public Map.Entry<String, Map<String, Object>> typeToData(Spawn spawn) {
        switch (spawn.getType()) {
            case SET:
                return SET_SPAWN_MAPPER.typeToData((SetSpawn) spawn);

            case SQUARE:
            case CIRCLE:
                return RADIUS_SPAWN_MAPPER.typeToData((RadiusSpawn) spawn);
        }

        return null;
    }
}
