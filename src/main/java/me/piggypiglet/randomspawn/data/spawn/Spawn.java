package me.piggypiglet.randomspawn.data.spawn;

import me.piggypiglet.framework.utils.SearchUtils;
import me.piggypiglet.randomspawn.data.options.Options;
import me.piggypiglet.randomspawn.data.spawn.types.Spawns;
import org.bukkit.World;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Spawn implements SearchUtils.Searchable {
    private String name;
    private final Spawns type;
    private String permission;
    private boolean enabled;
    private final World world;
    private Options options;

    public Spawn(String name, Spawns type, String permission, boolean enabled, World world, Options options) {
        this.name = name;
        this.type = type;
        this.permission = permission;
        this.enabled = enabled;
        this.world = world;
        this.options = options;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Spawns getType() {
        return type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public World getWorld() {
        return world;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }
}
