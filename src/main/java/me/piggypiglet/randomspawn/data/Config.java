package me.piggypiglet.randomspawn.data;

import me.piggypiglet.randomspawn.data.options.Options;
import me.piggypiglet.randomspawn.data.spawn.Spawn;

import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Config {
    private Options options;
    private Map<String, Spawn> spawns;

    public Config(Options options, Map<String, Spawn> spawns) {
        this.options = options;
        this.spawns = spawns;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Map<String, Spawn> getSpawns() {
        return spawns;
    }

    public void set(Config config) {
        this.options = config.getOptions();
        this.spawns = config.getSpawns();
    }
}
