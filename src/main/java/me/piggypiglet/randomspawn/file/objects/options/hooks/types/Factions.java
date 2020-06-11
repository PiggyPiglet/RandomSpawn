package me.piggypiglet.randomspawn.file.objects.options.hooks.types;

import me.piggypiglet.randomspawn.file.objects.options.hooks.framework.Claimable;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class Factions implements Claimable {
    private int distanceFromClaim;
    private boolean safezone;
    private boolean warzone;
    private boolean wilderness;
    private boolean enemy;
    private boolean own;

    @Override
    public int getDistanceFromClaim() {
        return distanceFromClaim;
    }

    @Override
    public void setDistanceFromClaim(final int distanceFromClaim) {
        this.distanceFromClaim = distanceFromClaim;
    }

    public boolean isSafezone() {
        return safezone;
    }

    public void setSafezone(final boolean safezone) {
        this.safezone = safezone;
    }

    public boolean isWarzone() {
        return warzone;
    }

    public void setWarzone(final boolean warzone) {
        this.warzone = warzone;
    }

    public boolean isWilderness() {
        return wilderness;
    }

    public void setWilderness(final boolean wilderness) {
        this.wilderness = wilderness;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void setEnemy(final boolean enemy) {
        this.enemy = enemy;
    }

    public boolean isOwn() {
        return own;
    }

    public void setOwn(final boolean own) {
        this.own = own;
    }
}
