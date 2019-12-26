package me.piggypiglet.randomspawn.data.spawn.types;

import me.piggypiglet.randomspawn.data.options.Options;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import org.bukkit.World;

import java.util.Arrays;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RectangleSpawn extends Spawn {
    private int[] corner1;
    private int[] corner2;

    public RectangleSpawn(String name, Spawns type, String permission, boolean enabled, World world, Options options, int[] corner1, int[] corner2) {
        super(name, type, permission, enabled, world, options);
        this.corner1 = corner1;
        this.corner2 = corner2;
    }

    public int[] getCorner1() {
        return corner1;
    }

    public void setCorner1(int[] corner1) {
        this.corner1 = corner1;
    }

    public int[] getCorner2() {
        return corner2;
    }

    public void setCorner2(int[] corner2) {
        this.corner2 = corner2;
    }

    @Override
    protected Spawn copy() {
        return new RectangleSpawn(getName(), getType(), getPermission(), isEnabled(), getWorld(), getOptions().dupe(),
                Arrays.copyOf(corner1, 2), Arrays.copyOf(corner2, 2));
    }
}
