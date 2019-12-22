package me.piggypiglet.randomspawn.lang;

import me.piggypiglet.framework.lang.LangEnum;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Lang implements LangEnum {
    NOT_PENDING("commands.not_pending"),
    IN_PENDING("commands.in_pending"),
    SUCCESS("commands.success"),

    SPAWN_ALREADY_EXISTS("commands.create.already_exists"),
    CREATE_ENABLED("commands.create.enabled"),
    CONFIRM("commands.create.confirm"),
    SET_SET("commands.create.set.set"),

    NOT_SET_SPAWN("commands.modify.set.add.not"),
    ADDED_LOCATION("commands.modify.set.add.success"),

    INDEX_TOO_BIG("commands.modify.set.remove.index_too_big"),
    REMOVED_LOCATION("commands.modify.set.remove.success"),

    UNKNOWN_SPAWN("commands.edit.unknown"),
    NO_SPAWNS("commands.edit.no_spawns"),
    EDIT_ENABLED("commands.edit.enabled"),
    TOGGLED_HOOKS("commands.edit.toggle.hook"),
    DISTANCE_SUCCESS("commands.edit.dfc.success"),
    TOGGLED_LIST("commands.edit.toggle.list"),
    LIST_MODIFICATION_ADDED("commands.edit.toggle.list_mod.added"),
    LIST_MODIFICATION_REMOVED("commands.edit.toggle.list_mod.removed"),
    HOOK_UPDATE("commands.edit.toggle.update"),

    LIST_HEADER("commands.list.header"),
    LIST_FORMAT("commands.list.format"),
    LIST_FOOTER("commands.list.footer"),

    INFO_HEADER("commands.info.header"),
    INFO_FORMAT("commands.info.format"),
    INFO_FOOTER("commands.info.footer"),
    INFO_SET_ADDENDUM("commands.info.set.addendum"),
    INFO_SET_FORMAT("commands.info.set.format"),
    INFO_RADIUS_ADDENDUM("commands.info.radius.addendum"),

    SAVE_SUCCESS("commands.save.success"),

    RELOAD_FAILURE("commands.reload.failure"),
    RELOAD_SUCCESS("commands.reload.success"),

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
