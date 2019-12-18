package me.piggypiglet.randomspawn.data.spawn.types;

import me.piggypiglet.randomspawn.data.options.Options;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import org.bukkit.World;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RadiusSpawn extends Spawn {
    private double[] center;
    private int radius;

    public RadiusSpawn(String name, Spawns type, String permission, boolean enabled, World world, Options options, double[] center, int radius) {
        super(name, type, permission, enabled, world, options);
        this.center = center;
        this.radius = radius;
    }

    public double[] getCenter() {
        return center;
    }

    public void setCenter(double[] center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
