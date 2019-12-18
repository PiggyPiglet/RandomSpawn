package me.piggypiglet.randomspawn.commands.spawn.edit;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;

import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class AbstractEditCommand extends BukkitCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;

    protected AbstractEditCommand(String command) {
        super(command);
    }

    @Override
    public boolean run(BukkitUser user, String[] args, String handler) {
        final UUID uuid = user.getAsPlayer().getUuid();

        if (!pendingSpawnManager.exists(uuid)) {
            user.sendMessage(Lang.NOT_PENDING);
            return true;
        }

        return super.run(user, args, handler);
    }
}
