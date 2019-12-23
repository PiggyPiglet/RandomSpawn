package me.piggypiglet.randomspawn.commands.spawn.modify.types.options.lists;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.data.options.OptionTypes;
import me.piggypiglet.randomspawn.data.options.types.list.List;
import me.piggypiglet.randomspawn.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class AddValueCommand extends MainListCommand {
    public AddValueCommand() {
        super("modify list add");
        options.root()
                .usage("<option> <list> <value>")
                .description("Add a value to a list on an option.");
    }

    @Override
    protected boolean execute(OptionTypes type, List list, BukkitUser user, String arg) {
        try {
            switch (type) {
                case BLOCKS:
                    Material.valueOf(arg.toUpperCase());
                    break;

                case BIOMES:
                    Biome.valueOf(arg.toUpperCase());
                    break;

                case WORLDS:
                    Bukkit.getWorld(arg);
                    break;
            }
        } catch (Exception e) {
            return false;
        }

        list.getValues().add(arg);
        user.sendMessage(Lang.LIST_ADDED, arg);

        return true;
    }
}
