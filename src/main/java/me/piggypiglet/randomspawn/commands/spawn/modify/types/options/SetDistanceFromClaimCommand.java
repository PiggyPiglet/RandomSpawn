package me.piggypiglet.randomspawn.commands.spawn.modify.types.options;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.modify.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.lang.Lang;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SetDistanceFromClaimCommand extends ModifyModeCommand<Spawn> {
    public SetDistanceFromClaimCommand() {
        super("dfc");
        options.root()
                .usage("<value>")
                .description("Set the minimum distance (blocks) from claim for a spawn.");
    }

    @Override
    protected boolean execute(Spawn spawn, BukkitUser user, String[] args) {
        if (args.length >= 1) {
            final int value;

            try {
                value = Integer.parseInt(args[0]);
            } catch (Exception e) {
                return false;
            }

            spawn.getOptions().getHooks().setDistanceFromClaim(value);
            user.sendMessage(Lang.DISTANCE_SUCCESS, value);

            return true;
        }

        return false;
    }
}
