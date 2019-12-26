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
    UNKNOWN("commands.unknown"),
    NO_SPAWNS("commands.no_spawns"),

    SPAWN_ALREADY_EXISTS("commands.create.already_exists"),
    CREATE_ENABLED("commands.create.enabled"),
    CONFIRM("commands.create.confirm"),

    SET_SET("commands.create.set.set"),
    RADIUS_SET("commands.create.radius.set"),
    RECTANGLE_SET("commands.create.rectangle.set"),

    NOT_SET_SPAWN("commands.modify.set.add.not"),
    ADDED_LOCATION("commands.modify.set.add.success"),

    NOT_RADIUS_SPAWN("commands.modify.radius.not"),
    SET_CENTER("commands.modify.radius.center"),
    SET_RADIUS("commands.modify.radius.radius"),

    SET_CORNER("commands.modify.rectangle.corner"),

    INDEX_TOO_BIG("commands.modify.remove.index_too_big"),

    REMOVED_LOCATION("commands.modify.set.remove.success"),

    EDIT_ENABLED("commands.edit.enabled"),
    TOGGLED_HOOKS("commands.edit.toggle.hook"),
    DISTANCE_SUCCESS("commands.edit.dfc.success"),
    TOGGLED_LIST("commands.edit.toggle.list"),
    LIST_MODIFICATION_ADDED("commands.edit.toggle.list_mod.added"),
    LIST_MODIFICATION_REMOVED("commands.edit.toggle.list_mod.removed"),
    HOOK_UPDATE("commands.edit.toggle.update"),

    LIST_ADDED("commands.modify.options.list.added"),
    LIST_REMOVED("commands.modify.options.list.removed"),

    LIST_HEADER("commands.list.header"),
    LIST_FORMAT("commands.list.format"),
    LIST_FOOTER("commands.list.footer"),

    INFO_HEADER("commands.info.header"),
    INFO_FORMAT("commands.info.main_format"),
    INFO_FOOTER("commands.info.footer"),
    INFO_SET_ADDENDUM("commands.info.set.addendum"),
    INFO_SET_FORMAT("commands.info.set.format"),
    INFO_RADIUS_ADDENDUM("commands.info.radius.addendum"),
    INFO_RECTANGLE_ADDENDUM("commands.info.rectangle.addendum"),

    INFO_OTHER_HEADER("commands.info.other_formats.format.header"),
    INFO_OTHER_FOOTER("commands.info.other_formats.format.footer"),
    INFO_OTHER_LIST_BODY("commands.info.other_formats.format.list.body"),
    INFO_OTHER_LIST_FORMAT("commands.info.other_formats.format.list.format"),
    INFO_OTHER_HOOKS_BODY("commands.info.other_formats.format.hooks.body"),
    INFO_OTHER_FACTIONS_FORMAT("commands.info.other_formats.format.factions"),
    INFO_OTHER_GRIEFPREVENTION_FORMAT("commands.info.other_formats.format.griefprevention"),
    INFO_OTHER_BLOCKS("commands.info.other_formats.blocks"),
    INFO_OTHER_BIOMES("commands.info.other_formats.biomes"),
    INFO_OTHER_WORLDS("commands.info.other_formats.worlds"),
    INFO_OTHER_HOOKS("commands.info.other_formats.hooks"),
    INFO_OTHER_WORLDGUARD("commands.info.other_formats.worldguard"),
    INFO_OTHER_FACTIONS("commands.info.other_formats.factions"),
    INFO_OTHER_GRIEFPREVENTION("commands.info.other_formats.griefprevention"),

    BAD_SPAWN("commands.info.bad_spawn"),

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
