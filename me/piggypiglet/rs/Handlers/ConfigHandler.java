package me.piggypiglet.rs.Handlers;

import me.piggypiglet.rs.Enums.Messages;
import me.piggypiglet.rs.RandomSpawn;
import org.bukkit.configuration.file.FileConfiguration;

// ------------------------------
// Copyright (c) PiggyPiglet 2017
// https://www.piggypiglet.me
// ------------------------------
public class ConfigHandler {
    private RandomSpawn plugin;

    public ConfigHandler() {
        plugin = RandomSpawn.getInstance();
    }

    public String getMessages(Messages m) {
        FileConfiguration cfg = plugin.getConfig();
        return cfg.getString("settings.chat." + m.toString());
    }
}
