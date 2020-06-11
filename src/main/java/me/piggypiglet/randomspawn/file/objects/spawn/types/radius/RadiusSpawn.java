package me.piggypiglet.randomspawn.file.objects.spawn.types.radius;

import me.piggypiglet.randomspawn.file.objects.spawn.Spawn;
import me.piggypiglet.randomspawn.file.objects.spawn.types.intpair.IntPair;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RadiusSpawn extends Spawn {
    private IntPair center;
    private int radius;

    @NotNull
    public IntPair getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(final int radius) {
        this.radius = radius;
    }
}
