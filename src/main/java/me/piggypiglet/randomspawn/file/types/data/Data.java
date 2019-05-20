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
            FileConfiguration data = fileManager.getConfig("data");
            String prefix = "data.locations." + name + ".";
            Spawn spawn = Spawn.builder()
                    .name(name)
                    .enabled(true)
                    .world(location.getWorld().getName())
                    .x(location.getX())
                    .y(location.getY())
                    .z(location.getZ())
                    .yaw(location.getYaw())
                    .pitch(location.getPitch())
                    .build();

            data.set(prefix + "enabled", spawn.isEnabled());
            data.set(prefix + "world", spawn.getWorld());
            data.set(prefix + "x", spawn.getX());
            data.set(prefix + "y", spawn.getY());
            data.set(prefix + "z", spawn.getZ());
            data.set(prefix + "yaw", spawn.getYaw());
            data.set(prefix + "pitch", spawn.getPitch());
            fileManager.save("data");

            return spawn;
        }

        return null;
    }

    public List<Spawn> getAllSpawns() {
        ConfigurationSection section = fileManager.getConfig("data").getConfigurationSection("data.locations");

        if (section != null) {
            return section.getKeys(false).stream().map(k -> Spawn.builder()
                    .name(k)
                    .enabled(section.getBoolean(k + ".enabled", true))
                    .world(section.getString(k + ".world", "world"))
                    .x(section.getDouble(k + ".x", 0D))
                    .y(section.getDouble(k + ".y", 0D))
                    .z(section.getDouble(k + ".z", 0D))
                    .yaw(section.getInt(k + ".yaw", 0))
                    .pitch(section.getInt(k + ".pitch", 0))
                    .build()).collect(Collectors.toList());
        }

        return null;
    }
}
