package me.piggypiglet.randomspawn.commands.spawn.modify.types.options.toggle.hooks;

import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.modify.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.options.types.hook.Factions;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.lang.Lang;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ToggleFactionsCommand extends ModifyModeCommand<Spawn> {
    public ToggleFactionsCommand() {
        super("factions toggle");
        options.root()
                .usage("<key> [value]")
                .description("Toggle a factions hook value for a spawn.");
    }

    @Override
    protected boolean execute(Spawn spawn, BukkitUser user, String[] args) {
        if (args.length >= 1) {
            args[0] = args[0].toLowerCase();
            final Factions factions = spawn.getOptions().getHooks().getFactions();
            Boolean setValue = null;

            if (args.length >= 2) {
                try {
                    setValue = Boolean.valueOf(args[1]);
                } catch (Exception e) {
                    return false;
                }
            }

            switch (args[0]) {
                case "safezone":
                    factions.setSafezone(setValue == null ? !factions.isSafezone() : setValue);
                    break;

                case "warzone":
                    factions.setWarzone(setValue == null ? !factions.isWarzone() : setValue);
                    break;

                case "wilderness":
                    factions.setWilderness(setValue == null ? !factions.isWilderness() : setValue);
                    break;

                case "enemy":
                    factions.setEnemy(setValue == null ? !factions.isEnemy() : setValue);
                    break;

                case "own":
                    factions.setOwn(setValue == null ? !factions.isOwn() : setValue);
                    break;

                default:
                    return false;
            }

            user.sendMessage(Lang.HOOK_UPDATE, args[0], setValue);
            return true;
        }

        return false;
    }
}
