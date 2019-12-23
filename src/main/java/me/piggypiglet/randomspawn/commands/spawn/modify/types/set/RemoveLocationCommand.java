package me.piggypiglet.randomspawn.commands.spawn.modify.types.set;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.modify.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.lang.Lang;
import org.bukkit.Location;

import java.util.Iterator;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RemoveLocationCommand extends ModifyModeCommand<SetSpawn> {
    public RemoveLocationCommand() {
        super("location remove");
        options
                .playerOnly(true)
                .permissions("randomspawn.admin", "randomspawn.edit", "randomspawn.create")
                .description("Remove a location from a spawn set.")
                .usage("<id>");
    }

    @Override
    protected boolean execute(SetSpawn spawn, BukkitUser user, String[] args) {
        final Set<Location> locations = spawn.getLocations();
        final int index;

        try {
            index = Integer.parseInt(args[0]);
        } catch (Exception e) {
            return false;
        }

        if (locations.size() < index + 1) {
            user.sendMessage(Lang.INDEX_TOO_BIG, index, locations.size() - 1);
            return true;
        }


        int i = 0;
        for (Iterator<Location> iterator = locations.iterator(); iterator.hasNext(); i++) {
            iterator.next();

            if (i == index) {
                iterator.remove();
                break;
            }
        }

        user.sendMessage(Lang.REMOVED_LOCATION, i);

        return true;
    }
}
