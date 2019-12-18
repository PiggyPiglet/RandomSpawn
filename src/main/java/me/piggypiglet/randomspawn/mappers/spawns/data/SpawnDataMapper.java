package me.piggypiglet.randomspawn.mappers.spawns.data;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.Spawns;
import me.piggypiglet.randomspawn.mappers.options.OptionsMapper;
import org.bukkit.Bukkit;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SpawnDataMapper implements ObjectMapper<Map.Entry<String, Map<String, Object>>, Spawn> {
    private static final OptionsMapper OPTIONS_MAPPER = new OptionsMapper();

    @SuppressWarnings("unchecked")
    @Override
    public Spawn dataToType(Map.Entry<String, Map<String, Object>> entry) {
        final Map<String, Object> map = entry.getValue();

        return new SpawnData(
                entry.getKey(),
                Spawns.valueOf(((String) map.get("type")).toUpperCase()),
                (String) map.getOrDefault("permission", "randomspawn.spawns." + entry.getKey()),
                (boolean) map.getOrDefault("enabled", true),
                Bukkit.getWorld((String) map.get("world")),
                OPTIONS_MAPPER.dataToType(
                        ((Map<String, Object>) map.getOrDefault("options", new HashMap<>())).entrySet().stream()
                                .flatMap(Maps::flatten)
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                )
        );
    }

    @Override
    public Map.Entry<String, Map<String, Object>> typeToData(Spawn spawn) {
        return new AbstractMap.SimpleEntry<>(
                spawn.getName(),
                Maps.of(new LinkedHashMap<String, Object>())
                        .key("type").value(spawn.getType())
                        .key("permission").value(spawn.getPermission())
                        .key("enabled").value(spawn.isEnabled())
                        .key("world").value(spawn.getWorld().getName())
                        .key("options").value(OPTIONS_MAPPER.typeToData(spawn.getOptions()))
                        .build()
        );
    }
}
