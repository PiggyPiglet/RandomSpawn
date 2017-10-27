package me.piggypiglet.randomspawn.handlers;

import me.piggypiglet.randomspawn.Config;
import me.piggypiglet.randomspawn.RandomSpawn;
import me.piggypiglet.randomspawn.enums.Messages;
import org.bukkit.configuration.file.FileConfiguration;

// ------------------------------
// Copyright (c) PiggyPiglet 2017
// https://www.piggypiglet.me
// ------------------------------
public class ConfigHandler {
    private Config config;

    public ConfigHandler() {
        RandomSpawn main = RandomSpawn.getInstance();
        config = new Config(main.getDataFolder().getPath(), "lang.yml");
    }
    public String getMessages(Messages msg) {
        final FileConfiguration cfg = config.getConfig();
        return cfg.getString(msg.toString().toLowerCase()) + "&r";
    }
}
