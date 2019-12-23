package me.piggypiglet.randomspawn.commands.spawn.modify;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.framework.utils.clazz.ClassUtils;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.Spawns;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;

import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class ModifyModeCommand<T extends Spawn> extends BukkitCommand {
    private final Class<T> type;

    @Inject protected PendingSpawnManager pendingSpawnManager;

    @SuppressWarnings("unchecked")
    protected ModifyModeCommand(String command) {
        super(command);

        Class<T> type;

        try {
            type = ClassUtils.getImplementedGeneric(getClass());
        } catch (Exception e) {
            type = (Class<T>) Spawn.class;
        }

        this.type = type;

        options
                .playerOnly(true)
                .permissions("randomspawn.admin", "randomspawn.create", "randomspawn.edit");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean execute(BukkitUser user, String[] args) {
        final UUID uuid = user.getAsPlayer().getUuid();

        if (!pendingSpawnManager.exists(uuid)) {
            user.sendMessage(Lang.NOT_PENDING);
            return true;
        }

        final Spawn spawn = pendingSpawnManager.get(uuid).getSpawn();

        if (type != Spawn.class && !type.isInstance(spawn)) {
            switch (Spawns.from(spawn.getClass())) {
                case SET:
                    user.sendMessage(Lang.NOT_SET_SPAWN);
                    break;

                case CIRCLE:
                case SQUARE:
                    user.sendMessage(Lang.NOT_RADIUS_SPAWN);
                    break;
            }

            return true;
        }

        return execute((T) spawn, user, args);
    }

    protected abstract boolean execute(T spawn, BukkitUser user, String[] args);
}
