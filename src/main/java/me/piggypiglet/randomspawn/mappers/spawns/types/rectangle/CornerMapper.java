package me.piggypiglet.randomspawn.mappers.spawns.types.rectangle;

import me.piggypiglet.framework.mapper.ObjectMapper;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class CornerMapper implements ObjectMapper<String, int[]> {
    @Override
    public int[] dataToType(String data) {
        final String[] parts = data.split(",");
        return new int[] {Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
    }

    @Override
    public String typeToData(int[] type) {
        return type[0] + "," + type[1];
    }
}
