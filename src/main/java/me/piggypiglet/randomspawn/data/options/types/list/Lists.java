package me.piggypiglet.randomspawn.data.options.types.list;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Lists {
    private List whitelist;
    private List blacklist;

    public Lists(List whitelist, List blacklist) {
        this.whitelist = whitelist;
        this.blacklist = blacklist;
    }

    public List getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(List whitelist) {
        this.whitelist = whitelist;
    }

    public List getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(List blacklist) {
        this.blacklist = blacklist;
    }

    public Lists dupe() {
        return new Lists(whitelist.clone(), blacklist.clone());
    }
}
