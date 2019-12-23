package me.piggypiglet.randomspawn.commands.spawn.modify.types.set;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.modify.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.utils.SetUtils;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RemoveLocationCommand extends ModifyModeCommand<SetSpawn> {
    public RemoveLocationCommand() {
        super("location remove");
        options
                .playerOnly(true)
                .permissions("randomspawn.admin", "randomspawn.edit", "randomspawn.create")
                .description("Remove a location from a spawn set.")
                .usage("<id>");
    }

    @Override
    protected boolean execute(SetSpawn spawn, BukkitUser user, String[] args) {
        return SetUtils.removeIndexCommand(args[0], user, spawn.getLocations(), Lang.REMOVED_LOCATION);
    }
}
