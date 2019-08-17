package me.piggypiglet.randomspawn.commands.implementations.edit;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.commands.Command;
import me.piggypiglet.randomspawn.file.types.Data;
import me.piggypiglet.randomspawn.spawns.Spawn;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import me.piggypiglet.randomspawn.utils.SpawnUtils;
import org.bukkit.command.CommandSender;

import java.util.Optional;

import static me.piggypiglet.randomspawn.file.types.Lang.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class DisableRespawnCommand extends Command {
    @Inject private SpawnManager spawnManager;
    @Inject private Data data;

    public DisableRespawnCommand() {
        super("disablerespawn", "Disable respawning on a spawn.", "<location name>", "randomspawn.admin", "randomspawn.disablerespawn");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            Optional<Spawn> opSpawn = SpawnUtils.getSpawnByName(args[0]);

            if (opSpawn.isPresent()) {
                Spawn spawn = opSpawn.get();

                spawnManager.getSpawns().remove(spawn);
                spawnManager.getRespawnable().remove(spawn);

//                spawn.setRespawn(false);

                data.editSpawn(spawn);
                spawnManager.getSpawns().add(spawn);

                sender.sendMessage(getMessage(DISABLERESPAWN_SUCCESS, args[0]));
            } else {
                sender.sendMessage(getMessage(UNKNOWN_SPAWN, args[0]));
            }

            return true;
        }

        return false;
    }
}
