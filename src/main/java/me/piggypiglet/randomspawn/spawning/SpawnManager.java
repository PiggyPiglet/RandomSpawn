package me.piggypiglet.randomspawn.spawning;

import com.google.inject.Singleton;
import lombok.Getter;
import me.piggypiglet.randomspawn.file.types.data.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class SpawnManager implements Listener {
    @Getter private final List<Spawn> spawns = new ArrayList<>();
    @Getter private final List<Spawn> enabled = new ArrayList<>();

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        if (enabled.size() > 0) {
            teleportPlayerToSpawn(e.getPlayer(), enabled.get(ThreadLocalRandom.current().nextInt(enabled.size())));
        }
    }

    private void teleportPlayerToSpawn(Player player, Spawn spawn) {
        player.teleport(new Location(
                Bukkit.getWorld(spawn.getWorld()),
                spawn.getX(),
                spawn.getY(),
                spawn.getZ(),
                spawn.getYaw(),
                spawn.getPitch()
        ));
    }
}
