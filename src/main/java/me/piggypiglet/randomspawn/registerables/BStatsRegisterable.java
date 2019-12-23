package me.piggypiglet.randomspawn.registerables;

import com.google.inject.Inject;
import me.piggypiglet.framework.registerables.StartupRegisterable;
import me.piggypiglet.framework.task.Task;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class BStatsRegisterable extends StartupRegisterable {
    @Inject private JavaPlugin main;
    @Inject private Task task;

    @Override
    protected void execute() {
        task.async(r -> new Metrics(main));
    }
}
