package me.piggypiglet.randomspawn.data.options.types.list;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class List {
    private boolean enabled;
    private Set<String> values;

    public List(boolean enabled, Set<String> values) {
        this.enabled = enabled;
        this.values = values;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<String> getValues() {
        return values;
    }

    public void setValues(Set<String> values) {
        this.values = values;
    }

//    public List dupe() {
//        return
//    }
}
