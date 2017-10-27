package me.piggypiglet.randomspawn.handlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2017
// https://www.piggypiglet.me
// ------------------------------
public class CommandHandler implements CommandExecutor {
    private ChatHandler chh;
    private void makeTab(String args, List<String> command, Enum[] values) {
        for (Enum commd : values) {
            String lowerName = commd.name().toLowerCase();
            if (lowerName.startsWith(args)) {
                command.add(lowerName);
            }
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return true;
    }
}
