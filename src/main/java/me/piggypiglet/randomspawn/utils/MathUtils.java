package me.piggypiglet.randomspawn.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MathUtils {
    public static List<int[]> coordinateCircle(double[] center, int radius) {
        List<int[]> coords = new ArrayList<>();

        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                if ((x * x) + (z * z) <= radius * radius) {
                    coords.add(new int[] {x + (int) center[0], z + (int) center[1]});
                }
            }
        }

        return coords;
    }
}
