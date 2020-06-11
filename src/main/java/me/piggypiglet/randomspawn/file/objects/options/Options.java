package me.piggypiglet.randomspawn.file.objects.options;

import me.piggypiglet.randomspawn.file.objects.options.hooks.Hooks;
import me.piggypiglet.randomspawn.file.objects.options.set.ValueSets;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class Options {
    private ValueSets blocks;
    private ValueSets biomes;
    private ValueSets worlds;
    private Hooks hooks;
    private boolean respawn;
    private boolean safeLocation;

    @NotNull
    public ValueSets getBlocks() {
        return blocks;
    }

    @NotNull
    public ValueSets getBiomes() {
        return biomes;
    }

    @NotNull
    public ValueSets getWorlds() {
        return worlds;
    }

    @NotNull
    public Hooks getHooks() {
        return hooks;
    }

    public boolean isRespawn() {
        return respawn;
    }

    public void setRespawn(final boolean respawn) {
        this.respawn = respawn;
    }

    public boolean isSafeLocation() {
        return safeLocation;
    }

    public void setSafeLocation(final boolean safeLocation) {
        this.safeLocation = safeLocation;
    }
}
