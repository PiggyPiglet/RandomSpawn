package me.piggypiglet.randomspawn.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.RandomSpawn;
import me.piggypiglet.randomspawn.commands.Command;
import me.piggypiglet.randomspawn.file.FileManager;
import me.piggypiglet.randomspawn.file.types.Lang;
import org.bukkit.command.CommandSender;

import java.util.stream.Stream;

import static me.piggypiglet.randomspawn.RandomSpawn.Registerables.FILES;
import static me.piggypiglet.randomspawn.RandomSpawn.Registerables.SPAWNS;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ReloadCommand extends Command {
    @Inject private FileManager fileManager;
    @Inject private RandomSpawn randomSpawn;

    public ReloadCommand() {
        super("reload", "Reloads the lang file.", "", "randomspawn.admin", "randomspawn.reload");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        fileManager.save("config");
        Stream.of(FILES, SPAWNS).forEach(randomSpawn::register);
        sender.sendMessage(Lang.getMessage(Lang.RELOAD_SUCCESS));

        return true;
    }
}
