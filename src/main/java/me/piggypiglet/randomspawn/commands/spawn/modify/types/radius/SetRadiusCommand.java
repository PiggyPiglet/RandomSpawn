package me.piggypiglet.randomspawn.commands.spawn.modify.types.radius;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.modify.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.spawn.types.RadiusSpawn;
import me.piggypiglet.randomspawn.lang.Lang;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SetRadiusCommand extends ModifyModeCommand<RadiusSpawn> {
    public SetRadiusCommand() {
        super("set radius");
        options.root()
                .description("Set a radius for your radius spawn.")
                .usage("<radius>");
    }

    @Override
    protected boolean execute(RadiusSpawn spawn, BukkitUser user, String[] args) {
        if (args.length > 0) {
            final int radius;

            try {
                radius = Integer.parseInt(args[0]);
            } catch (Exception e) {
                return false;
            }

            spawn.setRadius(radius);
            user.sendMessage(Lang.SET_RADIUS, radius);
            return true;
        }

        return false;
    }
}
