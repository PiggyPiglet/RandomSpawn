package me.piggypiglet.randomspawn.data.options.types.hook;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class GriefPrevention {
    private boolean own;
    private boolean other;
    private boolean wilderness;

    public GriefPrevention(boolean own, boolean other, boolean wilderness) {
        this.own = own;
        this.other = other;
        this.wilderness = wilderness;
    }

    public boolean isOwn() {
        return own;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }

    public boolean isWilderness() {
        return wilderness;
    }

    public void setWilderness(boolean wilderness) {
        this.wilderness = wilderness;
    }
}
