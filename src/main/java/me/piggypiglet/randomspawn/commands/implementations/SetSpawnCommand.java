package me.piggypiglet.randomspawn.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.commands.Command;
import me.piggypiglet.randomspawn.file.types.data.Data;
import me.piggypiglet.randomspawn.file.types.data.Spawn;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.piggypiglet.randomspawn.file.types.Lang.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SetSpawnCommand extends Command {
    @Inject private SpawnManager spawnManager;
    @Inject private Data data;

    public SetSpawnCommand() {
        super("setspawn", "Set a spawn.", "[location name]", true, "randomspawn.admin", "randomspawn.setspawn");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (args.length >= 1) {
            Spawn spawn = data.setSpawn(args[0], ((Player) sender).getLocation());

            if (spawn == null) {
                sender.sendMessage(getMessage(SETSPAWN_ERROR));
            } else {
                spawnManager.getSpawns().add(spawn);
                spawnManager.getEnabled().add(spawn);

                sender.sendMessage(getMessage(SETSPAWN_SUCCESS, spawn.getName(),
                        Math.round(spawn.getX()),
                        Math.round(spawn.getY()),
                        Math.round(spawn.getZ()),
                        Math.round(spawn.getYaw()),
                        Math.round(spawn.getPitch())
                ));
            }

            return true;
        }

        return false;
    }
}
