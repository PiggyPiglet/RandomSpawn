package me.piggypiglet.randomspawn.data.options.types.hook;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Factions {
    private boolean safezone;
    private boolean warzone;
    private boolean wilderness;
    private boolean enemy;
    private boolean own;

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

    public void setSafezone(boolean safezone) {
        this.safezone = safezone;
    }

    public boolean isWarzone() {
        return warzone;
    }

    public void setWarzone(boolean warzone) {
        this.warzone = warzone;
    }

    public boolean isWilderness() {
        return wilderness;
    }

    public void setWilderness(boolean wilderness) {
        this.wilderness = wilderness;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void setEnemy(boolean enemy) {
        this.enemy = enemy;
    }

    public boolean isOwn() {
        return own;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }
}
