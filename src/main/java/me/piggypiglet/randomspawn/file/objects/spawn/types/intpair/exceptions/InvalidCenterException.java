package me.piggypiglet.randomspawn.file.objects.spawn.types.intpair.exceptions;

import com.google.gson.JsonParseException;
import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class InvalidCenterException extends JsonParseException {
    public InvalidCenterException(@NotNull final String message) {
        super(message);
    }
}
