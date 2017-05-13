package me.piggypiglet.rs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.*;

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

                p.sendRawMessage(ChatColor.GRAY + "Help for " + ChatColor.RED + "RandomRespawn");
                p.sendRawMessage(ChatColor.RED + "/rs " + ChatColor.DARK_GRAY + "-" + ChatColor.GRAY + " Opens this help page.");
                p.sendRawMessage(ChatColor.RED + "/rssetspawn [location name] " + ChatColor.DARK_GRAY + "-" + ChatColor.GRAY + " Set a spawn.");
                p.sendRawMessage(ChatColor.RED + "/rstp [location name]" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Teleport to a spawn.");
                p.sendRawMessage(ChatColor.RED + "/rsinfo [location name]" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Get information on a certain location.");
                p.sendRawMessage(ChatColor.RED + "/rsreload" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Reload RandomSpawn (you must use this after adding new locations manually or ingame).");
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
                    plugin.getConfig().options().copyDefaults(true);
                    plugin.saveConfig();
                    plugin.reloadConfig();

                    p.sendRawMessage(ChatColor.GRAY + argsstr + " set to " + world + ", " + Math.round(x) + ", " + Math.round(y) + ", " + Math.round(z) + ", " + Math.round(yaw) + ", " + Math.round(pitch));
                } else if (args.length == 0) {
                    p.sendRawMessage(ChatColor.GRAY + "You must provide a location name when using " + ChatColor.RED + "/rssetspawn" + ChatColor.GRAY + ". This name can be anything, just make sure not to forget it if you are planning on editing config manually.");
                } else if (args.length >= 2) {
                    p.sendRawMessage(ChatColor.GRAY + "You can not use more than 1 argument in" + ChatColor.RED + " /rssetspawn" + ChatColor.GRAY + ".");
                }
            } else if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender c = (ConsoleCommandSender) sender;

                c.sendRawMessage("This command can only be executed ingame.");
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
                    p.sendRawMessage(ChatColor.GRAY + "Teleported to " + ChatColor.RED + loc);
                } else if (args.length == 1 && !plugin.getConfig().contains("locations." + argsstr)) {
                    p.sendRawMessage(ChatColor.GRAY + "The location you provided was invalid, check for spelling errors and retry.");
                } else if (args.length == 0) {
                    p.sendRawMessage(ChatColor.GRAY + "You must provide a location name when using" + ChatColor.RED + " /rstp" + ChatColor.GRAY + ".");
                } else if (args.length >= 2) {
                    p.sendRawMessage(ChatColor.GRAY + "You can not use more than 1 argument in " + ChatColor.RED + "/rstp" + ChatColor.GRAY + ".");
                }
            }
            if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender c = (ConsoleCommandSender) sender;

                c.sendRawMessage("This command can only be executed ingame");
            }
        }
        if (cmd.getName().equalsIgnoreCase("rsinfo")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 1 && plugin.getConfig().contains("locations." + argsstr)) {
                    String world = plugin.getConfig().getString("locations." + argsstr + ".world");
                    double x = plugin.getConfig().getDouble("locations." + argsstr + ".x");
                    double y = plugin.getConfig().getDouble("locations." + argsstr + ".y");
                    double z = plugin.getConfig().getDouble("locations." + argsstr + ".z");
                    float yaw = plugin.getConfig().getInt("locations." + argsstr + ".yaw");
                    float pitch = plugin.getConfig().getInt("locations." + argsstr + ".pitch");

                    p.sendRawMessage(ChatColor.GRAY + "Info for " + ChatColor.RED + argsstr);
                    p.sendRawMessage(ChatColor.GRAY + "World: " + ChatColor.RED + world);
                    p.sendRawMessage(ChatColor.GRAY + "X: " + ChatColor.RED + Math.round(x));
                    p.sendRawMessage(ChatColor.GRAY + "Y: " + ChatColor.RED + Math.round(y));
                    p.sendRawMessage(ChatColor.GRAY + "Z: " + ChatColor.RED + Math.round(z));
                    p.sendRawMessage(ChatColor.GRAY + "Yaw: " + ChatColor.RED + Math.round(yaw));
                    p.sendRawMessage(ChatColor.GRAY + "Pitch: " + ChatColor.RED + Math.round(pitch));
                } else if (args.length == 1 && !plugin.getConfig().contains("locations." + argsstr)) {
                    p.sendRawMessage(ChatColor.GRAY + "The location you provided was invalid, check for spelling errors and retry.");
                } else if (args.length == 0) {
                    p.sendRawMessage(ChatColor.GRAY + "You must provide a location name when using" + ChatColor.RED + " /rsinfo" + ChatColor.GRAY + ".");
                } else if (args.length >= 2) {
                    p.sendRawMessage(ChatColor.GRAY + "You can not use more than 1 argument in " + ChatColor.RED + "/rsinfo" + ChatColor.GRAY + ".");
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("rsreload")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                plugin.reloadConfig();
                plugin.getPluginLoader().disablePlugin(plugin.getServer().getPluginManager().getPlugin("RandomRespawn"));
                plugin.getPluginLoader().enablePlugin(plugin.getServer().getPluginManager().getPlugin("RandomRespawn"));
                p.sendRawMessage(ChatColor.GRAY + "Plugin reloaded.");
            } else if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender c = (ConsoleCommandSender) sender;

                plugin.reloadConfig();
                plugin.getPluginLoader().disablePlugin(plugin.getServer().getPluginManager().getPlugin("RandomRespawn"));
                plugin.getPluginLoader().enablePlugin(plugin.getServer().getPluginManager().getPlugin("RandomRespawn"));
                c.sendRawMessage("Plugin reloaded.");
            }
        }
        return true;
    }
}
