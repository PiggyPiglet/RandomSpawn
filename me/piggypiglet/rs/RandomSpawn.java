package me.piggypiglet.rs;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class RandomSpawn extends JavaPlugin {
	// ------------------------------
	// Copyright (c) PiggyPiglet 2017
	// https://www.piggypiglet.me
	// ------------------------------
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
        config.addDefault("locations.name.world", "world");
        config.addDefault("locations.name.x", 10);
        config.addDefault("locations.name.y", 100);
        config.addDefault("locations.name.z", 10);
        config.addDefault("locations.name.yaw", 10);
        config.addDefault("locations.name.pitch", 10);
        config.options().copyDefaults(true);
        saveConfig();

        ConfigurationSection section = this.getConfig().getConfigurationSection("locations");

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
        getLogger().info("RandomSpawn v" + getDescription().getVersion() + " enabled.");

        this.getCommand("rs").setExecutor(new RSCommands(this));
        this.getCommand("rssetspawn").setExecutor(new RSCommands(this));
        this.getCommand("rstp").setExecutor(new RSCommands(this));
        this.getCommand("rslist").setExecutor(new RSCommands(this));
        this.getCommand("rsinfo").setExecutor(new RSCommands(this));
        this.getCommand("rsreload").setExecutor(new RSCommands(this));
        getServer().getPluginManager().registerEvents(new RSEvent(this), this);

        loadConfig();
    }
    public void onDisable() {
        getLogger().info("RandomSpawn v" + getDescription().getVersion() + " disabled.");
    }
}
