package me.piggypiglet.randomspawn.data.spawn.types;

import me.piggypiglet.randomspawn.data.spawn.Spawn;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Spawns {
    SET(SetSpawn.class),
    CIRCLE(RadiusSpawn.class),
    SQUARE(RadiusSpawn.class),
    UNKNOWN(null);

    private final Class<? extends Spawn> type;

    Spawns(Class<? extends Spawn> type) {
        this.type = type;
    }

    public static Spawns from(Class<? extends Spawn> type) {
        for (Spawns spawn : values()) {
            if (spawn.type == type) return spawn;
        }

        return UNKNOWN;
    }
}
