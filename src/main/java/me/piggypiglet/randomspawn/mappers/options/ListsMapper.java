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
    private final Lists def;
    private final ListMapper whitelist;
    private final ListMapper blacklist;

    public ListsMapper(String prefix, Lists def) {
        this.def = def;
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
                .key("whitelist", w -> !w.equals(def.getWhitelist())).value(whitelist.typeToData(lists.getWhitelist()))
                .key("blacklist", b -> !b.equals(def.getBlacklist())).value(blacklist.typeToData(lists.getBlacklist()))
                .build();
    }
}
