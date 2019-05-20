package me.piggypiglet.randomspawn;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.piggypiglet.randomspawn.commands.CommandManager;
import me.piggypiglet.randomspawn.commands.Commands;
import me.piggypiglet.randomspawn.file.FileManager;
import me.piggypiglet.randomspawn.file.types.data.Data;
import me.piggypiglet.randomspawn.file.types.data.Spawn;
import me.piggypiglet.randomspawn.guice.BinderModule;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static me.piggypiglet.randomspawn.RandomSpawn.Registerables.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RandomSpawn extends JavaPlugin {
    @Inject private FileManager fileManager;
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

    }

    private void register(Registerables registerable) {
        switch (registerable) {
            case GUICE:
                injector = new BinderModule(this).createInjector();
                injector.injectMembers(this);
                break;

            case FILES:
                Stream.of("data", "lang").forEach(s -> {
                    try {
                        fileManager.copy(s, getDataFolder().getPath() + "/" + s + ".yml", "/" + s + ".yml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                break;

            case SPAWNS:
                spawnManager.getSpawns().clear();
                spawnManager.getEnabled().clear();

                List<Spawn> spawns = data.getAllSpawns();

                if (spawns != null) {
                    spawnManager.getSpawns().addAll(spawns);
                    spawnManager.getEnabled().addAll(spawns.stream().filter(Spawn::isEnabled).collect(Collectors.toList()));
                }
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

    enum Registerables {
        GUICE, FILES, COMMANDS, SPAWNS, EVENTS
    }
}
