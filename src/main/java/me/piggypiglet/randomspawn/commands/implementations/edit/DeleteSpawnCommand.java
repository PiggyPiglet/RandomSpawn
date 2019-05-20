package me.piggypiglet.randomspawn.commands.implementations.edit;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.commands.Command;
import me.piggypiglet.randomspawn.file.types.data.Data;
import me.piggypiglet.randomspawn.file.types.data.Spawn;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import org.bukkit.command.CommandSender;

import java.util.Optional;

import static me.piggypiglet.randomspawn.file.types.Lang.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class DeleteSpawnCommand extends Command {
    @Inject private SpawnManager spawnManager;
    @Inject private Data data;

    public DeleteSpawnCommand() {
        super("deletespawn", "Delete a spawn.", "<location name>", "randomspawn.admin", "randomspawn.deletespawn");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            Optional<Spawn> opSpawn = spawnManager.getSpawnByName(args[0]);

            if (opSpawn.isPresent()) {
                Spawn spawn = opSpawn.get();

                data.deleteSpawn(spawn);
                spawnManager.getSpawns().remove(spawn);
                spawnManager.getEnabled().remove(spawn);
                spawnManager.getRespawnable().remove(spawn);

                sender.sendMessage(getMessage(DELETESPAWN_SUCCESS, args[0]));
            } else {
                sender.sendMessage(getMessage(UNKNOWN_SPAWN, args[0]));
            }

            return true;
        }

        return false;
    }
}
