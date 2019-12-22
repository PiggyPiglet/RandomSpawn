package me.piggypiglet.randomspawn.commands.spawn.types.radius;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.RadiusSpawn;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static me.piggypiglet.randomspawn.utils.MathUtils.round;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SetCenterCommand extends ModifyModeCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;

    public SetCenterCommand() {
        super("set center");
        options
                .playerOnly(true)
                .permissions("randomspawn.admin", "randomspawn.edit", "randomspawn.create")
                .description("Set the center of your radius spawn.")
                .usage("");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        final Player player = user.getAsPlayer().getHandle();
        final Spawn spawn = pendingSpawnManager.get(player.getUniqueId()).getSpawn();

        if (!(spawn instanceof RadiusSpawn)) {
            user.sendMessage(Lang.NOT_RADIUS_SPAWN);
            return true;
        }

        final Location location = player.getLocation();
        ((RadiusSpawn) spawn).setCenter(new double[] {location.getX(), location.getZ()});
        user.sendMessage(Lang.SET_CENTER, round(location.getX()), round(location.getZ()));
        return true;
    }
}
