package me.piggypiglet.randomspawn.commands.spawn.edit.options;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.edit.AbstractEditCommand;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SetDistanceFromClaimCommand extends AbstractEditCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;

    public SetDistanceFromClaimCommand() {
        super("edit dfc");
        options
                .playerOnly(true)
                .usage("<value>")
                .description("Set the minimum distance (blocks) from claim for a spawn.")
                .permissions("randomspawn.admin", "randomspawn.edit");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        if (args.length >= 1) {
            final int value;

            try {
                value = Integer.parseInt(args[0]);
            } catch (Exception e) {
                return false;
            }

            pendingSpawnManager.get(user.getAsPlayer().getUuid()).getSpawn().getOptions().getHooks().setDistanceFromClaim(value);
            user.sendMessage(Lang.DISTANCE_SUCCESS, value);

            return true;
        }

        return false;
    }
}
