package me.piggypiglet.randomspawn.file;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Singleton;
import me.piggypiglet.randomspawn.RandomSpawn;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class FileManager {
    private final Multimap<String, Object> configs = ArrayListMultimap.create();

    public void copy(String name, String externalPath, String internalPath) throws Exception {
        File file = new File(externalPath);

        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();

            if (!file.createNewFile() || !exportResource(RandomSpawn.class.getResourceAsStream(internalPath), externalPath)) {
                throw new IOException("Something went wrong when creating " + name + ", try deleting the file (if it was created) then restarting the server. If it fails again, ask for help in https://piggypiglet.me/discord");
            }
        }

        load(name, file);
    }

    public void save(String name) {
        try {
            getConfig(name).save((File) new ArrayList<>(configs.get(name)).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig(String name) {
        return (FileConfiguration) new ArrayList<>(configs.get(name)).get(1);
    }

    private void load(String name, File file) throws Exception {
        if (file.getPath().endsWith(".yml")) {
            FileConfiguration config = new YamlConfiguration();
            config.load(file);
            configs.put(name, file);
            configs.put(name, config);
        }
    }

    private boolean exportResource(InputStream in, String destination) {
        boolean success = true;

        try {
            Files.copy(in, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }
}
