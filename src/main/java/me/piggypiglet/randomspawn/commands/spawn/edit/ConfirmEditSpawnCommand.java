package me.piggypiglet.randomspawn.commands.spawn.edit;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.create.objects.PendingSpawn;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;
import me.piggypiglet.randomspawn.managers.SpawnManager;

import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ConfirmEditSpawnCommand extends AbstractEditCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;
    @Inject private SpawnManager spawnManager;

    public ConfirmEditSpawnCommand() {
        super("edit confirm");
        options
                .playerOnly(true)
                .usage("")
                .description("Confirm edits on a spawn.")
                .permissions("randomspawn.admin", "randomspawn.edit");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        final UUID uuid = user.getAsPlayer().getUuid();
        final PendingSpawn pending = pendingSpawnManager.get(uuid);
        spawnManager.remove(spawnManager.get(pending.getSpawn().getName()));
        spawnManager.add(pending.getSpawn());
        pendingSpawnManager.remove(pending);
        user.sendMessage(Lang.SUCCESS);

        return true;
    }
}
