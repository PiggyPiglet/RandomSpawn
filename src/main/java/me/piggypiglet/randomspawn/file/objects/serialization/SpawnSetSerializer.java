package me.piggypiglet.randomspawn.file.objects.serialization;

import com.google.gson.*;
import me.piggypiglet.randomspawn.file.objects.spawn.Spawn;
import me.piggypiglet.randomspawn.utils.LambdaUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class SpawnSetSerializer implements JsonSerializer<Set<Spawn>>, JsonDeserializer<Set<Spawn>> {
    @NotNull
    @Override
    public JsonElement serialize(@NotNull final Set<Spawn> src, @NotNull final Type typeOfSrc,
                                 @NotNull final JsonSerializationContext context) {
        return context.serialize(src.stream().collect(Collectors.toMap(Spawn::getUuid, spawn -> spawn)));
    }

    @NotNull
    @Override
    public Set<Spawn> deserialize(@NotNull final JsonElement json, @NotNull final Type typeOfT,
                                  @NotNull final JsonDeserializationContext context) {
        final JsonObject spawns = json.getAsJsonObject();

        return spawns.entrySet().stream()
                .map(entry -> LambdaUtils.invoke(
                        entry.getValue().getAsJsonObject(),
                        object -> object.add("uuid", new JsonPrimitive(entry.getKey()))
                ))
                .map(object -> (Spawn) context.deserialize(object, Spawn.class))
                .collect(Collectors.toSet());
    }
}
