package me.piggypiglet.randomspawn.commands.spawn.types.radius;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.RadiusSpawn;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SetRadiusCommand extends ModifyModeCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;

    public SetRadiusCommand() {
        super("set radius");
        options
                .playerOnly(true)
                .description("Set a radius for your radius spawn.")
                .usage("<radius>")
                .permissions("randomspawn.admin", "randomspawn.edit", "randomspawn.create");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        if (args.length > 0) {
            final Spawn spawn = pendingSpawnManager.get(user.getAsPlayer().getUuid()).getSpawn();

            if (!(spawn instanceof RadiusSpawn)) {
                user.sendMessage(Lang.NOT_RADIUS_SPAWN);
                return true;
            }

            final int radius;

            try {
                radius = Integer.parseInt(args[0]);
            } catch (Exception e) {
                return false;
            }

            ((RadiusSpawn) spawn).setRadius(radius);
            user.sendMessage(Lang.SET_RADIUS, radius);
            return true;
        }

        return false;
    }
}
