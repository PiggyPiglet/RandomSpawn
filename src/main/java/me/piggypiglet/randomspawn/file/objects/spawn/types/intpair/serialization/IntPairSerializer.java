package me.piggypiglet.randomspawn.file.objects.spawn.types.intpair.serialization;

import com.google.gson.*;
import me.piggypiglet.randomspawn.file.objects.spawn.types.intpair.IntPair;
import me.piggypiglet.randomspawn.file.objects.spawn.types.intpair.exceptions.InvalidCenterException;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.regex.Pattern;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class IntPairSerializer implements JsonSerializer<IntPair>, JsonDeserializer<IntPair> {
    private static final Pattern CENTER_DELIMITER = Pattern.compile(",");

    @NotNull
    @Override
    public JsonElement serialize(@NotNull final IntPair src, @NotNull final Type typeOfSrc,
                                 @NotNull final JsonSerializationContext context) {
        return new JsonPrimitive(src.getX() + "," + src.getZ());
    }

    @NotNull
    @Override
    public IntPair deserialize(@NotNull final JsonElement json, @NotNull final Type typeOfT,
                               @NotNull final JsonDeserializationContext context) throws InvalidCenterException {
        final String center = json.getAsString();

        try {
            final int[] parts = Arrays.stream(CENTER_DELIMITER.split(center))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            return new IntPair(parts[0], parts[1]);
        } catch (final Exception exception) {
            throw new InvalidCenterException("This center is invalid: " + center);
        }
    }
}
