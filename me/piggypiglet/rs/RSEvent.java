package me.piggypiglet.rs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RSEvent implements Listener {
	// ------------------------------
	// Copyright (c) PiggyPiglet 2017
	// https://www.piggypiglet.me
	// ------------------------------
    RandomSpawn plugin;

    public RSEvent(RandomSpawn instance) {
        plugin = instance;
    }
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        String randomString = plugin.getRandomStringFromList(plugin.getLocationList());

        p.teleport(new Location(Bukkit.getWorld(String.valueOf(plugin.getConfig().getString("locations." + randomString + ".world"))),
            plugin.getConfig().getDouble("locations." + randomString + ".x"),
            plugin.getConfig().getDouble("locations." + randomString + ".y"),
            plugin.getConfig().getDouble("locations." + randomString + ".z"),
            plugin.getConfig().getInt("locations." + randomString + ".yaw"),
            plugin.getConfig().getInt("locations." + randomString + ".pitch")));
    }
}






