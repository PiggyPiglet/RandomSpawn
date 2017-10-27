package me.piggypiglet.rs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.stream.Collectors;

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
        FileConfiguration cfg = plugin.getConfig();

        // get all enabled locations
        List<String> locations = plugin.getLocationList().stream().
                filter(s -> !cfg.getBoolean("locations." + s + ".disabled")).
                collect(Collectors.toList());
        if (locations.size() == 0) {

            p.kickPlayer(ChatColor.GRAY + "Error! There are no enabled locations in config. Please contact the server administrator. \nIf you are the admin, enable a location via " + ChatColor.RED + "/rsenablespawn [location name]" + ChatColor.GRAY + " or manually enabling one in config.");
        } else if (locations.size() > 0) {
            String randomString = plugin.getRandomStringFromList(locations);
            String cfgPrefix = "locations." + randomString + ".";
            p.teleport(new Location(
                    Bukkit.getWorld(cfg.getString(cfgPrefix + "world")),
                    cfg.getDouble(cfgPrefix + "x"),
                    cfg.getDouble(cfgPrefix + "y"),
                    cfg.getDouble(cfgPrefix + "z"),
                    cfg.getInt(cfgPrefix + "yaw"),
                    cfg.getInt(cfgPrefix + "pitch")));
        }
    }
}






