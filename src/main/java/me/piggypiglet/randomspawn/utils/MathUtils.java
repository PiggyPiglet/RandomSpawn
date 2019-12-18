package me.piggypiglet.randomspawn.utils;

import java.util.HashSet;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MathUtils {
    public static Set<int[]> coordinateCircle(int[] center, int radius) {
        Set<int[]> coords = new HashSet<>();

        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                if ((x * x) + (z * z) <= radius * radius) {
                    coords.add(new int[]{x + center[0], z + center[1]});
                }
            }
        }

        return coords;
    }
}
