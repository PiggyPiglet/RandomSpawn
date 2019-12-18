package me.piggypiglet.randomspawn.data.options.types.list;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Lists {
    private final List whitelist;
    private final List blacklist;

    public Lists(List whitelist, List blacklist) {
        this.whitelist = whitelist;
        this.blacklist = blacklist;
    }

    public List getWhitelist() {
        return whitelist;
    }

    public List getBlacklist() {
        return blacklist;
    }
}
