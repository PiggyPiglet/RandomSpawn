package me.piggypiglet.randomspawn.registerables;

import com.google.inject.Inject;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.file.framework.MutableFileConfiguration;
import me.piggypiglet.framework.registerables.StartupRegisterable;
import me.piggypiglet.framework.task.Task;
import me.piggypiglet.randomspawn.data.Data;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ConfigSaveRegisterable extends StartupRegisterable {
    @Inject private Task task;
    @Inject @Data private FileConfiguration config;

    @Override
    protected void execute() {
        task.async(r -> {
            try {
                ((MutableFileConfiguration) config).save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "10 mins", true);
    }
}
