package me.piggypiglet.randomspawn.file.objects.options.hooks.types;

import me.piggypiglet.randomspawn.file.objects.options.hooks.framework.Claimable;
import me.piggypiglet.randomspawn.file.objects.options.set.ValueSets;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class WorldGuard extends ValueSets implements Claimable {
    private int distanceFromClaim;

    @Override
    public int getDistanceFromClaim() {
        return distanceFromClaim;
    }

    @Override
    public void setDistanceFromClaim(final int distanceFromClaim) {
        this.distanceFromClaim = distanceFromClaim;
    }
}
