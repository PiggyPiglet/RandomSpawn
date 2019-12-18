package me.piggypiglet.randomspawn.commands.spawn.edit.options.hooks;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.edit.AbstractEditCommand;
import me.piggypiglet.randomspawn.data.options.types.hook.Factions;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ToggleFactionsCommand extends AbstractEditCommand {
    @Inject
    private PendingSpawnManager pendingSpawnManager;

    public ToggleFactionsCommand() {
        super("edit factions toggle");
        options
                .playerOnly(true)
                .usage("<key> [value]")
                .description("Toggle a factions hook value for a spawn.")
                .permissions("randomspawn.admin", "randomspawn.edit");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        if (args.length >= 1) {
            args[0] = args[0].toLowerCase();
            final Factions factions = pendingSpawnManager.get(user.getAsPlayer().getUuid()).getSpawn().getOptions().getHooks().getFactions();
            final boolean newVal;
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
