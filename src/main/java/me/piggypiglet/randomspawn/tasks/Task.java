package me.piggypiglet.randomspawn.tasks;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.RandomSpawn;
import org.bukkit.Bukkit;

import java.util.function.Consumer;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Task {
    @Inject private static RandomSpawn randomSpawn;

    public static void async(Consumer<Runnable> task) {
        Bukkit.getScheduler().runTaskAsynchronously(randomSpawn, new Runnable() {
            @Override
            public void run() {
                task.accept(this);
            }
        });
    }
}
