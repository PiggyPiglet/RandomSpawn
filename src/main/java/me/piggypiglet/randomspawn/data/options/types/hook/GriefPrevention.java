package me.piggypiglet.randomspawn.data.options.types.hook;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class GriefPrevention {
    private final boolean own;
    private final boolean other;
    private final boolean wilderness;

    public GriefPrevention(boolean own, boolean other, boolean wilderness) {
        this.own = own;
        this.other = other;
        this.wilderness = wilderness;
    }

    public boolean isOwn() {
        return own;
    }

    public boolean isOther() {
        return other;
    }

    public boolean isWilderness() {
        return wilderness;
    }
}
