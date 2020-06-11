package me.piggypiglet.randomspawn.file.objects.spawn.location.serialization;

import com.google.gson.*;
import me.piggypiglet.randomspawn.file.objects.spawn.location.Location;
import me.piggypiglet.randomspawn.file.objects.spawn.location.exceptions.InvalidLocationException;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.regex.Pattern;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class LocationSerializer implements JsonSerializer<Location>, JsonDeserializer<Location> {
    private static final Pattern LOCATION_DELIMITER = Pattern.compile(",");

    @NotNull
    @Override
    public JsonElement serialize(@NotNull final Location src, @NotNull final Type typeOfSrc,
                                 @NotNull final JsonSerializationContext context) {
        return new JsonPrimitive(src.getX() + "," + src.getY() + "," + src.getZ() + "," +
                src.getPitch() + "," + src.getYaw());
    }

    @NotNull
    @Override
    public Location deserialize(@NotNull final JsonElement json, @NotNull final Type typeOfT,
                                @NotNull final JsonDeserializationContext context) throws InvalidLocationException {
        final String location = json.getAsString();

        try {
            final double[] parts = Arrays.stream(LOCATION_DELIMITER.split(location))
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            return new Location(parts[0], parts[1], parts[2], parts[3], parts[4]);
        } catch (final Exception exception) {
            throw new InvalidLocationException("This location is invalid: " + location);
        }
    }
}
