package me.piggypiglet.rs.Commands;

import me.piggypiglet.rs.Handlers.ChatHandler;
import me.piggypiglet.rs.Handlers.ConfigHandler;
import me.piggypiglet.rs.RandomSpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.stream.Stream;

import static me.piggypiglet.rs.Enums.Messages.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2017
// https://www.piggypiglet.me
// ------------------------------
public class RSCommands implements CommandExecutor {
    private RandomSpawn plugin;
    private ChatHandler chat;
    private ConfigHandler config;

    public RSCommands() {
        plugin = RandomSpawn.getInstance();
        chat = new ChatHandler();
        config = new ConfigHandler();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration cfg = plugin.getConfig();
        if (cmd.getName().equalsIgnoreCase("rs")) {
            if (args.length == 0) {
                if (sender.hasPermission("randomspawn.randomspawn")) {
                    Stream.of(
                            "&7Help for &cRandomSpawn",
                            "&c/rs &8- &7Opens this help page.",
                            "&c/rs setspawn [location name] &8- &7Set a spawn.",
                            "&c/rs enablespawn [location name] &8- &7Enable a spawn.",
                            "&c/rs disablespawn [location name] &8- &7Disable a spawn.",
                            "&c/rs enablerespawn [location name] &8- &7Enable respawning on a spawn.",
                            "&c/rs disablerespawn [location name] &8- &7Disable respawning on a spawn.",
                            "&c/rs tp [location name] &8- &7Teleport to a spawn.",
                            "&c/rs list &8- &7List all the locations.",
                            "&c/rs info [location name] &8- &7Get information on a certain location.",
                            "&c/rs reload &8- &7Reload RandomSpawn (you must use this after adding new locations through config)."
                    ).forEach(msg -> chat.sendNoPrefix(sender, msg));
                } else {
                    chat.send(sender, config.getMessages(NOPERM));
                }
            } else if (args.length == 1) {
                String command = args[0];
                if (command.equalsIgnoreCase("list")) {
                    if (sender.hasPermission("randomspawn.list")) {
                        chat.sendNoPrefix(sender, "&7Locations List:\n&c" + String.valueOf(plugin.getLocationList()).replace("[",
                                "").replace("]",
                                "").replace(",",
                                "\n").replace(" ",
                                ""));
                    } else {
                        chat.send(sender, config.getMessages(NOPERM));
                    }
                } else if (command.equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("randomspawn.reload")) {
                        plugin.saveConfig();
                        plugin.saveDefaultConfig();
                        plugin.loadConfig();
                        chat.send(sender, "&7Plugin reloaded.");
                    } else {
                        chat.send(sender, config.getMessages(NOPERM));
                    }
                }
            } else if (args.length >= 2) {
                String command = args[0];
                String location = args[1];
                String world = cfg.getString("data.locations." + location + ".world");
                double x = cfg.getDouble("data.locations." + location + ".x");
                double y = cfg.getDouble("data.locations." + location + ".y");
                double z = cfg.getDouble("data.locations." + location + ".z");
                float yaw = cfg.getInt("data.locations." + location + ".yaw");
                float pitch = cfg.getInt("data.locations." + location + ".pitch");
                if (command.equalsIgnoreCase("setspawn")) {
                    if (sender.hasPermission("randomspawn.setspawn")) {
                        if (sender instanceof Player) {
                            Player p = (Player) sender;
                            World pworld = p.getLocation().getWorld();
                            x = p.getLocation().getX();
                            y = p.getLocation().getY();
                            z = p.getLocation().getZ();
                            yaw = p.getLocation().getYaw();
                            pitch = p.getLocation().getPitch();

                            cfg.addDefault("data.locations." + location + ".world", pworld.getName());
                            cfg.addDefault("data.locations." + location + ".x", x);
                            cfg.addDefault("data.locations." + location + ".y", y);
                            cfg.addDefault("data.locations." + location + ".z", z);
                            cfg.addDefault("data.locations." + location + ".yaw", yaw);
                            cfg.addDefault("data.locations." + location + ".pitch", pitch);
                            cfg.options().copyDefaults(true);
                            plugin.saveDefaultConfig();
                            plugin.loadConfig();

                            chat.send(p, "&7" + location + " set to " + pworld.getName() + ", " + Math.round(x) + ", " + Math.round(y) + ", " + Math.round(z) + ", " + Math.round(yaw) + ", " + Math.round(pitch));
                        } else {
                            chat.send(sender, config.getMessages(ONLYPLAYER));
                        }
                    } else {
                        chat.send(sender, config.getMessages(NOPERM));
                    }
                } else if (command.equalsIgnoreCase("enablespawn")) {
                    if (sender.hasPermission("randomspawn.enable")) {
                        if (cfg.contains("data.locations." + location)) {
                            cfg.set("data.locations." + location + ".disabled", false);
                            chat.send(sender, "&c" + location + " &7enabled.");
                        } else {
                            chat.send(sender, config.getMessages(DOESNTEXIST));
                        }
                    } else {
                        chat.send(sender, config.getMessages(NOPERM));
                    }
                } else if (command.equalsIgnoreCase("disablespawn")) {
                    if (sender.hasPermission("randomspawn.disable")) {
                        if (cfg.contains("data.locations." + location)) {
                            cfg.set("data.locations." + location + ".disabled", true);
                            chat.send(sender, "&c" + location + " &7disabled.");
                        } else {
                            chat.send(sender, config.getMessages(DOESNTEXIST));
                        }
                    } else {
                        chat.send(sender, config.getMessages(NOPERM));
                    }
                } else if (command.equalsIgnoreCase("enablerespawn")) {
                    if (sender.hasPermission("randomspawn.respawn.enable")) {
                        if (cfg.contains("data.locations." + location)) {
                            cfg.set("data.locations." + location + ".respawn", true);
                            chat.send(sender, "&c" + location + " &7respawning enabled.");
                        } else {
                            chat.send(sender, config.getMessages(DOESNTEXIST));
                        }
                    } else {
                        chat.send(sender, config.getMessages(NOPERM));
                    }
                } else if (command.equalsIgnoreCase("disablerespawn")) {
                    if (sender.hasPermission("randomspawn.respawn.disable")) {
                        if (cfg.contains("data.locations." + location)) {
                            cfg.set("data.locations." + location + ".respawn", false);
                            chat.send(sender, "&c" + location + " &7respawning disabled.");
                        } else {
                            chat.send(sender, config.getMessages(DOESNTEXIST));
                        }
                    } else {
                        chat.send(sender, config.getMessages(NOPERM));
                    }
                } else if (command.equalsIgnoreCase("tp")) {
                    if (sender.hasPermission("randomspawn.tp")) {
                        if (sender instanceof Player) {
                            if (cfg.contains("data.locations." + location)) {
                                Player p = (Player) sender;

                                p.teleport(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
                            } else {
                                chat.send(sender, config.getMessages(DOESNTEXIST));
                            }
                        } else {
                            chat.send(sender, config.getMessages(ONLYPLAYER));
                        }
                    } else {
                        chat.send(sender, config.getMessages(NOPERM));
                    }
                } else if (command.equalsIgnoreCase("info")) {
                    if (sender.hasPermission("randomspawn.info")) {
                        if (cfg.contains("data.locations." + location)) {
                            String respawn = String.valueOf(cfg.getBoolean("data.locations." + location + ".respawn"));
                            String disabled = String.valueOf(cfg.getBoolean("data.locations." + location + ".disabled"));
                            Stream.of(
                                    "&7Info for &c" + location,
                                    "&7World: &c" + world,
                                    "&7X: &c" + Math.round(x),
                                    "&7Y: &c" + Math.round(y),
                                    "&7Z: &c" + Math.round(z),
                                    "&7Yaw: &c" + Math.round(yaw),
                                    "&7Pitch: &c" + Math.round(pitch),
                                    "&7Respawn: &c" + respawn,
                                    "&7Disabled: &c" + disabled
                            ).forEach(msg -> chat.sendNoPrefix(sender, msg));
                        } else {
                            chat.send(sender, config.getMessages(DOESNTEXIST));
                        }
                    } else {
                        chat.send(sender, config.getMessages(NOPERM));
                    }
                } else {
                    chat.send(sender, config.getMessages(UNKNOWNSUB));
                }
            } else if (args.length >= 3) {
                chat.send(sender, config.getMessages(TOOMUCHARGS));
            }
        }
        return true;
    }
}
