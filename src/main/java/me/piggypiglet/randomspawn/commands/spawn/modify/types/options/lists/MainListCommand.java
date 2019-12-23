package me.piggypiglet.randomspawn.commands.spawn.modify.types.options.lists;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.modify.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.options.OptionTypes;
import me.piggypiglet.randomspawn.data.options.types.list.List;
import me.piggypiglet.randomspawn.data.options.types.list.Lists;
import me.piggypiglet.randomspawn.data.spawn.Spawn;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class MainListCommand extends ModifyModeCommand<Spawn> {
    protected MainListCommand(String command) {
        super(command);
    }

    @Override
    protected boolean execute(Spawn spawn, BukkitUser user, String[] args) {
        if (args.length > 2) {
            final me.piggypiglet.randomspawn.data.options.Options options = spawn.getOptions();
            final OptionTypes type = OptionTypes.from(args[0]);
            final Lists lists;

            switch (type) {
                case BLOCKS:
                    lists = options.getBlocks();
                    break;

                case BIOMES:
                    lists = options.getBiomes();
                    break;

                case WORLDS:
                    lists = options.getWorlds();
                    break;

                case WORLDGUARD:
                    lists = options.getHooks().getWorldGuard();
                    break;

                default:
                    return false;
            }

            final List list;

            switch (args[1].toLowerCase()) {
                case "whitelist":
                    list = lists.getWhitelist();
                    break;

                case "blacklist":
                    list = lists.getBlacklist();
                    break;

                default:
                    return false;
            }

            return execute(type, list, user, args[2]);
        }

        return false;
    }

    protected abstract boolean execute(OptionTypes type, List list, BukkitUser user, String arg);
}
