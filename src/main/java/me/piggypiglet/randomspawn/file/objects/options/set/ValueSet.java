package me.piggypiglet.randomspawn.file.objects.options.set;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public class ValueSet {
    private boolean enabled;
    private Set<String> values;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    @NotNull
    public Set<String> getValues() {
        return values;
    }

    public void setValues(@NotNull final Set<String> values) {
        this.values = values;
    }
}
