package me.piggypiglet.randomspawn.file.objects.spawn;

import me.piggypiglet.randomspawn.file.objects.options.Options;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public abstract class Spawn {
    private UUID uuid;
    private String name;
    private String permission;
    private boolean enabled;
    private UUID world;
    private Options options;

    @NotNull
    public UUID getUuid() {
        return uuid;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull final String name) {
        this.name = name;
    }

    @NotNull
    public String getPermission() {
        return permission;
    }

    public void setPermission(@NotNull final String permission) {
        this.permission = permission;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    @NotNull
    public UUID getWorld() {
        return world;
    }

    public void setWorld(@NotNull final UUID world) {
        this.world = world;
    }

    @NotNull
    public Options getOptions() {
        return options;
    }
}
