package me.piggypiglet.randomspawn.file.objects.spawn.types;

import me.piggypiglet.randomspawn.file.objects.spawn.Spawn;
import me.piggypiglet.randomspawn.file.objects.spawn.types.intpair.IntPair;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RectangleSpawn extends Spawn {
    private IntPair corner1;
    private IntPair corner2;

    @NotNull
    public IntPair getCorner1() {
        return corner1;
    }

    @NotNull
    public IntPair getCorner2() {
        return corner2;
    }
}
