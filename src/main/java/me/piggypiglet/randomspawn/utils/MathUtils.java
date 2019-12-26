package me.piggypiglet.randomspawn.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MathUtils {
    public static Set<int[]> coordinateRectangle(int[] corner1, int[] corner2) {
        final Set<int[]> coords = new HashSet<>();

        final int width = corner2[0] - corner1[0];
        final int height = corner2[1] - corner1[1];

        for (int x = 0; x < width; x++) {
            for (int z = 0; z < height; z++) {
                coords.add(new int[] {x + corner1[0], z + corner1[1]});
            }
        }

        return coords;
    }

    public static Set<int[]> coordinateCircle(int[] center, int radius) {
        return coordinateRadius(center, radius, (x, z) -> (x * x) + (z * z) <= radius * radius);
    }

    public static Set<int[]> coordinateSquare(int[] center, int radius) {
        return coordinateRadius(center, radius, null);
    }

    private static Set<int[]> coordinateRadius(int[] center, int radius, BiPredicate<Integer, Integer> condition) {
        Set<int[]> coords = new HashSet<>();

        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                if (condition == null || condition.test(x, z)) {
                    coords.add(new int[]{x + center[0], z + center[1]});
                }
            }
        }

        return coords;
    }

    public static double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
}
