package me.piggypiglet.randomspawn.data.options;

import me.piggypiglet.randomspawn.data.options.types.hook.Hooks;
import me.piggypiglet.randomspawn.data.options.types.list.Lists;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Options {
    private final Lists blocks;
    private final Lists biomes;
    private final Lists worlds;
    private final Hooks hooks;
    private final boolean respawn;
    private final boolean safeLocation;

    public Options(Lists blocks, Lists biomes, Lists worlds, Hooks hooks, boolean respawn, boolean safeLocation) {
        this.blocks = blocks;
        this.biomes = biomes;
        this.worlds = worlds;
        this.hooks = hooks;
        this.respawn = respawn;
        this.safeLocation = safeLocation;
    }

    public Lists getBlocks() {
        return blocks;
    }

    public Lists getBiomes() {
        return biomes;
    }

    public Lists getWorlds() {
        return worlds;
    }

    public Hooks getHooks() {
        return hooks;
    }

    public boolean isRespawn() {
        return respawn;
    }

    public boolean isSafeLocation() {
        return safeLocation;
    }
}
