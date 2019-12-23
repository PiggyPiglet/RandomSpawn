package me.piggypiglet.randomspawn.commands.spawn.modify.types.options.toggle;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.modify.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.options.types.hook.HookTypes;
import me.piggypiglet.randomspawn.data.options.types.hook.Hooks;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.lang.Lang;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ToggleHookCommand extends ModifyModeCommand<Spawn> {
    public ToggleHookCommand() {
        super("hook toggle");
        options.root()
                .description("Toggle a hook for a spawn.")
                .usage("[hook] [true/false]");
    }

    @Override
    protected boolean execute(Spawn spawn, BukkitUser user, String[] args) {
        final Hooks hooks = spawn.getOptions().getHooks();

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
