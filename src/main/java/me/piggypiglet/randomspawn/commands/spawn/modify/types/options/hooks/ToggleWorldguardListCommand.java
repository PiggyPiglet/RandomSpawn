package me.piggypiglet.randomspawn.commands.spawn.modify.types.options.hooks;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.modify.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.options.types.list.List;
import me.piggypiglet.randomspawn.data.options.types.list.Lists;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.lang.Lang;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ToggleWorldguardListCommand extends ModifyModeCommand<Spawn> {
    public ToggleWorldguardListCommand() {
        super("worldguard toggle");
        options.root()
                .description("Toggle a list/list value for the worldguard hook on a spawn.")
                .usage("<list> [value]");
    }

    @Override
    protected boolean execute(Spawn spawn, BukkitUser user, String[] args) {
        if (args.length >= 1) {
            args[0] = args[0].toLowerCase();
            final Lists worldguard = spawn.getOptions().getHooks().getWorldGuard();
            final List list;

            if (args[0].equals("whitelist")) {
                list = worldguard.getWhitelist();
            } else if (args[0].equals("blacklist")) {
                list = worldguard.getBlacklist();
            } else {
                return false;
            }

            if (args.length >= 2) {
                final Set<String> values = list.getValues();
                args[1] = args[1].toLowerCase();

                if (values.contains(args[1])) {
                    values.remove(args[1]);
                } else {
                    values.add(args[1]);
                }

                user.sendMessage(values.contains(args[1]) ? Lang.LIST_MODIFICATION_ADDED : Lang.LIST_MODIFICATION_REMOVED, args[1], args[0]);
            } else {
                list.setEnabled(!list.isEnabled());
                user.sendMessage(Lang.TOGGLED_LIST, args[0], list.isEnabled());
            }

            return true;
        }

        return false;
    }
}
