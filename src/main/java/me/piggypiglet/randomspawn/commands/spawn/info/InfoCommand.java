package me.piggypiglet.randomspawn.commands.spawn.info;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.framework.lang.LangEnum;
import me.piggypiglet.framework.utils.StringUtils;
import me.piggypiglet.randomspawn.data.options.OptionTypes;
import me.piggypiglet.randomspawn.data.options.types.hook.Factions;
import me.piggypiglet.randomspawn.data.options.types.hook.GriefPrevention;
import me.piggypiglet.randomspawn.data.options.types.hook.HookList;
import me.piggypiglet.randomspawn.data.options.types.hook.HookTypes;
import me.piggypiglet.randomspawn.data.options.types.list.List;
import me.piggypiglet.randomspawn.data.options.types.list.Lists;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.RadiusSpawn;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;
import me.piggypiglet.randomspawn.managers.SpawnManager;

import java.util.Arrays;
import java.util.Set;
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
                .usage("[name/option/hook] [option/hook]")
                .description("Get info on a spawn.")
                .permissions("randomspawn.default", "randomspawn.info");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        final Spawn spawn;

        if (user.isPlayer() && pendingSpawnManager.exists(user.getAsPlayer().getUuid())) {
            spawn = pendingSpawnManager.get(user.getAsPlayer().getUuid()).getSpawn();
        } else {
            if (args.length == 0) return false;

            spawn = spawnManager.get(args[0].toLowerCase());
            args = Arrays.stream(args)
                    .skip(1)
                    .toArray(String[]::new);
        }

        if (args.length > 0) {
            final me.piggypiglet.randomspawn.data.options.Options options = spawn.getOptions();

            final LangEnum key;
            final String body;

            switch (OptionTypes.from(args[0])) {
                case BLOCKS:
                    key = Lang.INFO_OTHER_BLOCKS;
                    body = formatLists(options.getBlocks());
                    break;

                case BIOMES:
                    key = Lang.INFO_OTHER_BIOMES;
                    body = formatLists(options.getBiomes());
                    break;

                case WORLDS:
                    key = Lang.INFO_OTHER_WORLDS;
                    body = formatLists(options.getWorlds());
                    break;

                case HOOKS:
                    final HookList hooks = options.getHooks().getHooks();
                    key = Lang.INFO_OTHER_HOOKS;
                    body = StringUtils.format(Lang.INFO_OTHER_HOOKS_BODY, hooks.isEnabled(), formatSet(hooks.getValues().stream().map(HookTypes::toString).collect(Collectors.toSet())));
                    break;

                case WORLDGUARD:
                    key = Lang.INFO_OTHER_WORLDGUARD;
                    body = formatLists(options.getHooks().getWorldGuard());
                    break;

                case FACTIONS:
                    final Factions factions = options.getHooks().getFactions();
                    key = Lang.INFO_OTHER_FACTIONS;
                    body = StringUtils.format(Lang.INFO_OTHER_FACTIONS_FORMAT, factions.isSafezone(), factions.isWarzone(), factions.isWilderness(), factions.isEnemy(), factions.isOwn());
                    break;

                case GRIEFPREVENTION:
                    final GriefPrevention griefPrevention = options.getHooks().getGriefPrevention();
                    key = Lang.INFO_OTHER_GRIEFPREVENTION;
                    body = StringUtils.format(Lang.INFO_OTHER_GRIEFPREVENTION_FORMAT, griefPrevention.isOwn(), griefPrevention.isOther(), griefPrevention.isWilderness());
                    break;

                default:
                    return false;
            }

            user.sendMessage(Lang.INFO_OTHER_HEADER, "\n%s\n", Lang.INFO_OTHER_FOOTER, spawn.getName(), key, body);
            return true;
        }

        final String addendum;

        if (spawn instanceof SetSpawn) {
            final AtomicInteger i = new AtomicInteger();
            final Set<String> locations = ((SetSpawn) spawn).getLocations().stream()
                    .map(l -> StringUtils.format(Lang.INFO_SET_FORMAT, i.getAndIncrement(), round(l.getX()), round(l.getY()), round(l.getZ()), round(l.getYaw()), round(l.getPitch())))
                    .collect(Collectors.toSet());

            addendum = StringUtils.format(Lang.INFO_SET_ADDENDUM, "\n" + String.join("\n", locations));
        } else {
            final RadiusSpawn radiusSpawn = (RadiusSpawn) spawn;
            final int[] center = radiusSpawn.getCenter();
            addendum = StringUtils.format(Lang.INFO_RADIUS_ADDENDUM, round(center[0]), round(center[1]), radiusSpawn.getRadius());
        }

        user.sendMessage(Lang.INFO_HEADER, "\n", Lang.INFO_FORMAT, "\n", addendum, "\n", Lang.INFO_FOOTER,
                spawn.getName(),
                spawn.getType(),
                spawn.getPermission(),
                spawn.getWorld().getName(),
                spawn.isEnabled(),
                spawn.getOptions().isRespawn(),
                spawn.getOptions().isSafeLocation()
        );

        return true;
    }

    private String formatLists(Lists lists) {
        final List whitelist = lists.getWhitelist();
        final List blacklist = lists.getBlacklist();

        return StringUtils.format(Lang.INFO_OTHER_LIST_BODY, whitelist.isEnabled(), formatSet(whitelist.getValues()), blacklist.isEnabled(), formatSet(blacklist.getValues()));
    }

    private String formatSet(Set<String> values) {
        final AtomicInteger i = new AtomicInteger();
        return "\n" + values.stream().map(s -> StringUtils.format(Lang.INFO_OTHER_LIST_FORMAT, i.getAndIncrement(), s)).collect(Collectors.joining("\n"));
    }
}
