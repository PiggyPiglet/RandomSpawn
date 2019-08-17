package me.piggypiglet.randomspawn.spawns;

import me.piggypiglet.randomspawn.spawns.implementations.DefaultSpawn;
import me.piggypiglet.randomspawn.spawns.implementations.RadiusSpawn;
import me.piggypiglet.randomspawn.spawns.implementations.SquareSpawn;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Spawns {
    DEFAULT(DefaultSpawn.class),
    RADIUS(RadiusSpawn.class),
    SQUARE(SquareSpawn.class),
    UNKNOWN(null);

    private final Class<? extends Spawn> clazz;

    Spawns(Class<? extends Spawn> clazz) {
        this.clazz = clazz;
    }

    public static Spawns getTypeFromSpawn(Spawn spawn) {
        for (Spawns s : values()) {
            if (spawn.getClass() == s.clazz) {
                return s;
            }
        }

        return UNKNOWN;
    }
}
