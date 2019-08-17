package me.piggypiglet.randomspawn.utils;

import com.google.inject.Inject;
import me.piggypiglet.randomspawn.spawning.SpawnManager;
import me.piggypiglet.randomspawn.spawns.AreaSpawn;
import me.piggypiglet.randomspawn.spawns.Spawn;
import me.piggypiglet.randomspawn.spawns.Spawns;
import me.piggypiglet.randomspawn.spawns.implementations.DefaultSpawn;
import me.piggypiglet.randomspawn.spawns.implementations.RadiusSpawn;
import me.piggypiglet.randomspawn.spawns.implementations.SquareSpawn;
import me.piggypiglet.randomspawn.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SpawnUtils {
    @Inject private static SpawnManager spawnManager;

    public static double[] unGroup(String group) throws Exception {
        if (group == null) return new double[]{};

        String[] split = group.split(",");

        return new double[] {
                Double.parseDouble(split[0]),
                Double.parseDouble(split[1])
        };
    }

    public static Optional<Spawn> getSpawnByName(String name) {
        return spawnManager.getSpawns().stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst();
    }

    public static Spawn getRandomSpawn(List<Spawn> spawns) {
        return spawns.get(ThreadLocalRandom.current().nextInt(spawns.size()));
    }

    public static Location getRandomLocation(List<Spawn> spawns) {
        Spawn spawn = getRandomSpawn(spawns);
    }

    public static void getLocationObject(Spawn spawn) {
        switch (Spawns.getTypeFromSpawn(spawn)) {
            case DEFAULT:
                if (spawn instanceof DefaultSpawn) getDefaultSpawnLocation((DefaultSpawn) spawn);
                break;

            case RADIUS:
                if (spawn instanceof RadiusSpawn) getRadiusSpawnLocation((RadiusSpawn) spawn);
                break;

            case SQUARE:
                if (spawn instanceof SquareSpawn) getSquareSpawnLocation((SquareSpawn) spawn);
                break;
        }
    }

    private static Location getAndReplaceRandomLocation(AreaSpawn spawn) {
        final Location location = getRandomLocations(spawn, 1).get(0);

        return location;
    }

    private static List<Location> getRandomLocations(AreaSpawn spawn, int amount) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<Location> locations = new ArrayList<>();
        List<Location> loadedLocations = spawnManager.getProcessedLocations().get(spawn.getType()).get(spawn);

        IntStream.range(0, amount).forEach(i -> locations.add(loadedLocations.get(random.nextInt(0, loadedLocations.size() - 1))));

        return locations;
    }

    private static void replaceLocation(Spawn spawn, Location location) {
        List<Location> locations = spawnManager.getProcessedLocations().get(spawn.getType()).get(spawn);
        locations.remove(location);

//        Task.async(r -> {
//            int size =
//        });
    }
}
