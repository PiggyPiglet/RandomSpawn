package me.piggypiglet.randomspawn.spawns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.bukkit.Location;
import org.bukkit.World;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Data
public abstract class Spawn {
    protected final String name;
    protected final boolean enabled;
    protected final boolean respawn;
    protected final World world;

    protected Spawn(final String name, final boolean enabled, final boolean respawn, final World world) {
        this.name = name;
        this.enabled = enabled;
        this.respawn = respawn;
        this.world = world;
    }

    public Spawns getType() {
        return Spawns.getTypeFromSpawn(this);
    }
}