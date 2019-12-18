package me.piggypiglet.randomspawn;

import me.piggypiglet.framework.Framework;
import me.piggypiglet.framework.file.objects.FileData;
import me.piggypiglet.framework.utils.annotations.files.Config;
import me.piggypiglet.framework.utils.annotations.files.Lang;
import me.piggypiglet.framework.utils.annotations.registerable.RegisterableData;
import me.piggypiglet.randomspawn.data.Data;
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
                .file(true, "data", "/data.yml", "data.yml", Data.class)
                .file(true, "lang", new FileData.ConfigPathReference("config", "language", "en", s -> "lang_" + s + ".yml"), "lang.yml", Lang.class)
                .customLang("lang", me.piggypiglet.randomspawn.lang.Lang.values())
                .startup(new RegisterableData(ConfigMapperRegisterable.class))
                .build()
                .init();
    }
}
