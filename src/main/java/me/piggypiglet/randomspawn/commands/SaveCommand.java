package me.piggypiglet.randomspawn.commands;

import com.google.inject.Inject;
import me.piggypiglet.framework.bukkit.commands.framework.BukkitCommand;
import me.piggypiglet.framework.bukkit.user.BukkitUser;
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
public final class SaveCommand extends BukkitCommand {
    private final Config config;
    private final ConfigMapper configMapper;

    @Inject
    public SaveCommand(Config config, @Data FileConfiguration data) {
        super("save");
        options.root()
                .permissions("randomspawn.admin", "randomspawn.save")
                .description("Save data in memory to file.")
                .usage("");

        this.config = config;
        configMapper = new ConfigMapper((MutableFileConfiguration) data);
    }

    @Override
    protected boolean execute(BukkitUser user, String[] args) {
        try {
            configMapper.typeToData(config).save();
            user.sendMessage(Lang.SAVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
