package me.piggypiglet.randomspawn.lang;

import me.piggypiglet.framework.lang.LangEnum;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Lang implements LangEnum {
    NOT_PENDING("commands.not_pending"),
    IN_PENDING("commands.in_pending"),

    SPAWN_ALREADY_EXISTS("commands.create.already_exists"),
    CREATE_ENABLED("commands.create.enabled"),
    SET_SET("commands.create.set.set"),
    CONFIRM("commands.create.confirm"),
    NOT_SET_SPAWN("commands.create.set.not"),
    ADDED_LOCATION("commands.create.set.added"),
    SUCCESS("commands.create.success"),

    UNKNOWN_SPAWN("commands.edit.unknown"),
    NO_SPAWNS("commands.edit.no_spawns"),
    EDIT_ENABLED("commands.edit.enabled"),

    CANCEL_SUCCESS("commands.cancel.success")
    ;

    private final String path;

    Lang(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
