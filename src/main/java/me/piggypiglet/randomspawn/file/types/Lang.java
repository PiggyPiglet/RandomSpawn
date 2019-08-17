package me.piggypiglet.randomspawn.file.types;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.file.FileManager;
import org.bukkit.ChatColor;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Lang {
    FOUND_SPAWNS("spawns", "Found %s total spawns and %s enabled spawns."),
    MIGRATING_CONFIGS("migrating-configs", "%s is outdated! Converting to the latest version.\nConversion will most likely change the order of things inside the file,\nbut this won't change any of your settings/data."),

    INCORRECT_USAGE("commands.incorrect-usage", "&7Incorrect usage, the correct usage of that command is: &c%s %s"),
    NO_PERMISSION("commands.no-permission", "&7You do not have permission for that command."),
    UNKNOWN_COMMAND("commands.unknown-command", "&7Unknown command."),
    PLAYER_ONLY("commands.player-only", "&7That command can only be executed by a player."),
    UNKNOWN_SPAWN("commands.unknown-spawn", "&7Unknown spawn: &c%s&7."),

    SETSPAWN_ERROR("commands.setspawn.error", "&7An error occurred when trying to set your spawn."),
    SETSPAWN_SUCCESS("commands.setspawn.success", "&7Successfully set &c%s as a &c%s &7spawn."),
    ENABLESPAWN_SUCCESS("commands.enablespawn.success", "&7Successfully enabled &c%s&7."),
    DISABLESPAWN_SUCCESS("commands.disablespawn.success", "&7Successfully disabled &c%s&7."),
    ENABLERESPAWN_SUCCESS("commands.enablerespawn.success", "&7Successfully enabled respawning on &c%s&7."),
    DISABLERESPAWN_SUCCESS("commands.disablerespawn.success", "&7Successfully disabled respawning on &c%s&7."),
    DELETESPAWN_SUCCESS("commands.deletespawn.success", "&7Successfully deleted &c%s&7."),

    TELEPORT_SUCCESS("commands.teleport.success", "&7Successfully teleported to &c%s&7."),

    LIST_FORMAT("commands.list.format", "&7%s"),

    INFO_FORMAT("commands.info.format", "&7Info for &c%s&7:\n&7World: &c%s\n&7Type: &c%s\n&7Enabled: &c%s\n&7Respawnable: &c%s"),

    RELOAD_SUCCESS("commands.reload.success", "&7Successfully reloaded the lang file."),

    SPAWN_SUCCESS("commands.spawn.success", "&7Successfully teleported to a random spawn."),

    HELP_HEADER("commands.help.header", "&7----- &cRandomSpawn Commands &7-----"),
    HELP_FORMAT("commands.help.format", "&c/rs %s %s &8- &7%s"),
    HELP_FOOTER("commands.help.footer", "&7-------------------------------"),
    HELP_SPECIFIC("commands.help.specific", "&c/rs %s &s &8- &7%s");

    private final String path;
    private final String def;

    Lang(String path, String def) {
        this.path = path;
        this.def = def;
    }

    public static String getMessage(Lang message, Object... variables) {
        return CheekyInternalClassToDoThingsThatArentMeantToBeDone.get(message, variables);
    }

    public static class CheekyInternalClassToDoThingsThatArentMeantToBeDone {
        @Inject private static FileManager fileManager;

        private static String get(Lang message, Object... variables) {
            String value = fileManager.getConfig("lang").getString(message.path, message.def);

            try {
                //noinspection ConstantConditions
                return colorize(String.format(value, variables));
            } catch (Exception e) {
                return colorize(value.replace("%s", "null"));
            }
        }

        private static String colorize(String txt) {
            return ChatColor.translateAlternateColorCodes('&', txt);
        }
    }
}
