package me.piggypiglet.randomspawn.data.options.types.hook;

import java.util.LinkedHashSet;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class HookList {
    private boolean enabled;
    private Set<HookTypes> values;

    public HookList(boolean enabled, Set<HookTypes> values) {
        this.enabled = enabled;
        this.values = values;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<HookTypes> getValues() {
        return values;
    }

    public void setValues(Set<HookTypes> values) {
        this.values = values;
    }

    public HookList dupe() {
        return new HookList(enabled, new LinkedHashSet<>(values));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HookList)) return false;
        final HookList hookList = (HookList) obj;

        return hookList.enabled == enabled &&
                hookList.values.equals(values);
    }
}
