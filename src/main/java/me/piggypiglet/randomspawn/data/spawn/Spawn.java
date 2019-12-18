package me.piggypiglet.randomspawn.data.spawn;

import me.piggypiglet.randomspawn.data.options.Options;
import me.piggypiglet.randomspawn.data.spawn.types.Spawns;
import org.bukkit.World;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Spawn {
    private final String name;
    private final Spawns type;
    private final String permission;
    private final boolean enabled;
    private final World world;
    private final Options options;

    public Spawn(String name, Spawns type, String permission, boolean enabled, World world, Options options) {
        this.name = name;
        this.type = type;
        this.permission = permission;
        this.enabled = enabled;
        this.world = world;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public Spawns getType() {
        return type;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public World getWorld() {
        return world;
    }

    public Options getOptions() {
        return options;
    }
}
