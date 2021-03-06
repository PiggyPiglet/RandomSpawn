package me.piggypiglet.randomspawn.registerables;

import com.google.inject.Inject;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.file.framework.MutableFileConfiguration;
import me.piggypiglet.framework.registerables.StartupRegisterable;
import me.piggypiglet.randomspawn.data.Data;
import me.piggypiglet.randomspawn.mappers.ConfigMapper;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ConfigMapperRegisterable extends StartupRegisterable {
    private final ConfigMapper configMapper;
    private final MutableFileConfiguration config;

    @Inject
    public ConfigMapperRegisterable(@Data FileConfiguration config) {
        configMapper = new ConfigMapper((MutableFileConfiguration) config);
        this.config = (MutableFileConfiguration) config;
    }

    @Override
    protected void execute() {
        addBinding(configMapper.dataToType(config));
    }
}
