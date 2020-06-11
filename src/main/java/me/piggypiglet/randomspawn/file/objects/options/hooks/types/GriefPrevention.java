package me.piggypiglet.randomspawn.file.objects.options.hooks.types;

import me.piggypiglet.randomspawn.file.objects.options.hooks.framework.Claimable;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class GriefPrevention implements Claimable {
    private int distanceFromClaim;
    private boolean own;
    private boolean other;
    private boolean wilderness;

    @Override
    public int getDistanceFromClaim() {
        return distanceFromClaim;
    }

    @Override
    public void setDistanceFromClaim(final int distanceFromClaim) {
        this.distanceFromClaim = distanceFromClaim;
    }

    public boolean isOwn() {
        return own;
    }

    public void setOwn(final boolean own) {
        this.own = own;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(final boolean other) {
        this.other = other;
    }

    public boolean isWilderness() {
        return wilderness;
    }

    public void setWilderness(final boolean wilderness) {
        this.wilderness = wilderness;
    }
}
