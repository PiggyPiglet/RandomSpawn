package me.piggypiglet.randomspawn.mappers.spawns.location;

import me.piggypiglet.framework.mapper.ObjectMapper;
import org.bukkit.Location;
import org.bukkit.World;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class LocationMapper implements ObjectMapper<String, Location> {
    private final World world;

    public LocationMapper(World world) {
        this.world = world;
    }

    @Override
    public Location dataToType(String location) {
        final String[] locParts = location.split(",");

        return new Location(
                world, Double.parseDouble(locParts[0]), Double.parseDouble(locParts[1]), Double.parseDouble(locParts[2]),
                Float.parseFloat(locParts[3]), Float.parseFloat(locParts[4])
        );
    }

    @Override
    public String typeToData(Location location) {
        return String.format("%s,%s,%s,%s,%s", location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }
}
