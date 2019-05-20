package me.piggypiglet.randomspawn.file;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.randomspawn.RandomSpawn;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class FileManager {
    @Inject private RandomSpawn randomSpawn;

    private final Map<String, List<Object>> configs = new HashMap<>();

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
            getConfig(name).save((File) configs.get(name).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig(String name) {
        return (FileConfiguration) configs.get(name).get(1);
    }

    public void configConvert() {
        FileConfiguration config = getConfig("config");

        if (!config.contains("config-version")) {
            randomSpawn.getLogger().info("Found outdated config! Converting to the latest version.\nPhysical changes to the file will occur next restart, but the plugin will work fine till then.\nConversion will most likely change the order of things inside the file,\nbut this won't change any of your settings/data.");

            config.set("config-version", 1);
            config.set("settings", null);
            config.set("settings.first-join-only", false);

            ConfigurationSection section = config.getConfigurationSection("data.locations");

            if (section != null) {
                section.getKeys(false).forEach(k -> {
                    String disabled = k + ".disabled";

                    if (section.contains(disabled)) {
                        boolean value = section.getBoolean(disabled);
                        section.set(disabled, null);
                        section.set(k + ".enabled", !value);
                    }
                });
            }
        }
    }

    public void clear() {
        configs.clear();
    }

    private void load(String name, File file) throws Exception {
        if (file.getPath().endsWith(".yml")) {
            FileConfiguration config = new YamlConfiguration();
            config.load(file);
            configs.put(name, new ArrayList<>());
            configs.get(name).addAll(Stream.of(file, config).collect(Collectors.toList()));
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
