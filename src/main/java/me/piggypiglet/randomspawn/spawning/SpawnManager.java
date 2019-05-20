package me.piggypiglet.randomspawn.spawning;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import me.piggypiglet.randomspawn.file.FileManager;
import me.piggypiglet.randomspawn.file.types.data.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

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

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        if (fileManager.getConfig("config").getBoolean("settings.first-join-only", false)) {
            if (e.getPlayer().hasPlayedBefore()) {
                return;
            }
        }

        if (enabled.size() > 0) {
            teleportPlayerToSpawn(e.getPlayer(), getRandomSpawn(enabled));
        }
    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
        if (respawnable.size() > 0) {
            teleportPlayerToSpawn(e.getPlayer(), getRandomSpawn(respawnable));
        }
    }

    public Optional<Spawn> getSpawnByName(String name) {
        return getSpawns().stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst();
    }

    public void teleportPlayerToSpawn(Player player, Spawn spawn) {
        player.teleport(new Location(
                Bukkit.getWorld(spawn.getWorld()),
                spawn.getX(),
                spawn.getY(),
                spawn.getZ(),
                spawn.getYaw(),
                spawn.getPitch()
        ));
    }

    private Spawn getRandomSpawn(List<Spawn> spawns) {
        return spawns.get(ThreadLocalRandom.current().nextInt(spawns.size()));
    }
}
