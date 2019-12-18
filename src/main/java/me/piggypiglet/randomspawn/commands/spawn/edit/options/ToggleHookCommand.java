package me.piggypiglet.randomspawn.commands.spawn.edit.options;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.edit.AbstractEditCommand;
import me.piggypiglet.randomspawn.data.options.types.hook.HookTypes;
import me.piggypiglet.randomspawn.data.options.types.hook.Hooks;
import me.piggypiglet.randomspawn.lang.Lang;
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
                .usage("[hook] [true/false]");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        final Hooks hooks = pendingSpawnManager.get(user.getAsPlayer().getUuid()).getSpawn().getOptions().getHooks();

        if (args.length == 0) {
            hooks.getHooks().setEnabled(!hooks.getHooks().isEnabled());
            user.sendMessage(Lang.TOGGLED_HOOKS, hooks.getHooks().isEnabled());
            return true;
        }

        final Set<HookTypes> hookList = hooks.getHooks().getValues();
        final HookTypes hook;

        try {
            hook = HookTypes.valueOf(args[0].toUpperCase());
        } catch (Exception e) {
            return false;
        }

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

        return true;
    }
}
