package me.piggypiglet.randomspawn;

import org.bukkit.plugin.java.JavaPlugin;

public final class RandomSpawn extends JavaPlugin {
    private static RandomSpawn instance;

    public static RandomSpawn getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
