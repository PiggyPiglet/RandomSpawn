package me.piggypiglet.randomspawn.spawns;

import org.bukkit.World;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class AreaSpawn extends Spawn {
    protected AreaSpawn(final String name, final boolean enabled, final boolean respawn, final World world) {
        super(name, enabled, respawn, world);
    }

    protected abstract List<int[]> getLocations();
}
