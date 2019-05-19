package me.piggypiglet.randomspawn;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.piggypiglet.randomspawn.commands.CommandManager;
import me.piggypiglet.randomspawn.commands.Commands;
import me.piggypiglet.randomspawn.file.FileManager;
import me.piggypiglet.randomspawn.guice.BinderModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static me.piggypiglet.randomspawn.RandomSpawn.Registerables.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RandomSpawn extends JavaPlugin {
    @Inject private FileManager fileManager;
    @Inject private CommandManager commandManager;

    private Injector injector;

    @Override
    public void onEnable() {
        Stream.of(GUICE, FILES, COMMANDS).forEach(this::register);
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
                Stream.of("config", "lang").forEach(s -> {
                    try {
                        fileManager.copy(s, getDataFolder().getPath() + "/" + s + ".yml", "/" + s + ".yml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                break;

            case COMMANDS:
                Objects.requireNonNull(getCommand("randomspawn"))
                        .setExecutor(commandManager);
                Arrays.stream(Commands.values()).map(Commands::getClazz).map(injector::getInstance).forEach(commandManager.getCommands()::add);
                break;
        }
    }

    enum Registerables {
        GUICE, FILES, COMMANDS
    }
}
