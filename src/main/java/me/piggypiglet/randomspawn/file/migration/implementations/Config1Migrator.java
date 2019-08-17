package me.piggypiglet.randomspawn.file.migration.implementations;

import me.piggypiglet.randomspawn.file.migration.Migrator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Config1Migrator extends Migrator {
    public Config1Migrator() {
        super(1, "config", s -> !s.contains("config-version"));
    }

    @Override
    protected FileConfiguration migrate(FileConfiguration config) {
        config.set("config-version", 1);
        config.set("settings", null);
        config.set("settings.first-join-only", false);

        ConfigurationSection section = config.getConfigurationSection("data.locations");

        if (section != null) {
            section.getKeys(false).forEach(k -> {
                String disabled = k + ".disabled";

                if (section.contains(disabled)) {
                    section.set(disabled, null);
                    section.set(k + ".enabled", !section.getBoolean(disabled));
                }
            });
        }

        return config;
    }
}