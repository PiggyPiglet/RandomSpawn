package me.piggypiglet.randomspawn.scanning.implementations;

import me.piggypiglet.randomspawn.boot.RandomSpawnBootstrap;
import me.piggypiglet.randomspawn.scanning.exceptions.ScanningException;
import me.piggypiglet.randomspawn.scanning.framework.Scanner;
import me.piggypiglet.randomspawn.utils.annotations.wrapper.AnnotationRules;
import me.piggypiglet.randomspawn.utils.annotations.wrapper.AnnotationUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.security.CodeSource;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class ZISScanner implements Scanner {
    private static final Class<?> MAIN = RandomSpawnBootstrap.class;
    private static final String PACKAGE = "me/piggypiglet/randomspawn";

    private final Set<Class<?>> classes;

    private ZISScanner(@NotNull final Set<Class<?>> classes) {
        this.classes = classes;
    }

    @NotNull
    public static ZISScanner create() {
        final ClassLoader loader = MAIN.getClassLoader();
        final CodeSource src = MAIN.getProtectionDomain().getCodeSource();

        if (src == null) {
            return new ZISScanner(Collections.emptySet());
        }

        final Set<Class<?>> classes = new HashSet<>();

        try (final ZipInputStream zip = new ZipInputStream(src.getLocation().openStream())) {
            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {
                final String name = entry.getName();

                if (!name.endsWith(".class") || !name.startsWith(PACKAGE)) {
                    continue;
                }

                classes.add(loadClass(loader, name.replace('/', '.').replace(".class", "")));
            }
        } catch (final Exception exception) {
            throw new ScanningException(exception);
        }

        return new ZISScanner(classes);
    }

    @NotNull
    private static Class<?> loadClass(@NotNull final ClassLoader loader, @NotNull final String name) {
        try {
            return loader.loadClass(name);
        } catch (final Exception exception) {
            throw new ScanningException(exception);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<Class<? extends T>> getSubTypesOf(@NotNull final Class<T> type) {
        return classes.stream()
                .filter(clazz -> !clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()))
                .filter(type::isAssignableFrom)
                .map(clazz -> (Class<? extends T>) clazz)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Class<?>> getClassesAnnotatedWith(final @NotNull AnnotationRules rules) {
        return classes.stream()
                .filter(clazz -> AnnotationUtils.rulesMatch(clazz, rules))
                .collect(Collectors.toSet());
    }
}