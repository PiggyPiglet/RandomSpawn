package me.piggypiglet.randomspawn.commands.spawn.modify.create;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.data.Config;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.RadiusSpawn;
import me.piggypiglet.randomspawn.data.spawn.types.RectangleSpawn;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.data.spawn.types.Spawns;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;
import me.piggypiglet.randomspawn.managers.SpawnManager;
import me.piggypiglet.randomspawn.managers.objects.PendingSpawn;
import me.piggypiglet.randomspawn.mappers.spawns.data.SpawnData;
import org.bukkit.entity.Player;

import java.util.LinkedHashSet;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class NewSpawnCommand extends BukkitCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;
    @Inject private SpawnManager spawnManager;
    @Inject private Config config;

    public NewSpawnCommand() {
        super("create");
        options
                .playerOnly(true)
                .permissions("randomspawn.admin", "randomspawn.create")
                .usage("<name> <type>")
                .description("Create a spawn.");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        final Player player = user.getAsPlayer().getHandle();

        if (pendingSpawnManager.exists(player.getUniqueId())) {
            user.sendMessage(Lang.IN_PENDING);
            return true;
        }

        if (args.length > 1) {
            final String name = args[0];

            if (spawnManager.exists(name)) {
                user.sendMessage(Lang.SPAWN_ALREADY_EXISTS, name);
                return true;
            }

            final Spawns type;

            try {
                type = Spawns.valueOf(args[1].toUpperCase());
            } catch (Exception e) {
                return false;
            }

            Spawn spawn = new SpawnData(name, type, "randomspawn.spawns." + name, true, player.getWorld(), config.getOptions());
            final Lang set;

            switch (type) {
                case SET:
                    set = Lang.SET_SET;
                    spawn = new SetSpawn(
                            spawn.getName(), spawn.getType(), spawn.getPermission(), spawn.isEnabled(), spawn.getWorld(), spawn.getOptions(), new LinkedHashSet<>()
                    );
                    break;

                case SQUARE:
                case CIRCLE:
                    set = Lang.RADIUS_SET;
                    spawn = new RadiusSpawn(
                            spawn.getName(), spawn.getType(), spawn.getPermission(), spawn.isEnabled(), spawn.getWorld(), spawn.getOptions(),
                            new int[]{}, 0
                    );
                    break;

                case RECTANGLE:
                    set = Lang.RECTANGLE_SET;
                    spawn = new RectangleSpawn(
                            spawn.getName(), spawn.getType(), spawn.getPermission(), spawn.isEnabled(), spawn.getWorld(), spawn.getOptions(),
                            new int[]{}, new int[]{}
                    );
                    break;

                default:
                    return false;
            }

            user.sendMessage(set);
            user.sendMessage(Lang.CREATE_ENABLED);
            pendingSpawnManager.add(new PendingSpawn(player.getUniqueId(), spawn));
            user.sendMessage(Lang.CONFIRM);
            return true;
        }

        return false;
    }
}
