package me.piggypiglet.randomspawn.spawning;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import me.piggypiglet.randomspawn.file.FileManager;
import me.piggypiglet.randomspawn.spawns.Spawn;
import me.piggypiglet.randomspawn.spawns.Spawns;
import me.piggypiglet.randomspawn.spawns.implementations.DefaultSpawn;
import me.piggypiglet.randomspawn.spawns.implementations.RadiusSpawn;
import me.piggypiglet.randomspawn.spawns.implementations.SquareSpawn;
import me.piggypiglet.randomspawn.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static me.piggypiglet.randomspawn.utils.SpawnUtils.teleportPlayerToSpawn;
import static me.piggypiglet.randomspawn.utils.SpawnUtils.getRandomSpawn;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class SpawnManager implements Listener {
    @Inject private FileManager fileManager;

    @Getter private final List<Spawn> spawns = new ArrayList<>();
    @Getter private final List<Spawn> enabled = new ArrayList<>();
    @Getter private final List<Spawn> respawnable = new ArrayList<>();

    @Getter private final Map<Spawns, Map<Spawn, List<Location>>> processedLocations = new HashMap<>();

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        FileConfiguration config = fileManager.getConfig("config");

        if (!config.getBoolean("settings.never-on-join", false)) {
            if (config.getBoolean("settings.first-join-only", false)) {
                if (e.getPlayer().hasPlayedBefore()) {
                    return;
                }
            }
        }

        if (enabled.size() > 0) {
            teleportPlayerToSpawn(getRandomSpawn(enabled), e.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
        if (respawnable.size() > 0) {
            e.setRespawnLocation();
            teleportPlayerToSpawn(getRandomSpawn(respawnable), e.getPlayer());
        }
    }
}
