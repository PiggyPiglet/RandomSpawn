package me.piggypiglet.randomspawn.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.commands.Command;
import me.piggypiglet.randomspawn.spawns.Spawn;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import org.bukkit.command.CommandSender;

import java.util.stream.Collectors;

import static me.piggypiglet.randomspawn.file.types.Lang.LIST_FORMAT;
import static me.piggypiglet.randomspawn.file.types.Lang.getMessage;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ListCommand extends Command {
    @Inject private SpawnManager spawnManager;

    public ListCommand() {
        super("list", "Lists all spawns.", "", "randomspawn.default", "randomspawn.list");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage(getMessage(LIST_FORMAT,
                spawnManager.getSpawns().stream().map(Spawn::getName).collect(Collectors.joining(", ")) + "."));

        return true;
    }
}
