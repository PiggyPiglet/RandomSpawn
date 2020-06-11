package me.piggypiglet.randomspawn.file.objects;

import com.google.inject.Singleton;
import me.piggypiglet.randomspawn.file.objects.options.Options;
import me.piggypiglet.randomspawn.file.objects.spawn.Spawn;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class Data {
    private Options options;
    private Set<Spawn> spawns;

    @NotNull
    public Options getOptions() {
        return options;
    }

    @NotNull
    public Set<Spawn> getSpawns() {
        return spawns;
    }
}
