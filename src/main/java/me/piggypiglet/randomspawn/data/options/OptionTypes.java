package me.piggypiglet.randomspawn.data.options;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum OptionTypes {
    BLOCKS,
    BIOMES,
    WORLDS,
    HOOKS,
    WORLDGUARD,
    FACTIONS,
    GRIEFPREVENTION,
    UNKNOWN;

    public static OptionTypes from(String name) {
        for (OptionTypes type : values()) {
            if (type.toString().equalsIgnoreCase(name)) return type;
        }

        return UNKNOWN;
    }
}
