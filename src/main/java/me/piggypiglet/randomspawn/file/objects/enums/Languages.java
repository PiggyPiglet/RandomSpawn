package me.piggypiglet.randomspawn.file.objects.enums;

import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public enum Languages {
    EN("en");

    private final String filePrefix;

    Languages(@NotNull final String filePrefix) {
        this.filePrefix = filePrefix;
    }

    @NotNull
    public String getFilePrefix() {
        return filePrefix;
    }
}
