package me.piggypiglet.randomspawn.commands.implementations;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.commands.Command;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import me.piggypiglet.randomspawn.spawns.implementations.RadiusSpawn;
import me.piggypiglet.randomspawn.tasks.Task;
import me.piggypiglet.randomspawn.utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class TestCommand extends Command {
    @Inject private SpawnManager spawnManager;

    public TestCommand() {
        super("test", "test", "test");
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        RadiusSpawn spawn = (RadiusSpawn) spawnManager.getSpawns().stream().filter(s -> s instanceof RadiusSpawn).findFirst().get();

//        List<String> coords = MathUtils.coordinateCircle(new double[]{spawn.getX(), spawn.getZ()}, spawn.getRadius());
//
//        Task.async(r -> {
//            MathUtils.coordinateCircle(new double[]{spawn.getX(), spawn.getZ()}, spawn.getRadius()).forEach(g -> {
//                String[] split = g.replace("[", "").replace("]", "").replace(" ", "").split(",");
//                System.out.println(Bukkit.getWorld("world").getHighestBlockAt(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
//            });
//        });

        return true;
    }
}
