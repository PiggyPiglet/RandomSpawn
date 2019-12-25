package me.piggypiglet.randomspawn.events;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.managers.SpawnManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class JoinEvent implements Listener {
    @Inject private SpawnManager spawnManager;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        final Location location = e.getPlayer().getLocation();
    }
}
