package me.piggypiglet.randomspawn.commands.spawn.create;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class AddLocationCommand extends BukkitCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;

    public AddLocationCommand() {
        super("create location add");
        options
                .playerOnly(true)
                .usage("")
                .permissions("randomspawn.admin", "randomspawn.create")
                .description("Add a location to your spawn set, part of creation process.");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        final Player player = user.getAsPlayer().getHandle();
        final UUID uuid = player.getUniqueId();

        if (!pendingSpawnManager.exists(uuid)) {
            user.sendMessage(Lang.NOT_PENDING);
            return true;
        }

        final Spawn spawn = pendingSpawnManager.get(uuid).getSpawn();

        if (!(spawn instanceof SetSpawn)) {
            user.sendMessage(Lang.NOT_SET_SPAWN);
            return true;
        }

        final Location location = player.getLocation();
        ((SetSpawn) spawn).getLocations().add(location);
        user.sendMessage(Lang.ADDED_LOCATION, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        return true;
    }
}
