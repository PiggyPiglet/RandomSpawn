package me.piggypiglet.randomspawn.spawns.implementations;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.piggypiglet.randomspawn.spawns.Spawn;
import org.bukkit.Location;
import org.bukkit.World;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@EqualsAndHashCode(callSuper = true)
@Data
public final class DefaultSpawn extends Spawn {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    @Builder(toBuilder = true)
    public DefaultSpawn(String name, boolean enabled, boolean respawn, World world, double x, double y, double z, float yaw, float pitch) {
        super(name, enabled, respawn, world);
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}