package me.piggypiglet.randomspawn.managers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.framework.managers.implementations.SearchableManager;
import me.piggypiglet.framework.managers.objects.KeyTypeInfo;
import me.piggypiglet.framework.task.Task;
import me.piggypiglet.randomspawn.data.Config;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.RadiusSpawn;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.mappers.spawns.data.SpawnData;
import me.piggypiglet.randomspawn.utils.MathUtils;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class SpawnManager extends SearchableManager<Spawn> {
    public static final Spawn DEFAULT = new SpawnData(null, null, null, false, null, null);

    @Inject private Config config;
    @Inject private Task task;

    private Map<String, Spawn> spawns;
    private final Multimap<Spawn, Location> cachedLocations = ArrayListMultimap.create();

    @Override
    protected void preConfigure() {
        spawns = config.getSpawns();
        items.addAll(spawns.values());
    }

    @Override
    protected void postConfigure() {
        final Multimap<Spawn, int[]> locations = ArrayListMultimap.create();

        task.sync(r -> spawns.values().forEach(s -> {
            final World world = s.getWorld();

            switch (s.getType()) {
                case SET:
                    locations.putAll(s, from(((SetSpawn) s).getLocations()));
                    break;

                case CIRCLE:
                    final RadiusSpawn circle = (RadiusSpawn) s;
                    locations.putAll(s, MathUtils.coordinateCircle(circle.getCenter(), circle.getRadius()));
                    break;

                case SQUARE:
                    break;
            }
        }));
    }

    private Set<int[]> from(Set<Location> locations) {
        return locations.stream()
                .map(l -> new int[] {l.getBlockX(), l.getBlockX(), l.getBlockX(), (int) l.getYaw(), (int) l.getPitch()})
                .collect(Collectors.toSet());
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
