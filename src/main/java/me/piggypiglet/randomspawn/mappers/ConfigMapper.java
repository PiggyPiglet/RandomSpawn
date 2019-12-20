package me.piggypiglet.randomspawn.mappers;

import me.piggypiglet.framework.file.framework.AbstractFileConfiguration;
import me.piggypiglet.framework.file.framework.MutableFileConfiguration;
import me.piggypiglet.framework.mapper.ObjectMapper;
import me.piggypiglet.randomspawn.data.Config;
import me.piggypiglet.randomspawn.data.options.Options;
import me.piggypiglet.randomspawn.mappers.options.OptionsMapper;
import me.piggypiglet.randomspawn.mappers.spawns.SpawnsMapper;

import java.util.HashMap;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ConfigMapper implements ObjectMapper<MutableFileConfiguration, Config> {
    private static final OptionsMapper OPTIONS_MAPPER = new OptionsMapper();
    private static final SpawnsMapper SPAWNS_MAPPER = new SpawnsMapper();

    private final MutableFileConfiguration config;

    public ConfigMapper(MutableFileConfiguration config) {
        this.config = config;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Config dataToType(MutableFileConfiguration config) {
        final Options options = OPTIONS_MAPPER.dataToType(((AbstractFileConfiguration) config.getConfigSection("options")).getAll());
        OptionsMapper.DEFAULT = options;

        return new Config(
                options,
                SPAWNS_MAPPER.dataToType((Map<String, Map<String, Object>>) config.get("data", new HashMap<>()))
        );
    }

    @Override
    public MutableFileConfiguration typeToData(Config config) {
        this.config.set("options", OPTIONS_MAPPER.typeToData(config.getOptions()));
        this.config.set("data", SPAWNS_MAPPER.typeToData(config.getSpawns()));
        return this.config;
    }
}
