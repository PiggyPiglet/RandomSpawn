package me.piggypiglet.randomspawn.data.options.types.hook;

import me.piggypiglet.randomspawn.data.options.types.list.Lists;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Hooks {
    private HookList hooks;
    private int distanceFromClaim;
    private Lists worldGuard;
    private Factions factions;
    private GriefPrevention griefPrevention;

    public Hooks(HookList hooks, int distanceFromClaim, Lists worldGuard, Factions factions, GriefPrevention griefPrevention) {
        this.hooks = hooks;
        this.distanceFromClaim = distanceFromClaim;
        this.worldGuard = worldGuard;
        this.factions = factions;
        this.griefPrevention = griefPrevention;
    }

    public HookList getHooks() {
        return hooks;
    }

    public void setHooks(HookList hooks) {
        this.hooks = hooks;
    }

    public int getDistanceFromClaim() {
        return distanceFromClaim;
    }

    public void setDistanceFromClaim(int distanceFromClaim) {
        this.distanceFromClaim = distanceFromClaim;
    }

    public Lists getWorldGuard() {
        return worldGuard;
    }

    public void setWorldGuard(Lists worldGuard) {
        this.worldGuard = worldGuard;
    }

    public Factions getFactions() {
        return factions;
    }

    public void setFactions(Factions factions) {
        this.factions = factions;
    }

    public GriefPrevention getGriefPrevention() {
        return griefPrevention;
    }

    public void setGriefPrevention(GriefPrevention griefPrevention) {
        this.griefPrevention = griefPrevention;
    }

    public Hooks dupe() {
        return new Hooks(hooks.dupe(), distanceFromClaim, worldGuard.dupe(), factions.dupe(), griefPrevention.dupe());
    }
}
