package me.piggypiglet.randomspawn.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.commands.Command;
import me.piggypiglet.randomspawn.file.types.Lang;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import me.piggypiglet.randomspawn.utils.SpawnUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SpawnCommand extends Command {
    @Inject private SpawnManager spawnManager;

    public SpawnCommand() {
        super("spawn", "Go to one of the enabled spawns.", "", true, "randomspawn.default", "randomspawn.spawn");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
//        SpawnUtils.teleportPlayerToSpawn(SpawnUtils.getRandomSpawn(spawnManager.getEnabled()), (Player) sender);
//        sender.sendMessage(Lang.getMessage(Lang.SPAWN_SUCCESS));

        return true;
    }
}
