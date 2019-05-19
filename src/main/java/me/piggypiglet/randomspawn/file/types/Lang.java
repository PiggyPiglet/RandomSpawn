package me.piggypiglet.randomspawn.file.types;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.file.FileManager;
import org.bukkit.ChatColor;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Lang {
    INCORRECT_USAGE("commands.incorrect-usage", "&7Incorrect usage, the correct usage of that command is: &c%s %s"),
    NO_PERMISSION("commands.no-permission", "&7You do not have permission for that command."),
    UNKNOWN_COMMAND("commands.unknown-command", "&7Unknown command."),
    PLAYER_ONLY("commands.player-only", "&7That command can only be executed by a player."),

    SETSPAWN_ERROR("commands.setspawn.error", "&7An error occurred when trying to set your spawn."),
    SETSPAWN_SUCCESS("commands.setspawn.success", "&7Successfully set &c%s &7to &c%s, %s, %s, %s, %s, %s&7."),

    HELP_HEADER("commands.help.header", "&7----- &cRandomSpawn Commands &7-----"),
    HELP_FORMAT("commands.help.format", "&c/rs %s %s &8- &7%s"),
    HELP_FOOTER("commands.help.footer", "&7--------------------------------");

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
