package me.piggypiglet.randomspawn.managers.objects;

import me.piggypiglet.randomspawn.data.spawn.Spawn;

import java.util.UUID;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class PendingSpawn {
    private final UUID player;
    private final Spawn spawn;

    public PendingSpawn(UUID player, Spawn spawn) {
        this.player = player;
        this.spawn = spawn;
    }

    public UUID getPlayer() {
        return player;
    }

    public Spawn getSpawn() {
        return spawn;
    }
}
