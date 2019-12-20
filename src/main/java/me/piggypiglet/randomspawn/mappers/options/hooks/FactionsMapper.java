package me.piggypiglet.randomspawn.mappers.options.hooks;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.options.types.hook.Factions;
import me.piggypiglet.randomspawn.mappers.options.OptionsMapper;

import java.util.LinkedHashMap;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class FactionsMapper implements ObjectMapper<Map<String, Object>, Factions> {
    private static final String PREFIX = "hooks.factions.";
    private static final Factions DEFAULT = OptionsMapper.DEFAULT.getHooks().getFactions();

    @Override
    public Factions dataToType(Map<String, Object> map) {
        return new Factions(
                (boolean) map.getOrDefault(PREFIX + "safezone", DEFAULT.isSafezone()),
                (boolean) map.getOrDefault(PREFIX + "warzone", DEFAULT.isWarzone()),
                (boolean) map.getOrDefault(PREFIX + "wilderness", DEFAULT.isWilderness()),
                (boolean) map.getOrDefault(PREFIX + "enemy", DEFAULT.isEnemy()),
                (boolean) map.getOrDefault(PREFIX + "own", DEFAULT.isOwn())
        );
    }

    @Override
    public Map<String, Object> typeToData(Factions factions) {
        return Maps.of(new LinkedHashMap<String, Object>())
                .key("safezone", s -> ((boolean) s) != DEFAULT.isSafezone()).value(factions.isSafezone())
                .key("warzone", w -> ((boolean) w) != DEFAULT.isWarzone()).value(factions.isWarzone())
                .key("wilderness", w -> ((boolean) w) != DEFAULT.isWilderness()).value(factions.isWilderness())
                .key("enemy", e -> ((boolean) e) != DEFAULT.isEnemy()).value(factions.isEnemy())
                .key("own", o -> ((boolean) o) != DEFAULT.isOwn()).value(factions.isOwn())
                .build();
    }
}
