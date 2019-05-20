package me.piggypiglet.randomspawn.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.commands.Command;
import me.piggypiglet.randomspawn.file.types.data.Spawn;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

import static me.piggypiglet.randomspawn.file.types.Lang.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class TeleportCommand extends Command {
    @Inject private SpawnManager spawnManager;

    public TeleportCommand() {
        super("tp", "Teleport to a spawn.", "<location name>", true, "randomspawn.admin", "randomspawn.teleport");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            Optional<Spawn> opSpawn = spawnManager.getSpawnByName(args[0]);

            if (opSpawn.isPresent()) {
                spawnManager.teleportPlayerToSpawn((Player) sender, opSpawn.get());
                sender.sendMessage(getMessage(TELEPORT_SUCCESS, args[0]));
            } else {
                sender.sendMessage(getMessage(UNKNOWN_SPAWN, args[0]));
            }

            return true;
        }

        return false;
    }
}