package me.piggypiglet.randomspawn.data.options;

import me.piggypiglet.randomspawn.data.options.types.hook.Hooks;
import me.piggypiglet.randomspawn.data.options.types.list.Lists;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Options implements Cloneable {
    private Lists blocks;
    private Lists biomes;
    private Lists worlds;
    private Hooks hooks;
    private boolean respawn;
    private boolean safeLocation;

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

    public void setBlocks(Lists blocks) {
        this.blocks = blocks;
    }

    public Lists getBiomes() {
        return biomes;
    }

    public void setBiomes(Lists biomes) {
        this.biomes = biomes;
    }

    public Lists getWorlds() {
        return worlds;
    }

    public void setWorlds(Lists worlds) {
        this.worlds = worlds;
    }

    public Hooks getHooks() {
        return hooks;
    }

    public void setHooks(Hooks hooks) {
        this.hooks = hooks;
    }

    public boolean isRespawn() {
        return respawn;
    }

    public void setRespawn(boolean respawn) {
        this.respawn = respawn;
    }

    public boolean isSafeLocation() {
        return safeLocation;
    }

    public void setSafeLocation(boolean safeLocation) {
        this.safeLocation = safeLocation;
    }

    public Options dupe() {
        return new Options(blocks.dupe(), biomes.dupe(), worlds.dupe(), hooks.dupe(), respawn, safeLocation);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Options)) return false;
        final Options options = (Options) obj;

        return options.blocks.equals(blocks) &&
                options.biomes.equals(biomes) &&
                options.worlds.equals(worlds) &&
                options.hooks.equals(hooks) &&
                options.respawn == respawn &&
                options.safeLocation == safeLocation;
    }
}
