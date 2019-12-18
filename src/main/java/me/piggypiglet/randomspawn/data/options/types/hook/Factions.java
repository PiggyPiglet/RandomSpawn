package me.piggypiglet.randomspawn.data.options.types.hook;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Factions {
    private final boolean safezone;
    private final boolean warzone;
    private final boolean wilderness;
    private final boolean enemy;
    private final boolean own;

    public Factions(boolean safezone, boolean warzone, boolean wilderness, boolean enemy, boolean own) {
        this.safezone = safezone;
        this.warzone = warzone;
        this.wilderness = wilderness;
        this.enemy = enemy;
        this.own = own;
    }

    public boolean isSafezone() {
        return safezone;
    }

    public boolean isWarzone() {
        return warzone;
    }

    public boolean isWilderness() {
        return wilderness;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public boolean isOwn() {
        return own;
    }
}
