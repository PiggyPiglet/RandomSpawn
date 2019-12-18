package me.piggypiglet.randomspawn.mappers.options;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.options.types.list.Lists;

import java.util.LinkedHashMap;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ListsMapper implements ObjectMapper<Map<String, Object>, Lists> {
    private final ListMapper whitelist;
    private final ListMapper blacklist;

    public ListsMapper(String prefix, Lists def) {
        whitelist = new ListMapper(prefix + ".whitelist", def.getWhitelist());
        blacklist = new ListMapper(prefix + ".blacklist", def.getBlacklist());
    }

    @Override
    public Lists dataToType(Map<String, Object> map) {
        return new Lists(
                whitelist.dataToType(map),
                blacklist.dataToType(map)
        );
    }

    @Override
    public Map<String, Object> typeToData(Lists lists) {
        return Maps.of(new LinkedHashMap<String, Object>())
                .key("whitelist").value(whitelist.typeToData(lists.getWhitelist()))
                .key("blacklist").value(blacklist.typeToData(lists.getBlacklist()))
                .build();
    }
}
