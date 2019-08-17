package me.piggypiglet.randomspawn.file.migration;

import lombok.Getter;
import me.piggypiglet.randomspawn.file.migration.implementations.Config1Migrator;
import me.piggypiglet.randomspawn.file.migration.implementations.Config2Migrator;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Migrators {
    CONFIG_1(Config1Migrator.class, Categories.CONFIG),
    CONFIG_2(Config2Migrator.class, Categories.CONFIG),
    UNKNOWN(null, null);

    @Getter private final Class<? extends Migrator> clazz;
    @Getter private final Categories category;

    Migrators(final Class<? extends Migrator> clazz, Categories category) {
        this.clazz = clazz;
        this.category = category;
    }

    public static Migrators getMigrator(Class<? extends Migrator> migrator) {
        for (Migrators m : values()) {
            if (m.clazz == migrator) {
                return m;
            }
        }

        return UNKNOWN;
    }

    public enum Categories {
        CONFIG
    }
}
