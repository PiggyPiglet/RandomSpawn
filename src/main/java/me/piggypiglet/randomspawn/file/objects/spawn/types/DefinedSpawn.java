package me.piggypiglet.randomspawn.file.objects.spawn.types;

import me.piggypiglet.randomspawn.file.objects.spawn.Spawn;
import me.piggypiglet.randomspawn.file.objects.spawn.location.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class DefinedSpawn extends Spawn {
    private Set<Location> locations;

    @NotNull
    public Set<Location> getLocations() {
        return locations;
    }
}
