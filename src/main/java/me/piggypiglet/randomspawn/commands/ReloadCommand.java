package me.piggypiglet.randomspawn.commands;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
import me.piggypiglet.framework.file.FileManager;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.file.framework.MutableFileConfiguration;
import me.piggypiglet.randomspawn.data.Config;
import me.piggypiglet.randomspawn.data.Data;
import me.piggypiglet.randomspawn.lang.Lang;
import me.piggypiglet.randomspawn.mappers.ConfigMapper;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ReloadCommand extends BukkitCommand {
    @Inject private FileManager fileManager;
    @Inject private Config config;

    private final MutableFileConfiguration data;
    private final ConfigMapper configMapper;

    @Inject
    public ReloadCommand(@Data FileConfiguration data) {
        super("reload");
        options.root()
                .usage("")
                .description("Sync hard data to memory.")
                .permissions("randomspawn.admin", "randomspawn.reload");

        this.data = (MutableFileConfiguration) data;
        this.configMapper = new ConfigMapper(this.data);
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        try {
            fileManager.update("data");
            config.set(configMapper.dataToType(data));
        } catch (Exception e) {
            user.sendMessage(Lang.RELOAD_FAILURE);
            e.printStackTrace();
            return true;
        }

        user.sendMessage(Lang.RELOAD_SUCCESS);
        return true;
    }
}
