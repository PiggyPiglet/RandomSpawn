package me.piggypiglet.randomspawn.data.options.types.list;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class List {
    private final boolean enabled;
    private final java.util.Set<String> values;

    public List(boolean enabled, java.util.Set<String> values) {
        this.enabled = enabled;
        this.values = values;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public java.util.Set<String> getValues() {
        return values;
    }
}
