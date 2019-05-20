package me.piggypiglet.randomspawn.commands;

import lombok.Getter;
import org.bukkit.command.CommandSender;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Command {
    @Getter private final String command;
    @Getter private final String description;
    @Getter private final String usage;
    @Getter private final boolean playerOnly;
    @Getter private final String[] permissions;

    protected Command(final String command, final String description, final String usage, String... permissions) {
        this(command, description, usage, false, permissions);
    }

    protected Command(final String command, final String description, final String usage, final boolean playerOnly, String... permissions) {
        this.command = command;
        this.description = description;
        this.usage = usage;
        this.playerOnly = playerOnly;
        this.permissions = permissions;
    }

    protected abstract boolean execute(CommandSender sender, String[] args);

    public boolean run(CommandSender sender, String[] args) {
        return execute(sender, args);
    }
}
