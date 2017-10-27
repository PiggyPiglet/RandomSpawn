package me.piggypiglet.rs.Events;

import me.piggypiglet.rs.Enums.Messages;
import me.piggypiglet.rs.Handlers.ChatHandler;
import me.piggypiglet.rs.Handlers.ConfigHandler;
import me.piggypiglet.rs.RandomSpawn;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static me.piggypiglet.rs.Enums.Messages.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2017
// https://www.piggypiglet.me
// ------------------------------
public class RSEvent implements Listener {
    private RandomSpawn plugin;
    private ChatHandler chat;
    private ConfigHandler config;
    private HashMap<Player, ItemStack[]> items = new HashMap<>();

    public RSEvent() {
        plugin = RandomSpawn.getInstance();
        chat = new ChatHandler();
        config = new ConfigHandler();
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        FileConfiguration cfg = plugin.getConfig();

        List<String> locations = plugin.getLocationList().stream().
                filter(s -> !cfg.getBoolean("data.locations." + s + ".disabled")).
                collect(Collectors.toList());
        if (locations.size() == 0) {

            p.kickPlayer(ChatColor.GRAY + "Error! There are no enabled locations in config. Please contact the server administrator. \nIf you are the admin, enable a location via " + ChatColor.RED + "/rsenablespawn [location name]" + ChatColor.GRAY + " or manually enabling one in config.");
        } else if (locations.size() > 0) {
            String randomString = plugin.getRandomStringFromList(locations);
            String cfgPrefix = "data.locations." + randomString + ".";
            p.teleport(new Location(
                    Bukkit.getWorld(cfg.getString(cfgPrefix + "world")),
                    cfg.getDouble(cfgPrefix + "x"),
                    cfg.getDouble(cfgPrefix + "y"),
                    cfg.getDouble(cfgPrefix + "z"),
                    cfg.getInt(cfgPrefix + "yaw"),
                    cfg.getInt(cfgPrefix + "pitch")));
        }
    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (items.containsKey(p)) {
            p.getInventory().clear();
            for (ItemStack stack : items.get(p)) {
                p.getInventory().addItem(stack);
            }
            items.remove(p);
        }
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity();
        p.setHealth(20);
        ItemStack[] inventory = p.getInventory().getContents();
        items.put(p, inventory);
        p.getInventory().clear();
        FileConfiguration cfg = plugin.getConfig();

        List<String> locations = plugin.getLocationList().stream().
                filter(s -> cfg.getBoolean("data.locations." + s + ".respawn")).
                collect(Collectors.toList());
        if (locations.size() == 0) {
            chat.send(p, config.getMessages(RESPAWN));
        } else if (locations.size() > 0) {
            String randomString = plugin.getRandomStringFromList(locations);
            p.teleport(new Location(
                    Bukkit.getWorld(cfg.getString("data.locations." + randomString + ".world")),
                    cfg.getDouble("data.locations." + randomString + ".x"),
                    cfg.getDouble("data.locations." + randomString + ".y"),
                    cfg.getDouble("data.locations." + randomString + ".z"),
                    cfg.getInt("data.locations." + randomString + ".yaw"),
                    cfg.getInt("data.locations." + randomString + ".pitch")));
        }
    }
}






