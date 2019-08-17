package me.piggypiglet.randomspawn.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.commands.Command;
import me.piggypiglet.randomspawn.spawns.Spawn;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import me.piggypiglet.randomspawn.spawns.Spawns;
import me.piggypiglet.randomspawn.utils.SpawnUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.Optional;

import static me.piggypiglet.randomspawn.file.types.Lang.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class InfoCommand extends Command {
    @Inject private SpawnManager spawnManager;

    public InfoCommand() {
        super("info", "Get information on a certain spawn", "<location name>", "randomspawn.default", "randomspawn.info");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            Optional<Spawn> opSpawn = SpawnUtils.getSpawnByName(args[0]);

            if (opSpawn.isPresent()) {
                Spawn spawn = opSpawn.get();

                sender.sendMessage(getMessage(INFO_FORMAT,
                        spawn.getName(),
                        spawn.getWorld(),
                        StringUtils.capitalize(Spawns.getTypeFromSpawn(spawn).toString()),
                        spawn.isEnabled(),
                        spawn.isRespawn()
                ));
            } else {
                sender.sendMessage(getMessage(UNKNOWN_SPAWN, args[0]));
            }

            return true;
        }

        return false;
    }
}
