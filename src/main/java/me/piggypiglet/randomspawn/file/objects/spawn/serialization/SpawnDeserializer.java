package me.piggypiglet.randomspawn.file.objects.spawn.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.piggypiglet.randomspawn.file.objects.spawn.Spawn;
import me.piggypiglet.randomspawn.file.objects.spawn.SpawnTypes;
import me.piggypiglet.randomspawn.file.objects.spawn.exceptions.UnknownSpawnTypeException;
import me.piggypiglet.randomspawn.file.objects.spawn.types.DefinedSpawn;
import me.piggypiglet.randomspawn.file.objects.spawn.types.RectangleSpawn;
import me.piggypiglet.randomspawn.file.objects.spawn.types.radius.RadiusSpawn;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class SpawnDeserializer implements JsonDeserializer<Spawn> {
    @NotNull
    @Override
    public Spawn deserialize(@NotNull final JsonElement json, @NotNull final Type typeOfT,
                             @NotNull final JsonDeserializationContext context) throws UnknownSpawnTypeException {
        final Type type;
        final JsonObject spawn = json.getAsJsonObject();

        switch ((SpawnTypes) context.deserialize(spawn.get("type"), SpawnTypes.class)) {
            case DEFINED:
                type = DefinedSpawn.class;
                break;

            case RADIUS:
                type = RadiusSpawn.class;
                break;

            case RECTANGLE:
                type = RectangleSpawn.class;
                break;

            default:
                throw new UnknownSpawnTypeException("Unknown spawn type: " + spawn.get("uuid").getAsString());
        }

        return context.deserialize(json, type);
    }
}
