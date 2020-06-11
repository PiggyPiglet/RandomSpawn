package me.piggypiglet.randomspawn.file.objects.options.hooks;

import me.piggypiglet.randomspawn.file.objects.options.hooks.types.Factions;
import me.piggypiglet.randomspawn.file.objects.options.hooks.types.GriefPrevention;
import me.piggypiglet.randomspawn.file.objects.options.hooks.types.WorldGuard;
import me.piggypiglet.randomspawn.file.objects.options.set.ValueSet;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class Hooks extends ValueSet {
    private WorldGuard worldGuard;
    private Factions factions;
    private GriefPrevention griefPrevention;

    @NotNull
    public WorldGuard getWorldGuard() {
        return worldGuard;
    }

    @NotNull
    public Factions getFactions() {
        return factions;
    }

    @NotNull
    public GriefPrevention getGriefPrevention() {
        return griefPrevention;
    }
}
