package me.piggypiglet.randomspawn.commands.spawn.modify.types.set;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.modify.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.lang.Lang;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static me.piggypiglet.randomspawn.utils.MathUtils.round;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class AddLocationCommand extends ModifyModeCommand<SetSpawn> {
    public AddLocationCommand() {
        super("location add");
        options
                .playerOnly(true)
                .usage("[name]")
                .permissions("randomspawn.admin", "randomspawn.edit", "randomspawn.create")
                .description("Add a location to your spawn set.");
    }

    @Override
    protected boolean execute(SetSpawn spawn, BukkitUser user, String[] args) {
        final Player player = user.getAsPlayer().getHandle();
        final Location location = player.getLocation();
        spawn.getLocations().add(location);
        user.sendMessage(Lang.ADDED_LOCATION, round(location.getX()), round(location.getY()), round(location.getZ()), round(location.getYaw()), round(location.getPitch()));
        return true;
    }
}
