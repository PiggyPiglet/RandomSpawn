package me.piggypiglet.randomspawn.mappers.options;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.options.types.list.List;

import java.util.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ListMapper implements ObjectMapper<Map<String, Object>, List> {
    private final String prefix;
    private final List def;

    public ListMapper(String prefix, List def) {
        this.prefix = prefix;
        this.def = def;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List dataToType(Map<String, Object> map) {
        return new List(
                (boolean) map.getOrDefault(prefix + ".enabled", def.isEnabled()),
                new HashSet<>((Collection<String>) map.getOrDefault(prefix + ".values", def.getValues()))
        );
    }

    @Override
    public Map<String, Object> typeToData(List list) {
        return Maps.of(new LinkedHashMap<String, Object>())
                .key("enabled", e -> ((boolean) e) != def.isEnabled()).value(list.isEnabled())
                .key("values", v -> !v.equals(new ArrayList<>(def.getValues()))).value(new ArrayList<>(list.getValues()))
                .build();
    }
}
