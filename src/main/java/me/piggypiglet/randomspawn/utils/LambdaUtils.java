package me.piggypiglet.randomspawn.utils;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class LambdaUtils {
    private LambdaUtils() {
        throw new AssertionError("This class cannot be initialized.");
    }

    @NotNull
    public static <T> T invoke(@NotNull final T object, @NotNull final Consumer<T> function) {
        function.accept(object);
        return object;
    }
}
