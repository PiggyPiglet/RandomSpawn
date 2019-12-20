package me.piggypiglet.randomspawn.commands.spawn.info;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.managers.SpawnManager;

import java.util.Map;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ListCommand extends BukkitCommand {
    @Inject private SpawnManager spawnManager;

    public ListCommand() {
        super("list");
        options.root()
                .permissions("randomspawn.default", "randomspawn.list")
                .usage("")
                .description("List all spawns");
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        final Map<String, String> spawns = spawnManager.getAll().stream()
                .collect(Collectors.toMap(Spawn::getName, s -> s.getType().toString()));

        user.sendMessage(Lang.LIST_HEADER);
        spawns.forEach((n, t) -> user.sendMessage(Lang.LIST_FORMAT, n, t));
        user.sendMessage(Lang.LIST_FOOTER);
        return true;
    }
}
