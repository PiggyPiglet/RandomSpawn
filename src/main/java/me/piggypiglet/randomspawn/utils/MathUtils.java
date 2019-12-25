package me.piggypiglet.randomspawn.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MathUtils {
    public static Set<double[]> coordinateCircle(double[] center, int radius) {
        final int[] intCenter = Arrays.stream(center).mapToInt(i -> (int) i).toArray();
        Set<double[]> coords = new HashSet<>();

        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                if ((x * x) + (z * z) <= radius * radius) {
                    coords.add(new double[]{x + intCenter[0], z + intCenter[1]});
                }
            }
        }

        return coords;
    }

    public static Set<double[]> coordinateSquare(double[] center, int radius) {
        final int[] intCenter = Arrays.stream(center).mapToInt(i -> (int) i).toArray();
        Set<double[]> coords = new HashSet<>();

        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                coords.add(new double[]{x + intCenter[0], z + intCenter[1]});
            }
        }

        return coords;
    }

    public static double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
}
