package me.piggypiglet.randomspawn.commands.spawn.edit.options.hooks;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.edit.AbstractEditCommand;
import me.piggypiglet.randomspawn.data.options.types.list.List;
import me.piggypiglet.randomspawn.data.options.types.list.Lists;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ToggleWorldguardListCommand extends AbstractEditCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;

    public ToggleWorldguardListCommand() {
        super("edit worldguard toggle");
        options
                .playerOnly(true)
                .permissions("randomspawn.admin", "randomspawn.edit")
                .description("Toggle a list/list value for the worldguard hook on a spawn.")
                .usage("<list> [value]");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        if (args.length >= 1) {
            args[0] = args[0].toLowerCase();
            final Lists worldguard = pendingSpawnManager.get(user.getAsPlayer().getUuid()).getSpawn().getOptions().getHooks().getWorldGuard();
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

                user.sendMessage(Lang.LIST_MODIFICATION, values.contains(args[1]) ? Lang.LIST_MODIFICATION_ADDED : Lang.LIST_MODIFICATION_REMOVED, args[1], args[0]);
            } else {
                list.setEnabled(!list.isEnabled());
                user.sendMessage(Lang.TOGGLED_LIST, args[0], list.isEnabled());
            }

            return true;
        }

        return false;
    }
}
