package me.piggypiglet.randomspawn.mappers.options;

import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.options.Options;
import me.piggypiglet.randomspawn.data.options.types.hook.*;
import me.piggypiglet.randomspawn.data.options.types.list.List;
import me.piggypiglet.randomspawn.data.options.types.list.Lists;
import me.piggypiglet.randomspawn.mappers.options.hooks.HooksMapper;

import java.util.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class OptionsMapper implements ObjectMapper<Map<String, Object>, Options> {
    public static final Options DEFAULT = new Options(
            new Lists(
                    new List(
                            true, Collections.singleton("grass_block")
                    ),
                    new List(
                            false, new HashSet<>(Arrays.asList("lava", "water"))
                    )
            ),
            new Lists(
                    new List(
                            true, Collections.singleton("plains")
                    ),
                    new List(
                            false, new HashSet<>(Arrays.asList("jungle", "savanna"))
                    )
            ),
            new Lists(
                    new List(
                            true, Collections.singleton("world")
                    ),
                    new List(
                            false, new HashSet<>(Arrays.asList("world_nether", "world_the_end"))
                    )
            ),
            new Hooks(
                    new HookList(
                            false, new HashSet<>(Arrays.asList(HookTypes.values()))
                    ),
                    50,
                    new Lists(
                            new List(
                                    false, Collections.singleton("warzone")
                            ),
                            new List(
                                    true, new HashSet<>(Arrays.asList("spawn", "staff"))
                            )
                    ),
                    new Factions(
                            false, true, true, false, false
                    ),
                    new GriefPrevention(
                            false, false, true
                    )
            ),
            true,
            true
    );

    private static final ListsMapper BLOCKS_MAPPER = new ListsMapper("blocks", DEFAULT.getBlocks());
    private static final ListsMapper BIOMES_MAPPER = new ListsMapper("biomes", DEFAULT.getBiomes());
    private static final ListsMapper WORLDS_MAPPER = new ListsMapper("worlds", DEFAULT.getWorlds());
    private static final HooksMapper HOOKS_MAPPER = new HooksMapper();

    @Override
    public Options dataToType(Map<String, Object> map) {
        return new Options(
                BLOCKS_MAPPER.dataToType(map),
                BIOMES_MAPPER.dataToType(map),
                WORLDS_MAPPER.dataToType(map),
                HOOKS_MAPPER.dataToType(map),
                (boolean) map.getOrDefault("respawn", DEFAULT.isRespawn()),
                (boolean) map.getOrDefault("safe_location", DEFAULT.isSafeLocation())
        );
    }

    @Override
    public Map<String, Object> typeToData(Options options) {
        return Maps.of(new LinkedHashMap<String, Object>())
                .key("blocks").value(BLOCKS_MAPPER.typeToData(options.getBlocks()))
                .key("biomes").value(BIOMES_MAPPER.typeToData(options.getBiomes()))
                .key("worlds").value(WORLDS_MAPPER.typeToData(options.getWorlds()))
                .key("hooks").value(HOOKS_MAPPER.typeToData(options.getHooks()))
                .key("respawn").value(options.isRespawn())
                .key("safe_location").value(options.isSafeLocation())
                .build();
    }
}
