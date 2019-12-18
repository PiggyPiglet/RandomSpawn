package me.piggypiglet.randomspawn.commands.spawn.edit.options;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.edit.AbstractEditCommand;
import me.piggypiglet.randomspawn.data.options.types.hook.Hooks;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ToggleHookCommand extends AbstractEditCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;

    public ToggleHookCommand() {
        super("edit hook toggle");
        options
                .playerOnly(true)
                .description("Toggle a hook for a spawn.")
                .usage("<hook> [true/false]");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        if (args.length >= 1) {
            final Hooks hooks = pendingSpawnManager.get(user.getAsPlayer().getUuid()).getSpawn().getOptions().getHooks();
            final Set<String> hookList = hooks.getHooks().getValues();
            final String hook = args[0];
            final boolean value;

            if (args.length >= 2) {
                try {
                    value = Boolean.parseBoolean(args[1]);
                } catch (Exception e) {
                    return false;
                }
            } else {
                value = hookList.contains(hook);
            }

            if (value) {
                hookList.remove(hook);
            } else {
                hookList.add(hook);
            }
        }

        return false;
    }
}
