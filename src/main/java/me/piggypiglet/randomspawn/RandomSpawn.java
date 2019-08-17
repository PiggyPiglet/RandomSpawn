package me.piggypiglet.randomspawn;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.piggypiglet.randomspawn.commands.CommandManager;
import me.piggypiglet.randomspawn.commands.Commands;
import me.piggypiglet.randomspawn.file.FileManager;
import me.piggypiglet.randomspawn.file.MigrationManager;
import me.piggypiglet.randomspawn.file.migration.Migrators;
import me.piggypiglet.randomspawn.file.types.Lang;
import me.piggypiglet.randomspawn.file.types.Data;
import me.piggypiglet.randomspawn.spawns.Spawn;
import me.piggypiglet.randomspawn.guice.BinderModule;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import me.piggypiglet.randomspawn.spawns.Spawns;
import me.piggypiglet.randomspawn.spawns.implementations.RadiusSpawn;
import me.piggypiglet.randomspawn.tasks.Task;
import me.piggypiglet.randomspawn.utils.MathUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static me.piggypiglet.randomspawn.RandomSpawn.Registerables.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RandomSpawn extends JavaPlugin {
    @Inject private FileManager fileManager;
    @Inject private MigrationManager migrationManager;
    @Inject private CommandManager commandManager;
    @Inject private SpawnManager spawnManager;

    @Inject private Data data;

    private Injector injector;

    @Override
    public void onEnable() {
        Stream.of(GUICE, FILES, SPAWNS, COMMANDS, EVENTS).forEach(this::register);
    }

    @Override
    public void onDisable() {
        fileManager.save("config");
    }

    public void register(Registerables registerable) {
        switch (registerable) {
            case GUICE:
                injector = new BinderModule(this).createInjector();
                injector.injectMembers(this);
                break;

            case FILES:
                fileManager.clear();

                Stream.of("config", "lang").forEach(s -> {
                    try {
                        fileManager.copy(s, getDataFolder().getPath() + "/" + s + ".yml", "/" + s + ".yml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                migrationManager.addMigrators(Arrays.stream(Migrators.values()).filter(m -> m != Migrators.UNKNOWN).map(Migrators::getClazz).map(injector::getInstance).collect(Collectors.toList()));
                migrationManager.migrate();
                break;

            case SPAWNS:
                spawnManager.getSpawns().clear();
                spawnManager.getEnabled().clear();
                spawnManager.getRespawnable().clear();

                List<Spawn> spawns = data.getAllSpawns();

                if (!spawns.isEmpty()) {
                    spawnManager.getSpawns().addAll(spawns);
                    spawnManager.getEnabled().addAll(spawns.stream().filter(Spawn::isEnabled).collect(Collectors.toList()));
                    spawnManager.getRespawnable().addAll(spawnManager.getEnabled().stream().filter(Spawn::isRespawn).collect(Collectors.toList()));

                    FileConfiguration config = fileManager.getConfig("config");
                    int maxLocations = config.getInt("settings.safe-locations-in-memory", 10);

                    /*Task.async(r -> */spawnManager.getSpawns().forEach(s -> {
                        Spawns type = Spawns.getTypeFromSpawn(s);

                        switch (type) {
                            case RADIUS:

                                break;

                            case SQUARE:
                                break;
                        }
                    })/*)*/;
                }

                getLogger().info(Lang.getMessage(Lang.FOUND_SPAWNS, spawnManager.getSpawns().size(), spawnManager.getEnabled().size()));
                break;

            case COMMANDS:
                Objects.requireNonNull(getCommand("randomspawn"))
                        .setExecutor(commandManager);
                Arrays.stream(Commands.values()).map(Commands::getClazz).map(injector::getInstance).forEach(commandManager.getCommands()::add);
                break;

            case EVENTS:
                getServer().getPluginManager().registerEvents(spawnManager, this);
                break;
        }
    }

    public enum Registerables {
        GUICE, FILES, COMMANDS, SPAWNS, EVENTS
    }
}
