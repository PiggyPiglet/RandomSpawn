package me.piggypiglet.randomspawn.spawns.implementations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.piggypiglet.randomspawn.spawns.Spawn;
import org.bukkit.Location;
import org.bukkit.World;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@EqualsAndHashCode(callSuper = true)
@Data
public final class SquareSpawn extends Spawn {
    private XZGroup group1;
    private XZGroup group2;

    @Builder(toBuilder = true)
    public SquareSpawn(String name, boolean enabled, boolean respawn, World world, XZGroup group1, XZGroup group2) {
        super(name, enabled, respawn, world);
        this.group1 = group1;
        this.group2 = group2;
    }

    @Data
    public static final class XZGroup {
        private final double x;
        private final double z;

        @Override
        public String toString() {
            return String.format("%s,%s", x, z);
        }
    }
}
