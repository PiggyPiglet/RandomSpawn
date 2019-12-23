package me.piggypiglet.randomspawn.commands.spawn.modify.edit;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;
import me.piggypiglet.randomspawn.managers.SpawnManager;
import me.piggypiglet.randomspawn.managers.objects.PendingSpawn;

import java.util.List;
import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class EditSpawnCommand extends BukkitCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;
    @Inject private SpawnManager spawnManager;

    public EditSpawnCommand() {
        super("edit");
        options
                .playerOnly(true)
                .usage("<name>")
                .permissions("randomspawn.admin", "randomspawn.edit")
                .description("Edit a spawn.");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        if (args.length >= 1) {
            final UUID uuid = user.getAsPlayer().getUuid();
            final String name = args[0];

            if (pendingSpawnManager.exists(uuid)) {
                user.sendMessage(Lang.IN_PENDING);
                return true;
            }

            if (!spawnManager.exists(name)) {
                final List<Spawn> spawns = spawnManager.search(name);

                if (spawns.isEmpty()) {
                    user.sendMessage(Lang.NO_SPAWNS);
                } else {
                    user.sendMessage(Lang.UNKNOWN_SPAWN, spawns.get(0).getName());
                }

                return true;
            }

            final Spawn spawn = spawnManager.get(name);
            pendingSpawnManager.add(new PendingSpawn(uuid, spawn.dupe()));
            user.sendMessage(Lang.EDIT_ENABLED);
            return true;
        }

        return false;
    }
}
