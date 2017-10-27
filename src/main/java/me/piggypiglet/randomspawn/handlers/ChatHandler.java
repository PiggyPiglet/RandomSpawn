package me.piggypiglet.randomspawn.handlers;

import me.piggypiglet.randomspawn.enums.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static me.piggypiglet.randomspawn.enums.Messages.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2017
// https://www.piggypiglet.me
// ------------------------------
public class ChatHandler {
    private ConfigHandler coh;

    public ChatHandler() {
        coh = new ConfigHandler();
    }
    public void send(CommandSender sender, Messages msg, boolean prefix, boolean list) {
        String str1 = prefix ? coh.getMessages(PREFIX) + coh.getMessages(msg) : coh.getMessages(msg);
        String str = list ? str1.replace("[", "").replace("]", "").replace(",", "\n") : str1;
        sender.sendMessage(cc(str));
    }
    public String cc(String txt) {
        return ChatColor.translateAlternateColorCodes('&', txt);
    }
}
