package me.piggypiglet.randomspawn.commands.spawn.types.set;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static me.piggypiglet.randomspawn.utils.MathUtils.round;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class AddLocationCommand extends ModifyModeCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;

    public AddLocationCommand() {
        super("location add");
        options
                .playerOnly(true)
                .usage("[name]")
                .permissions("randomspawn.admin", "randomspawn.edit", "randomspawn.create")
                .description("Add a location to your spawn set.");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        final Player player = user.getAsPlayer().getHandle();
        final Spawn spawn = pendingSpawnManager.get(player.getUniqueId()).getSpawn();

        if (!(spawn instanceof SetSpawn)) {
            user.sendMessage(Lang.NOT_SET_SPAWN);
            return true;
        }

        final Location location = player.getLocation();
        ((SetSpawn) spawn).getLocations().add(location);
        user.sendMessage(Lang.ADDED_LOCATION, round(location.getX()), round(location.getY()), round(location.getZ()), round(location.getYaw()), round(location.getPitch()));
        return true;
    }

}
