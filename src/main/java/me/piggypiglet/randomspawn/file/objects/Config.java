package me.piggypiglet.randomspawn.file.objects;

import com.google.inject.Singleton;
import me.piggypiglet.randomspawn.file.objects.enums.Languages;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class Config {
    private int configVersion;
    private Languages language;

    public int getConfigVersion() {
        return configVersion;
    }

    @NotNull
    public Languages getLanguage() {
        return language;
    }
}
