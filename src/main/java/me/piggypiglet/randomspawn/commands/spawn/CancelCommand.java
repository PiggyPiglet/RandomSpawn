package me.piggypiglet.randomspawn.commands.spawn;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.framework.minecraft.player.Player;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class CancelCommand extends BukkitCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;

    public CancelCommand() {
        super("cancel");
        options
                .playerOnly(true)
                .permissions("randomspawn.admin", "randomspawn.create", "randomspawn.edit")
                .usage("")
                .description("Exit out of create/edit mode.");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        final Player<?> player = user.getAsPlayer();

        if (!pendingSpawnManager.exists(player.getUuid())) {
            user.sendMessage(Lang.NOT_PENDING);
            return true;
        }

        pendingSpawnManager.remove(pendingSpawnManager.get(player.getUuid()));
        user.sendMessage(Lang.CANCEL_SUCCESS);
        return true;
    }
}
