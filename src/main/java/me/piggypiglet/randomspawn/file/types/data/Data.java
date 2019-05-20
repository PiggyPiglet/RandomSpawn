package me.piggypiglet.randomspawn.file.types.data;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.file.FileManager;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Data {
    @Inject private FileManager fileManager;

    public Spawn setSpawn(String name, Location location) {
        if (location.getWorld() != null) {
            FileConfiguration config = fileManager.getConfig("data");
            Spawn spawn = Spawn.builder()
                    .name(name)
                    .world(location.getWorld().getName())
                    .x(location.getX())
                    .y(location.getY())
                    .z(location.getZ())
                    .yaw(location.getYaw())
                    .pitch(location.getPitch())
                    .enabled(true)
                    .respawn(false)
                    .build();

            config.createSection("data.locations." + name);
            editSpawn(spawn);

            return spawn;
        }

        return null;
    }

    public void editSpawn(Spawn spawn) {
        ConfigurationSection section = fileManager.getConfig("config").getConfigurationSection("data.locations." + spawn.getName());

        if (section != null) {
            section.set("world", spawn.getWorld());
            section.set("x", spawn.getX());
            section.set("y", spawn.getY());
            section.set("z", spawn.getZ());
            section.set("yaw", spawn.getYaw());
            section.set("pitch", spawn.getPitch());
            section.set("enabled", spawn.isEnabled());
            section.set("respawn", spawn.isRespawn());
        }
    }

    public void deleteSpawn(Spawn spawn) {
        FileConfiguration config = fileManager.getConfig("config");

        config.set(spawn.getName(), null);
    }

    public List<Spawn> getAllSpawns() {
        ConfigurationSection section = fileManager.getConfig("config").getConfigurationSection("data.locations");

        if (section != null) {
            return section.getKeys(false).stream().map(k -> Spawn.builder()
                    .name(k)
                    .world(section.getString(k + ".world", "world"))
                    .x(section.getDouble(k + ".x", 0D))
                    .y(section.getDouble(k + ".y", 0D))
                    .z(section.getDouble(k + ".z", 0D))
                    .yaw(section.getInt(k + ".yaw", 0))
                    .pitch(section.getInt(k + ".pitch", 0))
                    .enabled(section.getBoolean(k + ".enabled", true))
                    .respawn(section.getBoolean(k + ".respawn", false))
                    .build()).collect(Collectors.toList());
        }

        return null;
    }
}
