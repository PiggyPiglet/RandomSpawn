package me.piggypiglet.rs;

import me.piggypiglet.rs.Commands.RSCommands;
import me.piggypiglet.rs.Events.RSEvent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2017
// https://www.piggypiglet.me
// ------------------------------
public class RandomSpawn extends JavaPlugin {
    private static RandomSpawn myinstance;

    public static RandomSpawn getInstance() {
        return myinstance;
    }
    public String getRandomStringFromList(List<String> list) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int randomNumber = random.nextInt(list.size());
        String randomString = list.get(randomNumber);

        return randomString;
    }
    private List<String> locationList;

    public List<String> getLocationList() {
        if (this.locationList == null)
            this.locationList = new ArrayList<>();
            this.locationList = locationList.stream().distinct().collect(Collectors.toList());
        return this.locationList;
    }

    public void loadConfig() {
        final FileConfiguration config = this.getConfig();
        config.addDefault("settings.chat.PREFIX", "&7[&cRS&7] ");
        config.addDefault("settings.chat.NOPERM", "&7No permission for that command.");
        config.addDefault("settings.chat.UNKNOWNSUB", "&7Unknown subcommand.");
        config.addDefault("settings.chat.ONLYPLAYER", "&7This command can only be executed by a player!");
        config.addDefault("settings.chat.DOESNTEXIST", "&7The location you specified does not exist.");
        config.addDefault("settings.chat.TOOMUCHARGS", "&7Too many arguments!");
        config.addDefault("settings.chat.TOOMUCHARGS", "&7There are no locations defined in config for respawning! You will respawn at the default location.");
        config.addDefault("data.locations.name.world", "world");
        config.addDefault("data.locations.name.x", 10);
        config.addDefault("data.locations.name.y", 100);
        config.addDefault("data.locations.name.z", 10);
        config.addDefault("data.locations.name.yaw", 10);
        config.addDefault("data.locations.name.pitch", 10);
        config.addDefault("data.locations.name.respawn", true);
        config.addDefault("data.locations.name.disabled", false);
        config.options().copyDefaults(true);
        saveConfig();

        ConfigurationSection section = this.getConfig().getConfigurationSection("data.locations");

        if (section != null) {
            Set<String> locations = section.getKeys(false);

            if (locations != null && !locations.isEmpty()) {
                for (String location : locations) {
                    getLocationList().add(location);
                }
            }
        }
    }
    @Override
    public void onEnable() {
        myinstance = this;
        getLogger().info("RandomSpawn v" + getDescription().getVersion() + " enabled.");

        this.getCommand("rs").setExecutor(new RSCommands());
        getServer().getPluginManager().registerEvents(new RSEvent(), this);

        loadConfig();
    }
    @Override
    public void onDisable() {
        getLogger().info("RandomSpawn v" + getDescription().getVersion() + " disabled.");
        myinstance = null;
    }
}
