package me.piggypiglet.randomspawn.commands;

import lombok.Getter;
import me.piggypiglet.randomspawn.commands.implementations.HelpCommand;
import me.piggypiglet.randomspawn.commands.implementations.SetSpawnCommand;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Commands {
    SETSPAWN(SetSpawnCommand.class),
    HELP(HelpCommand.class);

    @Getter private final Class<? extends Command> clazz;

    Commands(final Class<? extends Command> clazz) {
        this.clazz = clazz;
    }
}
