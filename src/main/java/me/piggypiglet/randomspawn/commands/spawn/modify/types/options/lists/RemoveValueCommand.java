package me.piggypiglet.randomspawn.commands.spawn.modify.types.options.lists;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.data.options.OptionTypes;
import me.piggypiglet.randomspawn.data.options.types.list.List;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.utils.SetUtils;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RemoveValueCommand extends MainListCommand {
    public RemoveValueCommand() {
        super("modify list remove");
        options.root()
                .usage("<option> <list> <index>")
                .description("Remove a value from a list on an option.");
    }

    @Override
    protected boolean execute(OptionTypes type, List list, BukkitUser user, String arg) {
        return SetUtils.removeIndexCommand(arg, user, list.getValues(), Lang.LIST_REMOVED);
    }
}
