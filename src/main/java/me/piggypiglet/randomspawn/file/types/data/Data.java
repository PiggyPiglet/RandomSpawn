package me.piggypiglet.randomspawn.file.types.data;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.file.FileManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Data {
    private FileConfiguration data;

    @Inject
    public Data(FileManager fileManager) {
        this.data = fileManager.getConfig("data");
    }

    public Spawn setSpawn(String name, Location location) {
        if (location.getWorld() != null) {
            String prefix = "data.locations." + name + ".";
            Spawn spawn = Spawn.builder()
                    .name(name)
                    .world(location.getWorld().getName())
                    .x(location.getX())
                    .y(location.getY())
                    .z(location.getZ())
                    .yaw(location.getYaw())
                    .pitch(location.getPitch())
                    .build();

            data.set(prefix + "world", spawn.getWorld());
            data.set(prefix + "x", spawn.getX());
            data.set(prefix + "y", spawn.getY());
            data.set(prefix + "z", spawn.getZ());
            data.set(prefix + "yaw", spawn.getYaw());
            data.set(prefix + "pitch", spawn.getPitch());

            return spawn;
        }

        return null;
    }
}
