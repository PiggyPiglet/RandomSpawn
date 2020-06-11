package me.piggypiglet.randomspawn.scanning.framework;

import me.piggypiglet.randomspawn.utils.annotations.wrapper.AnnotationRules;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public interface Scanner {
    <T> Set<Class<? extends T>> getSubTypesOf(@NotNull final Class<T> type);

    Set<Class<?>> getClassesAnnotatedWith(@NotNull final AnnotationRules rules);
}
