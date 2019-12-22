package me.piggypiglet.randomspawn.data.spawn.types;

import me.piggypiglet.randomspawn.data.options.Options;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.LinkedHashSet;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SetSpawn extends Spawn {
    private Set<Location> locations;

    public SetSpawn(String name, Spawns type, String permission, boolean enabled, World world, Options options, Set<Location> locations) {
        super(name, type, permission, enabled, world, options);
        this.locations = locations;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    @Override
    protected Spawn copy() {
        return new SetSpawn(getName(), getType(), getPermission(), isEnabled(), getWorld(), getOptions().dupe(), new LinkedHashSet<>(locations));
    }
}
