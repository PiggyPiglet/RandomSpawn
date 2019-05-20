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
public final class DisableSpawnCommand extends Command {
    @Inject private SpawnManager spawnManager;
    @Inject private Data data;

    public DisableSpawnCommand() {
        super("disablespawn", "Disable a spawn.", "<location name>", "randomspawn.admin", "randomspawn.disable");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            Optional<Spawn> opSpawn = spawnManager.getSpawnByName(args[0]);

            if (opSpawn.isPresent()) {
                Spawn spawn = opSpawn.get().toBuilder().enabled(false).build();

                data.editSpawn(spawn);
                spawnManager.getSpawns().add(spawn);
                spawnManager.getEnabled().remove(spawn);

                sender.sendMessage(getMessage(DISABLESPAWN_SUCCESS, args[0]));
            } else {
                sender.sendMessage(getMessage(UNKNOWN_SPAWN, args[0]));
            }

            return true;
        }

        return false;
    }
}
