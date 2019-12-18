package me.piggypiglet.randomspawn.mappers.spawns.data;

import me.piggypiglet.randomspawn.data.options.Options;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.Spawns;
import org.bukkit.World;

public final class SpawnData extends Spawn {
    public SpawnData(String name, Spawns type, String permission, boolean enabled, World world, Options options) {
        super(name, type, permission, enabled, world, options);
    }
}
