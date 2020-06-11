package me.piggypiglet.randomspawn.boot;

import com.google.inject.Injector;
import me.piggypiglet.randomspawn.boot.framework.Registerable;
import me.piggypiglet.randomspawn.guice.ExceptionalInjector;
import me.piggypiglet.randomspawn.guice.modules.DynamicModule;
import me.piggypiglet.randomspawn.guice.modules.InitialModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class RandomSpawnBootstrap extends JavaPlugin {
    private static final List<Class<? extends Registerable>> REGISTERABLES = Arrays.asList(

    );

    @Override
    public void onEnable() {
        final AtomicReference<Injector> injectorReference = new AtomicReference<>(
                new ExceptionalInjector(new InitialModule(this).createInjector())
        );

        for (final Class<? extends Registerable> registerableClass : REGISTERABLES) {
            final Injector injector = injectorReference.get();
            final Registerable registerable = injector.getInstance(registerableClass);
            registerable.run(injector);

            if (!registerable.getBindings().isEmpty() || registerable.getStaticInjections().length > 0) {
                injectorReference.set(injector.createChildInjector(new DynamicModule(
                        registerable.getBindings(),
                        registerable.getStaticInjections()
                )));
            }
        }
    }
}
