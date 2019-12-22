package me.piggypiglet.randomspawn.commands.spawn.info;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.framework.utils.StringUtils;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.RadiusSpawn;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;
import me.piggypiglet.randomspawn.managers.SpawnManager;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static me.piggypiglet.randomspawn.utils.MathUtils.round;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class InfoCommand extends BukkitCommand {
    @Inject private SpawnManager spawnManager;
    @Inject private PendingSpawnManager pendingSpawnManager;

    public InfoCommand() {
        super("info");
        options.root()
                .usage("[name]")
                .description("Get info on a spawn.")
                .permissions("randomspawn.default", "randomspawn.info");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        final UUID uuid = user.getAsPlayer().getUuid();
        final Spawn spawn;

        if (pendingSpawnManager.exists(uuid)) {
            spawn = pendingSpawnManager.get(uuid).getSpawn();
        } else {
            if (args.length == 0) return false;

            spawn = spawnManager.get(args[0].toLowerCase());
        }

        final String addendum;

        if (spawn instanceof SetSpawn) {
            final AtomicInteger i = new AtomicInteger();
            final List<String> locations = ((SetSpawn) spawn).getLocations().stream()
                    .map(l -> StringUtils.format(Lang.INFO_SET_FORMAT, i.getAndIncrement(), round(l.getX()), round(l.getY()), round(l.getZ()), round(l.getYaw()), round(l.getPitch())))
                    .collect(Collectors.toList());

            addendum = StringUtils.format(Lang.INFO_SET_ADDENDUM, "\n" + String.join("\n", locations));
        } else {
            final RadiusSpawn radiusSpawn = (RadiusSpawn) spawn;
            final double[] center = radiusSpawn.getCenter();
            addendum = StringUtils.format(Lang.INFO_RADIUS_ADDENDUM, round(center[0]), round(center[1]), radiusSpawn.getRadius());
        }

        user.sendMessage(Lang.INFO_HEADER, "\n", Lang.INFO_FORMAT, "\n", addendum, "\n", Lang.INFO_FOOTER,
                spawn.getName(),
                spawn.getType(),
                spawn.getPermission(),
                spawn.getWorld().getName(),
                spawn.isEnabled()
        );

        return true;
    }
}
