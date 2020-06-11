package me.piggypiglet.randomspawn.file.objects.spawn.location.exceptions;

import com.google.gson.JsonParseException;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class InvalidLocationException extends JsonParseException {
    public InvalidLocationException(@NotNull final String message) {
        super(message);
    }
}
