package me.piggypiglet.randomspawn.file.migration;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.function.Predicate;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Migrator implements Comparable {
    @Getter private String file;
    @Getter private final int priority;

    private Predicate<FileConfiguration> requirement;

    protected Migrator(String file, Predicate<FileConfiguration> requirement) {
        this(0, file, requirement);
    }

    protected Migrator(int priority, String file, Predicate<FileConfiguration> requirement) {
        this.priority = priority;
        this.file = file;
        this.requirement = requirement;
    }

    protected abstract FileConfiguration migrate(FileConfiguration config);

    public boolean canMigrate(FileConfiguration config) {
        return requirement.test(config);
    }

    public FileConfiguration run(FileConfiguration config) {
        FileConfiguration copyConfig = new YamlConfiguration();

        try {
            copyConfig.loadFromString(config.saveToString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return migrate(copyConfig);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Migrator)) return 0;

        return priority - ((Migrator) o).getPriority();
    }
}
