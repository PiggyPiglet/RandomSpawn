package me.piggypiglet.randomspawn.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.framework.managers.implementations.SearchableManager;
import me.piggypiglet.framework.managers.objects.KeyTypeInfo;
import me.piggypiglet.randomspawn.data.Config;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.mappers.spawns.data.SpawnData;

import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class SpawnManager extends SearchableManager<Spawn> {
    public static final Spawn DEFAULT = new SpawnData(null, null, null, false, null, null);

    private final Map<String, Spawn> spawns;

    @Inject
    public SpawnManager(Config config) {
        spawns = config.getSpawns();
    }

    @Override
    protected KeyTypeInfo configure(KeyTypeInfo.Builder builder) {
        return builder
                .key(String.class)
                    .map(spawns, DEFAULT)
                .build();
    }

    @Override
    protected void insert(Spawn spawn) {
        spawns.put(spawn.getName(), spawn);
    }

    @Override
    protected void delete(Spawn spawn) {
        spawns.remove(spawn.getName());
    }
}
