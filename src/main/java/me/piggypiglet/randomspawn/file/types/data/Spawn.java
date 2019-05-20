package me.piggypiglet.randomspawn.file.types.data;

import lombok.Builder;
import lombok.Data;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Builder(toBuilder = true) @Data
public final class Spawn {
    private String name;
    private boolean enabled;
    private boolean respawn;
    private String world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
}
