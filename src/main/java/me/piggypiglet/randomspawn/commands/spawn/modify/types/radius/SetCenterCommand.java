package me.piggypiglet.randomspawn.commands.spawn.modify.types.radius;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.framework.minecraft.world.location.Location;
import me.piggypiglet.randomspawn.commands.spawn.modify.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.spawn.types.RadiusSpawn;
import me.piggypiglet.randomspawn.lang.Lang;

import static me.piggypiglet.randomspawn.utils.MathUtils.round;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SetCenterCommand extends ModifyModeCommand<RadiusSpawn> {
    public SetCenterCommand() {
        super("set center");
        options.root()
                .description("Set the center of your radius spawn.")
                .usage("");
    }

    @Override
    protected boolean execute(RadiusSpawn spawn, BukkitUser user, String[] args) {
        final Location location = user.getAsPlayer().getLocation();

        spawn.setCenter(new double[] {location.getX(), location.getZ()});
        user.sendMessage(Lang.SET_CENTER, round(location.getX()), round(location.getZ()));
        return true;
    }
}
