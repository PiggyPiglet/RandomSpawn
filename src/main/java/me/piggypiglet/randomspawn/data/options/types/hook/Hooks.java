package me.piggypiglet.randomspawn.data.options.types.hook;

import me.piggypiglet.randomspawn.data.options.types.list.List;
import me.piggypiglet.randomspawn.data.options.types.list.Lists;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Hooks {
    private final List hooks;
    private final int distanceFromClaim;
    private final Lists worldGuard;
    private final Factions factions;
    private final GriefPrevention griefPrevention;

    public Hooks(List hooks, int distanceFromClaim, Lists worldGuard, Factions factions, GriefPrevention griefPrevention) {
        this.hooks = hooks;
        this.distanceFromClaim = distanceFromClaim;
        this.worldGuard = worldGuard;
        this.factions = factions;
        this.griefPrevention = griefPrevention;
    }

    public List getHooks() {
        return hooks;
    }

    public int getDistanceFromClaim() {
        return distanceFromClaim;
    }

    public Lists getWorldGuard() {
        return worldGuard;
    }

    public Factions getFactions() {
        return factions;
    }

    public GriefPrevention getGriefPrevention() {
        return griefPrevention;
    }
}
