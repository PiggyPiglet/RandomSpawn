package me.piggypiglet.rs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Stream;

import static org.bukkit.ChatColor.*;

public class RSCommands implements CommandExecutor {
    // ------------------------------
    // Copyright (c) PiggyPiglet 2017
    // https://www.piggypiglet.me
    // ------------------------------
    RandomSpawn plugin;

    public RSCommands (RandomSpawn instance) {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String argsstr = Arrays.toString(args).replace("'", "").replace("[", "").replace("]", "");
        if (cmd.getName().equalsIgnoreCase("rs")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                Stream.of(
                        GRAY + "Help for " + RED + "RandomRespawn",
                        RED + "/rs" + DARK_GRAY + " - " + GRAY + "Opens this help page.",
                        RED + "/rssetspawn [location name]" + DARK_GRAY + " - " + GRAY + "Set a spawn.",
                        RED + "/rsenablespawn [location name]" + DARK_GRAY + " - " + GRAY + "Enable a spawn.",
                        RED + "/rsdisablespawn [location name]" + DARK_GRAY + " - " + GRAY + "Disable a spawn.",
                        RED + "/rstp [location name]" + DARK_GRAY + " - " + GRAY + "Teleport to a spawn.",
                        RED + "/rslist" + DARK_GRAY + " - " + GRAY + "List all the locations.",
                        RED + "/rsinfo [location name]" + DARK_GRAY + " - " + GRAY + "Get information on a certain location.",
                        RED + "/rsreload" + DARK_GRAY + " - " + GRAY + "Reload RandomSpawn (you must use this after adding new locations through config)."
                ).forEach(p::sendRawMessage);
            }
            if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender c = (ConsoleCommandSender) sender;

                Stream.of(
                        GRAY + "Help for " + RED + "RandomRespawn",
                        RED + "/rs" + DARK_GRAY + " - " + GRAY + "Opens this help page.",
                        RED + "/rsenablespawn [location name]" + DARK_GRAY + " - " + GRAY + "Enable a spawn.",
                        RED + "/rsdisablespawn [location name]" + DARK_GRAY + " - " + GRAY + "Disable a spawn.",
                        RED + "/rslist" + DARK_GRAY + " - " + GRAY + "List all the locations.",
                        RED + "/rsinfo [location name]" + DARK_GRAY + " - " + GRAY + "Get information on a certain location.",
                        RED + "/rsreload" + DARK_GRAY + " - " + GRAY + "Reload RandomSpawn (you must use this after adding new locations through config)."
                ).forEach(c::sendRawMessage);
            }
        }
        if (cmd.getName().equalsIgnoreCase("rssetspawn")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 1) {

                    String world = p.getWorld().getName();
                    double x = p.getLocation().getX();
                    double y = p.getLocation().getY();
                    double z = p.getLocation().getZ();
                    float yaw = p.getLocation().getYaw();
                    float pitch = p.getLocation().getPitch();

                    plugin.getConfig().addDefault("locations." + argsstr + ".world", world);
                    plugin.getConfig().addDefault("locations." + argsstr + ".x", x);
                    plugin.getConfig().addDefault("locations." + argsstr + ".y", y);
                    plugin.getConfig().addDefault("locations." + argsstr + ".z", z);
                    plugin.getConfig().addDefault("locations." + argsstr + ".yaw", yaw);
                    plugin.getConfig().addDefault("locations." + argsstr + ".pitch", pitch);
                    plugin.getConfig().addDefault("locations." + argsstr + ".disabled", false);
                    plugin.getConfig().options().copyDefaults(true);
                    plugin.saveDefaultConfig();
                    plugin.loadConfig();

                    p.sendRawMessage(GRAY + argsstr + " set to " + world + ", " + Math.round(x) + ", " + Math.round(y) + ", " + Math.round(z) + ", " + Math.round(yaw) + ", " + Math.round(pitch));
                } else if (args.length == 0) {
                    p.sendRawMessage(GRAY + "You must provide a location name when using " + RED + "/rssetspawn" + GRAY + ". This name can be anything, just make sure not to forget it if you are planning on editing config manually.");
                } else if (args.length >= 2) {
                    p.sendRawMessage(GRAY + "You can not use more than 1 argument in" + RED + " /rssetspawn" + GRAY + ".");
                }
            } else if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender c = (ConsoleCommandSender) sender;

                c.sendRawMessage("This command can only be executed ingame.");
            }
        }
        if (cmd.getName().equalsIgnoreCase("rsenablespawn")) {
            if (args.length == 1 && plugin.getConfig().contains("locations." + argsstr)) {
                plugin.getConfig().set("locations." + argsstr + ".disabled", false);
                plugin.saveConfig();
                plugin.loadConfig();

                sender.sendMessage(RED + argsstr + GRAY + " Enabled.");
            } else if (args.length == 1 && !plugin.getConfig().contains("locations." + argsstr)) {
                sender.sendMessage(GRAY + "The location you provided was invalid, check for spelling errors and retry.");
            } else if (args.length == 0) {
                sender.sendMessage(GRAY + "You must provide a location name when using " + RED + "/rsenablespawn" + GRAY + ".");
            } else if (args.length >= 2) {
                sender.sendMessage(GRAY + "You can not use more than 1 argument in " + RED + "/rsenablespawn");
            }
        }
        if (cmd.getName().equalsIgnoreCase("rsdisablespawn")) {
            if (args.length == 1 && plugin.getConfig().contains("locations." + argsstr)) {
                plugin.getConfig().set("locations." + argsstr + ".disabled", true);
                plugin.saveConfig();
                plugin.loadConfig();

                sender.sendMessage(RED + argsstr + GRAY + " Disabled.");
            } else if (args.length == 1 && !plugin.getConfig().contains("locations." + argsstr)) {
                sender.sendMessage(GRAY + "The location you provided was invalid, check for spelling errors and retry.");
            } else if (args.length == 0) {
                sender.sendMessage(GRAY + "You must provide a location name when using " + RED + "/rsdisablespawn" + GRAY + ".");
            } else if (args.length >= 2) {
                sender.sendMessage(GRAY + "You can not use more than 1 argument in " + RED + "/rsdsablespawn");
            }
        }
        if (cmd.getName().equalsIgnoreCase("rstp")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 1 && plugin.getConfig().contains("locations." + argsstr)) {
                    String loc = argsstr;
                    World world = Bukkit.getWorld(plugin.getConfig().getString("locations." + argsstr + ".world"));
                    double x = plugin.getConfig().getDouble("locations." + argsstr + ".x");
                    double y = plugin.getConfig().getDouble("locations." + argsstr + ".y");
                    double z = plugin.getConfig().getDouble("locations." + argsstr + ".z");
                    float yaw = plugin.getConfig().getInt("locations." + argsstr + ".yaw");
                    float pitch = plugin.getConfig().getInt("locations." + argsstr + ".pitch");

                    p.teleport(new Location(world, x, y, z, yaw, pitch));
                    p.sendRawMessage(GRAY + "Teleported to " + RED + loc);
                } else if (args.length == 1 && !plugin.getConfig().contains("locations." + argsstr)) {
                    p.sendRawMessage(GRAY + "The location you provided was invalid, check for spelling errors and retry.");
                } else if (args.length == 0) {
                    p.sendRawMessage(GRAY + "You must provide a location name when using" + RED + " /rstp" + GRAY + ".");
                } else if (args.length >= 2) {
                    p.sendRawMessage(GRAY + "You can not use more than 1 argument in " + RED + "/rstp" + GRAY + ".");
                }
            } else if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender c = (ConsoleCommandSender) sender;

                c.sendRawMessage("This command can only be executed ingame");
            }
        }
        if (cmd.getName().equalsIgnoreCase("rslist")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                p.sendRawMessage(GRAY + "Locations list:");
                p.sendRawMessage(RED + String.valueOf(plugin.getLocationList()).replace("[", "").replace("]", "").replace(",", "\n").replace(" ", ""));
            } else if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender c = (ConsoleCommandSender) sender;

                c.sendRawMessage(String.valueOf(plugin.getLocationList()).replace("[", "").replace("]", "").replace(",", "\n").replace(" ", ""));
            }
        }
        if (cmd.getName().equalsIgnoreCase("rsinfo")) {
            if (args.length == 1 && plugin.getConfig().contains("locations." + argsstr)) {
                String world = plugin.getConfig().getString("locations." + argsstr + ".world");
                double x = plugin.getConfig().getDouble("locations." + argsstr + ".x");
                double y = plugin.getConfig().getDouble("locations." + argsstr + ".y");
                double z = plugin.getConfig().getDouble("locations." + argsstr + ".z");
                float yaw = plugin.getConfig().getInt("locations." + argsstr + ".yaw");
                float pitch = plugin.getConfig().getInt("locations." + argsstr + ".pitch");
                String disabled = String.valueOf(plugin.getConfig().getBoolean("locations." + argsstr + ".disabled"));

                Stream.of(
                        GRAY + "Info for " + RED + argsstr,
                        GRAY + "World: " + RED + world,
                        GRAY + "X: " + RED + Math.round(x),
                        GRAY + "Y: " + RED + Math.round(y),
                        GRAY + "Z: " + RED + Math.round(z),
                        GRAY + "Yaw: " + RED + Math.round(yaw),
                        GRAY + "Pitch: " + RED + Math.round(pitch),
                        GRAY + "Disabled:" + RED + disabled
                ).forEach(sender::sendMessage);
            } else if (args.length == 1 && !plugin.getConfig().contains("locations." + argsstr)) {
                sender.sendMessage(GRAY + "The location you provided was invalid, check for spelling errors and retry.");
            } else if (args.length == 0) {
                sender.sendMessage(GRAY + "You must provide a location name when using" + RED + " /rsinfo" + GRAY + ".");
            } else if (args.length >= 2) {
                sender.sendMessage(GRAY + "You can not use more than 1 argument in " + RED + "/rsinfo" + GRAY + ".");
            }
        }
        if (cmd.getName().equalsIgnoreCase("rsreload")) {
            plugin.saveDefaultConfig();
            plugin.loadConfig();

            sender.sendMessage(GRAY + "Plugin reloaded.");
        }
        return true;
    }
}
