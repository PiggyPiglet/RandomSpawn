package me.piggypiglet.randomspawn.file.types;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.file.FileManager;
import me.piggypiglet.randomspawn.spawns.Spawn;
import me.piggypiglet.randomspawn.spawns.Spawns;
import me.piggypiglet.randomspawn.spawns.implementations.DefaultSpawn;
import me.piggypiglet.randomspawn.spawns.implementations.RadiusSpawn;
import me.piggypiglet.randomspawn.spawns.implementations.SquareSpawn;
import me.piggypiglet.randomspawn.utils.SpawnUtils;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Data {
    @Inject private FileManager fileManager;

    public Spawn setSpawn(String name, Location location) {
        FileConfiguration config = fileManager.getConfig("config");
        assert location.getWorld() != null;
        Spawn spawn = DefaultSpawn.builder()
                .name(name)
                .enabled(true)
                .respawn(false)
                .world(location.getWorld().getName())
                .x(location.getX())
                .y(location.getY())
                .z(location.getZ())
                .yaw(location.getYaw())
                .pitch(location.getPitch())
                .build();

        config.createSection("data.locations." + name);
        editSpawn(spawn);

        return spawn;
    }

    public Spawn setRadiusSpawn(String name, Location location, int radius) {
        FileConfiguration config = fileManager.getConfig("config");
        assert location.getWorld() != null;
        Spawn spawn = RadiusSpawn.builder()
                .name(name)
                .enabled(true)
                .respawn(false)
                .world(location.getWorld().getName())
                .radius(radius)
                .x(location.getX())
                .z(location.getZ())
                .build();

        config.createSection("data.locations." + name);
        editSpawn(spawn);

        return spawn;
    }

    public Spawn setSquareSpawn(String name, String world, double x1, double z1, double x2, double z2) {
        FileConfiguration config = fileManager.getConfig("config");
        Spawn spawn = SquareSpawn.builder()
                .name(name)
                .enabled(true)
                .respawn(false)
                .world(world)
                .group1(new SquareSpawn.XZGroup(x1, z1))
                .group2(new SquareSpawn.XZGroup(x2, z2))
                .build();

        config.createSection("data.locations." + name);
        editSpawn(spawn);

        return spawn;
    }

    public void editSpawn(Spawn spawn) {
        ConfigurationSection section = fileManager.getConfig("config").getConfigurationSection("data.locations." + spawn.getName());

        if (section != null) {
            section.set("world", spawn.getWorld());
            section.set("enabled", spawn.isEnabled());
            section.set("respawn", spawn.isRespawn());

            switch (Spawns.getTypeFromSpawn(spawn)) {
                case DEFAULT:
                    DefaultSpawn defaultSpawn = (DefaultSpawn) spawn;
                    section.set("x", defaultSpawn.getX());
                    section.set("y", defaultSpawn.getY());
                    section.set("z", defaultSpawn.getY());
                    section.set("yaw", defaultSpawn.getY());
                    section.set("pitch", defaultSpawn.getPitch());
                    break;

                case RADIUS:
                    RadiusSpawn radiusSpawn = (RadiusSpawn) spawn;
                    section.set("radius", true);
                    section.set("radius-amount", radiusSpawn.getRadius());
                    section.set("x", radiusSpawn.getX());
                    section.set("z", radiusSpawn.getZ());
                    break;

                case SQUARE:
                    SquareSpawn squareSpawn = (SquareSpawn) spawn;
                    section.set("radius", true);
                    section.set("group-1", squareSpawn.getGroup1().toString());
                    section.set("group-2", squareSpawn.getGroup2().toString());
                    break;
            }
        }
    }

    public void deleteSpawn(Spawn spawn) {
        FileConfiguration config = fileManager.getConfig("config");

        config.set("data.locations." + spawn.getName(), null);
    }

    public List<Spawn> getAllSpawns() {
        List<Spawn> spawns = new ArrayList<>();
        ConfigurationSection section = fileManager.getConfig("config").getConfigurationSection("data.locations");

        if (section != null) {
            section.getKeys(false).forEach(k -> {
                ConfigurationSection s = section.getConfigurationSection(k);

                if (s != null) {
                    Spawn spawn = null;

                    switch (sectionType(s)) {
                        case DEFAULT:
                            spawn = DefaultSpawn.builder()
                                    .x(s.getDouble("x", 0D))
                                    .y(s.getDouble("y", 0D))
                                    .z(s.getDouble("z", 0D))
                                    .yaw(s.getInt("yaw", 0))
                                    .pitch(s.getInt("pitch", 0))
                                    .build();
                            break;

                        case RADIUS:
                            spawn = RadiusSpawn.builder()
                                    .radius(s.getInt("radius-amount", 1))
                                    .x(s.getDouble("x", 0D))
                                    .z(s.getDouble("z", 0D))
                                    .build();
                            break;

                        case SQUARE:
                            try {
                                double[] group1 = SpawnUtils.unGroup(s.getString("group-1", "null"));
                                double[] group2 = SpawnUtils.unGroup(s.getString("group-2", "null"));

                                spawn = SquareSpawn.builder()
                                        .group1(new SquareSpawn.XZGroup(group1[0], group1[1]))
                                        .group2(new SquareSpawn.XZGroup(group2[0], group2[1]))
                                        .build();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                    }

                    if (spawn != null) {
                        spawn.setName(s.getName());
                        spawn.setEnabled(s.getBoolean("enabled", true));
                        spawn.setRespawn(s.getBoolean("respawn", false));
                        spawn.setWorld(s.getString("world"));
                        spawns.add(spawn);
                    }
                }
            });
        }

        return spawns;
    }

    private Spawns sectionType(ConfigurationSection section) {
        if (section.contains("radius")) {
            if (section.contains("radius-amount")) {
                return Spawns.RADIUS;
            }

            return Spawns.SQUARE;
        }

        return Spawns.DEFAULT;
    }
}
