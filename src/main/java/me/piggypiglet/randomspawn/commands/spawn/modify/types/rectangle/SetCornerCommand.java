package me.piggypiglet.randomspawn.commands.spawn.modify.types.rectangle;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.modify.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.spawn.types.RectangleSpawn;
import me.piggypiglet.randomspawn.lang.Lang;
import org.bukkit.Location;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SetCornerCommand extends ModifyModeCommand<RectangleSpawn> {
    public SetCornerCommand() {
        super("set corner");
        options.root()
                .description("Set the corners of your rectangle spawn.")
                .usage("<1/2>");
    }

    @Override
    protected boolean execute(RectangleSpawn spawn, BukkitUser user, String[] args) {
        if (args.length > 0) {
            final boolean equalsOne = args[0].equals("1");
            if (!equalsOne && !args[0].equals("2")) return false;

            final Location location = user.getAsPlayer().getHandle().getLocation();
            final int[] array = new int[] {location.getBlockX(), location.getBlockZ()};

            if (equalsOne) {
                spawn.setCorner1(array);
            } else {
                spawn.setCorner2(array);
            }

            user.sendMessage(Lang.SET_CORNER, args[0], array[0], array[1]);
            return true;
        }

        return false;
    }
}
