package me.piggypiglet.randomspawn.file.objects.options.set;

import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public class ValueSets {
    private ValueSets whitelist;
    private ValueSets blacklist;

    @NotNull
    public ValueSets getWhitelist() {
        return whitelist;
    }

    @NotNull
    public ValueSets getBlacklist() {
        return blacklist;
    }
}
