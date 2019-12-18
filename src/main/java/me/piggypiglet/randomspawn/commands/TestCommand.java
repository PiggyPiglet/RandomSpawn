package me.piggypiglet.randomspawn.commands;

import com.google.inject.Inject;
import me.piggypiglet.framework.minecraft.commands.framework.MinecraftCommand;
import me.piggypiglet.framework.minecraft.user.MinecraftUser;
import me.piggypiglet.randomspawn.data.Config;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class TestCommand extends MinecraftCommand {
    @Inject private Config config;

    public TestCommand() {
        super("test");
    }

    @Override
    protected boolean execute(MinecraftUser user, String[] args) {
        user.sendMessage(config.getSpawns().get("test").getType());

        return true;
    }
}
