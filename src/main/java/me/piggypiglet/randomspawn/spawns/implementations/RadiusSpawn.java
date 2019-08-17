package me.piggypiglet.randomspawn.spawns.implementations;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.piggypiglet.randomspawn.spawns.AreaSpawn;
import me.piggypiglet.randomspawn.spawns.Spawn;
import me.piggypiglet.randomspawn.utils.MathUtils;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@EqualsAndHashCode(callSuper = true)
@Data
public final class RadiusSpawn extends AreaSpawn {
    private int radius;
    private double x;
    private double z;

    @Builder(toBuilder = true)
    public RadiusSpawn(String name, boolean enabled, boolean respawn, World world, int radius, double x, double z) {
        super(name, enabled, respawn, world);
        this.x = x;
        this.z = z;
        this.radius = radius;
    }

    @Override
    public List<int[]> getLocations() {
        return MathUtils.coordinateCircle(new double[] {x, z}, radius);
    }
}
