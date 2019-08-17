package me.piggypiglet.randomspawn.file;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.randomspawn.RandomSpawn;
import me.piggypiglet.randomspawn.file.migration.Migrator;
import me.piggypiglet.randomspawn.file.migration.Migrators;
import me.piggypiglet.randomspawn.file.types.Lang;
import me.piggypiglet.randomspawn.tasks.Task;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class MigrationManager {
    @Inject private FileManager fileManager;
    @Inject private RandomSpawn randomSpawn;

    private final Map<Migrators.Categories, List<Migrator>> migrators = new HashMap<>();

    public void migrate() {
        Set<String> needsSaving = new HashSet<>();

        migrators.forEach((c, ms) -> {
            String str = c.toString().toLowerCase();
            final FileConfiguration config = fileManager.getConfig(str);

            ms.forEach(m -> {
                if (m.canMigrate(config)) {
                    FileConfiguration updated = m.run(config);

                    if (updated != null) {
                        fileManager.set(str, updated);
                        needsSaving.add(str);
                    }
                }
            });
        });

        needsSaving.forEach(this::saveAndLog);
    }

    private void saveAndLog(String str) {
        Task.async(r -> fileManager.save(str));
        randomSpawn.getLogger().info(Lang.getMessage(Lang.MIGRATING_CONFIGS, str));
    }

    @SuppressWarnings("unchecked")
    public void addMigrators(List<Migrator> migrators) {
        Arrays.stream(Migrators.Categories.values()).forEach(c -> this.migrators.put(c, new ArrayList<>()));
        migrators.forEach(m -> this.migrators.get(Migrators.getMigrator(m.getClass()).getCategory()).add(m));

        // Uses compareTo in Migrator.java to order by priority
        this.migrators.values().forEach(Collections::sort);
    }
}
