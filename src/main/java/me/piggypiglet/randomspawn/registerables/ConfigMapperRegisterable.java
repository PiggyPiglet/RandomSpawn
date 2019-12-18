package me.piggypiglet.randomspawn.registerables;

import com.google.inject.Inject;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.registerables.StartupRegisterable;
import me.piggypiglet.framework.utils.annotations.files.Config;
import me.piggypiglet.randomspawn.utils.data.DataUtils;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ConfigMapperRegisterable extends StartupRegisterable {
    @Inject @Config private FileConfiguration config;

    @Override
    protected void execute() {
        addBinding(DataUtils.constructConfig(config));
    }
}
