package me.piggypiglet.randomspawn.commands.implementations.edit;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.commands.Command;
import me.piggypiglet.randomspawn.file.types.Data;
import me.piggypiglet.randomspawn.spawns.Spawn;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import me.piggypiglet.randomspawn.spawns.Spawns;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
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
        super("setspawn", "Set a spawn.", "<location name> [radius/x,z] [x,z]", true, "randomspawn.admin", "randomspawn.setspawn");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (args.length >= 1) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            Spawn spawn = null;

            if (location.getWorld() == null) return false;

            if (args.length >= 2) {
                switch (args.length) {
                    case 2:
                        spawn = data.setRadiusSpawn(args[0], location, Integer.parseInt(args[1]));
                        break;

                    case 3:
                        String[] group1 = args[1].split(",");
                        String[] group2 = args[2].split(",");

                        try {
                            spawn = data.setSquareSpawn(args[0], location.getWorld().getName(),
                                    Integer.parseInt(group1[0]),
                                    Integer.parseInt(group1[1]),
                                    Integer.parseInt(group2[0]),
                                    Integer.parseInt(group2[1])
                            );
                        } catch (Exception e) {
                            return false;
                        }

                        break;
                }
            }

            spawn = spawn == null ? data.setSpawn(args[0], location) : spawn;

            if (spawn == null) {
                sender.sendMessage(getMessage(SETSPAWN_ERROR));
            } else {
                spawnManager.getSpawns().add(spawn);
                spawnManager.getEnabled().add(spawn);

                sender.sendMessage(getMessage(SETSPAWN_SUCCESS, spawn.getName(), StringUtils.capitalize(Spawns.getTypeFromSpawn(spawn).toString())));
            }

            return true;
        }

        return false;
    }
}
