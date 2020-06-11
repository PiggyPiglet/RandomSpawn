package me.piggypiglet.randomspawn.file.objects.spawn.exceptions;

import com.google.gson.JsonParseException;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class UnknownSpawnTypeException extends JsonParseException {
    public UnknownSpawnTypeException(@NotNull final String message) {
        super(message);
    }
}
