package me.piggypiglet.randomspawn.commands;

import lombok.Getter;
import me.piggypiglet.randomspawn.commands.implementations.*;
import me.piggypiglet.randomspawn.commands.implementations.edit.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Commands {
    SETSPAWN(SetSpawnCommand.class),
    DELETESPAWN(DeleteSpawnCommand.class),
    ENABLESPAWN(EnableSpawnCommand.class),
    DISABLESPAWN(DisableSpawnCommand.class),
    ENABLERESPAWN(EnableRespawnCommand.class),
    DISABLERESPAWN(DisableRespawnCommand.class),

    HELP(HelpCommand.class),
    INFO(InfoCommand.class),
    LIST(ListCommand.class),
    TELEPORT(TeleportCommand.class),
    RELOAD(ReloadCommand.class);

    @Getter private final Class<? extends Command> clazz;

    Commands(final Class<? extends Command> clazz) {
        this.clazz = clazz;
    }
}
