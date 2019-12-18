package me.piggypiglet.randomspawn.mappers.options.hooks;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.options.types.hook.Hooks;
import me.piggypiglet.randomspawn.mappers.options.ListsMapper;
import me.piggypiglet.randomspawn.mappers.options.OptionsMapper;

import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class HooksMapper implements ObjectMapper<Map<String, Object>, Hooks> {
    private static final Hooks DEFAULT = OptionsMapper.DEFAULT.getHooks();
    private static final HookListMapper HOOKS_MAPPER = new HookListMapper("hooks", DEFAULT.getHooks());
    private static final ListsMapper WORLDGUARD_MAPPER = new ListsMapper("hooks.worldguard", DEFAULT.getWorldGuard());
    private static final FactionsMapper FACTIONS_MAPPER = new FactionsMapper();
    private static final GriefPreventionMapper GRIEF_PREVENTION_MAPPER = new GriefPreventionMapper();

    @Override
    public Hooks dataToType(Map<String, Object> map) {
        return new Hooks(
                HOOKS_MAPPER.dataToType(map),
                (int) map.getOrDefault("distance-from-claim", DEFAULT.getDistanceFromClaim()),
                WORLDGUARD_MAPPER.dataToType(map),
                FACTIONS_MAPPER.dataToType(map),
                GRIEF_PREVENTION_MAPPER.dataToType(map)
        );
    }

    @Override
    public Map<String, Object> typeToData(Hooks hooks) {
        return Maps.of(HOOKS_MAPPER.typeToData(hooks.getHooks()))
                .key("distance-from-claim").value(hooks.getDistanceFromClaim())
                .key("worldguard").value(WORLDGUARD_MAPPER.typeToData(hooks.getWorldGuard()))
                .key("factions").value(FACTIONS_MAPPER.typeToData(hooks.getFactions()))
                .key("griefprevention").value(GRIEF_PREVENTION_MAPPER.typeToData(hooks.getGriefPrevention()))
                .build();
    }
}
