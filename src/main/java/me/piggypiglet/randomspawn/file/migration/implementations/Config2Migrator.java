package me.piggypiglet.randomspawn.file.migration.implementations;

import me.piggypiglet.randomspawn.file.migration.Migrator;
import me.piggypiglet.randomspawn.file.migration.Migrators;
import org.bukkit.configuration.file.FileConfiguration;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Config2Migrator extends Migrator {
    public Config2Migrator() {
        super(2,"config", s -> s.getInt("config-version") == 1);
    }

    @Override
    protected FileConfiguration migrate(FileConfiguration config) {
        config.set("config-version", 2);
        config.set("settings.never-on-join", false);
        config.set("settings.safe-locations-in-memory", 10);

        return config;
    }
}
