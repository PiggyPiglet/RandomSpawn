package me.piggypiglet.randomspawn;

import me.piggypiglet.framework.Framework;
import me.piggypiglet.framework.utils.annotations.files.Config;
import me.piggypiglet.framework.utils.annotations.registerable.RegisterableData;
import me.piggypiglet.randomspawn.registerables.ConfigMapperRegisterable;
import org.bukkit.plugin.java.JavaPlugin;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RandomSpawn extends JavaPlugin {
    @Override
    public void onEnable() {
        Framework.builder()
                .main(JavaPlugin.class, this)
                .pckg("me.piggypiglet.randomspawn")
                .commandPrefix("randomspawn")
                .fileDir(getDataFolder().getPath())
                .file(true, "config", "/config.yml", "config.yml", Config.class)
                .startup(new RegisterableData(ConfigMapperRegisterable.class))
                .build()
                .init();
    }
}
