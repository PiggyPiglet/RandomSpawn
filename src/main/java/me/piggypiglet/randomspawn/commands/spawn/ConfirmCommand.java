package me.piggypiglet.randomspawn.commands.spawn;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.framework.minecraft.player.Player;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;
import me.piggypiglet.randomspawn.managers.SpawnManager;
import me.piggypiglet.randomspawn.managers.objects.PendingSpawn;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ConfirmCommand extends BukkitCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;
    @Inject private SpawnManager spawnManager;

    public ConfirmCommand() {
        super("confirm");
        options
                .playerOnly(true)
                .permissions("randomspawn.admin", "randomspawn.create", "randomspawn.edit")
                .usage("")
                .description("Confirm the creation/edit of a spawn.");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        final Player<?> player = user.getAsPlayer();

        if (!pendingSpawnManager.exists(player.getUuid())) {
            user.sendMessage(Lang.NOT_PENDING);
            return true;
        }

        final PendingSpawn pendingSpawn = pendingSpawnManager.get(player.getUuid());
        spawnManager.add(pendingSpawn.getSpawn());
        pendingSpawnManager.remove(pendingSpawnManager.get(player.getUuid()));
        user.sendMessage(Lang.SUCCESS);
        return true;
    }
}
