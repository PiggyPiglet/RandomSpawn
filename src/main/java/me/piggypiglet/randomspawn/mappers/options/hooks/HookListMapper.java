package me.piggypiglet.randomspawn.mappers.options.hooks;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.options.types.hook.HookList;
import me.piggypiglet.randomspawn.data.options.types.hook.HookTypes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class HookListMapper implements ObjectMapper<Map<String, Object>, HookList> {
    private final String prefix;
    private final HookList def;

    public HookListMapper(String prefix, HookList def) {
        this.prefix = prefix;
        this.def = def;
    }

    @SuppressWarnings("unchecked")
    @Override
    public HookList dataToType(Map<String, Object> map) {
        return new HookList(
                (boolean) map.getOrDefault(prefix + ".enabled", def.isEnabled()),
                ((Collection<String>) map.getOrDefault(prefix + ".values", def.getValues().stream()
                        .map(HookTypes::toString)
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet())))
                        .stream()
                        .map(HookTypes::valueOf)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public Map<String, Object> typeToData(HookList hookList) {
        return Maps.of(new LinkedHashMap<String, Object>())
                .key("enabled").value(hookList.isEnabled())
                .key("values").value(hookList.getValues().stream().map(HookTypes::toString).collect(Collectors.toSet()))
                .build();
    }
}
