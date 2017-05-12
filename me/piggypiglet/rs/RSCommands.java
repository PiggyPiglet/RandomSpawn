package me.piggypiglet.rs;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

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
        if (cmd.getName().equalsIgnoreCase("rs")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                p.sendRawMessage(ChatColor.GRAY + "Help for " + ChatColor.RED + "RandomRespawn");
                p.sendRawMessage(ChatColor.RED + "/rssetspawn [location name] " + ChatColor.GRAY + "Set a spawn");
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

                    plugin.getConfig().addDefault("locations." + Arrays.toString(args).replace("'", "").replace("[", "").replace("]", "") + ".world", world);
                    plugin.getConfig().addDefault("locations." + Arrays.toString(args).replace("'", "").replace("[", "").replace("]", "") + ".x", x);
                    plugin.getConfig().addDefault("locations." + Arrays.toString(args).replace("'", "").replace("[", "").replace("]", "") + ".y", y);
                    plugin.getConfig().addDefault("locations." + Arrays.toString(args).replace("'", "").replace("[", "").replace("]", "") + ".z", z);
                    plugin.getConfig().addDefault("locations." + Arrays.toString(args).replace("'", "").replace("[", "").replace("]", "") + ".yaw", yaw);
                    plugin.getConfig().addDefault("locations." + Arrays.toString(args).replace("'", "").replace("[", "").replace("]", "") + ".pitch", pitch);
                    plugin.getConfig().options().copyDefaults(true);
                    plugin.saveConfig();

                    p.sendRawMessage(ChatColor.GRAY + Arrays.toString(args) + " set to " + world + ", " + Math.round(x) + ", " + Math.round(y) + ", " + Math.round(z) + ", " + Math.round(yaw) + ", " + Math.round(pitch));
                } else if (args.length == 0) {
                    p.sendRawMessage(ChatColor.RED + "You must provide a location name when using /rssetspawn. This name can be anything, just make sure not to forget it if you are planning on editing config manually.");
                } else if (args.length >= 2) {
                    p.sendRawMessage(ChatColor.RED + "You can not use more than 1 argument in /rssetspawn.");
                }
            }
        }
        return true;
    }
}
