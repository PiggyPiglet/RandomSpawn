package me.piggypiglet.randomspawn.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.piggypiglet.randomspawn.RandomSpawn;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class BinderModule extends AbstractModule {
    private final RandomSpawn randomSpawn;

    public BinderModule(RandomSpawn randomSpawn) {
        this.randomSpawn = randomSpawn;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    public void configure() {
        bind(RandomSpawn.class).toInstance(randomSpawn);
    }
}
