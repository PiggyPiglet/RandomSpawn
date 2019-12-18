package me.piggypiglet.randomspawn.mappers.options.hooks;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.options.types.hook.GriefPrevention;
import me.piggypiglet.randomspawn.mappers.options.OptionsMapper;

import java.util.LinkedHashMap;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class GriefPreventionMapper implements ObjectMapper<Map<String, Object>, GriefPrevention> {
    private static final String PREFIX = "hooks.griefprevention.";
    private static final GriefPrevention DEFAULT = OptionsMapper.DEFAULT.getHooks().getGriefPrevention();

    @Override
    public GriefPrevention dataToType(Map<String, Object> map) {
        return new GriefPrevention(
                (boolean) map.getOrDefault(PREFIX + "own", DEFAULT.isOwn()),
                (boolean) map.getOrDefault(PREFIX + "other", DEFAULT.isOther()),
                (boolean) map.getOrDefault(PREFIX + "wilderness", DEFAULT.isWilderness())
        );
    }

    @Override
    public Map<String, Object> typeToData(GriefPrevention griefPrevention) {
        return Maps.of(new LinkedHashMap<String, Object>())
                .key("own").value(griefPrevention.isOwn())
                .key("other").value(griefPrevention.isOther())
                .key("wilderness").value(griefPrevention.isWilderness())
                .build();
    }
}
