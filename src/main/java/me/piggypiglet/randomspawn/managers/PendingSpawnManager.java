package me.piggypiglet.randomspawn.managers;

import com.google.inject.Singleton;
import me.piggypiglet.framework.managers.Manager;
import me.piggypiglet.framework.managers.objects.KeyTypeInfo;
import me.piggypiglet.randomspawn.commands.spawn.create.objects.PendingSpawn;
import me.piggypiglet.randomspawn.data.spawn.Spawn;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class PendingSpawnManager extends Manager<PendingSpawn> {
    private final Map<UUID, Spawn> spawns = new ConcurrentHashMap<>();

    @Override
    protected KeyTypeInfo configure(KeyTypeInfo.Builder builder) {
        return builder
                .key(UUID.class)
                    .getter(u -> new PendingSpawn(u, spawns.getOrDefault(u, SpawnManager.DEFAULT)))
                    .exists(spawns::containsKey)
                    .bundle()
                .build();
    }

    @Override
    protected void insert(PendingSpawn spawn) {
        spawns.put(spawn.getPlayer(), spawn.getSpawn());
    }

    @Override
    protected void delete(PendingSpawn spawn) {
        spawns.remove(spawn.getPlayer());
    }

    @Override
    protected Collection<PendingSpawn> retrieveAll() {
        return spawns.entrySet().stream()
                .map(e -> new PendingSpawn(e.getKey(), e.getValue()))
                .collect(Collectors.toSet());
    }
}
