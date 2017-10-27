package me.piggypiglet.rs.Handlers;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static me.piggypiglet.rs.Enums.Messages.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2017
// https://www.piggypiglet.me
// ------------------------------
public class ChatHandler {
    private String prefix;

    public ChatHandler() {
        ConfigHandler config = new ConfigHandler();
        prefix = config.getMessages(PREFIX);
    }

    public void send(CommandSender sender, String txt) {
        sender.sendMessage(cc(prefix + txt));
    }
    public void sendNoPrefix(CommandSender sender, String txt) {
        sender.sendMessage(cc(txt));
    }

    private String cc(String txt) {
        return ChatColor.translateAlternateColorCodes('&', txt);
    }

}
