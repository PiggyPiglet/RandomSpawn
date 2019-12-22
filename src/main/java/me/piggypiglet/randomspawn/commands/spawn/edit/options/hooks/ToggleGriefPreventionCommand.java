package me.piggypiglet.randomspawn.commands.spawn.edit.options.hooks;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.commands.spawn.ModifyModeCommand;
import me.piggypiglet.randomspawn.data.options.types.hook.GriefPrevention;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.PendingSpawnManager;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ToggleGriefPreventionCommand extends ModifyModeCommand {
    @Inject private PendingSpawnManager pendingSpawnManager;

    public ToggleGriefPreventionCommand() {
        super("griefprevention toggle");
        options
                .playerOnly(true)
                .description("Toggle a griefprevention hook value for a spawn.")
                .usage("<key> [value]")
                .permissions("randomspawn.admin", "randomspawn.edit");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        if (args.length >= 1) {
            args[0] = args[0].toLowerCase();
            final GriefPrevention griefPrevention = pendingSpawnManager.get(user.getAsPlayer().getUuid()).getSpawn().getOptions().getHooks().getGriefPrevention();
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
                case "own":
                    griefPrevention.setOwn(setValue == null ? !griefPrevention.isOwn() : setValue);
                    break;

                case "other":
                    griefPrevention.setOther(setValue == null ? !griefPrevention.isOther() : setValue);
                    break;

                case "wilderness":
                    griefPrevention.setWilderness(setValue == null ? !griefPrevention.isWilderness() : setValue);
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
