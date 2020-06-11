package me.piggypiglet.randomspawn.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class LocationUtils {
    public static Location bukkitLocation(@NotNull final World world,
                                          @NotNull final me.piggypiglet.randomspawn.file.objects.spawn.location.Location location) {
        return new Location(world, location.getX(), location.getY(), location.getZ(),
                (float) location.getYaw(), (float) location.getPitch());
    }
}
