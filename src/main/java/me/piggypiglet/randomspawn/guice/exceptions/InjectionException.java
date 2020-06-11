package me.piggypiglet.randomspawn.guice.exceptions;

import org.jetbrains.annotations.NotNull;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class InjectionException extends RuntimeException {
    public InjectionException(@NotNull final Exception exception) {
        super(exception);
    }
}
