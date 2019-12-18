package me.piggypiglet.randomspawn.utils.data;

import me.piggypiglet.framework.file.framework.AbstractFileConfiguration;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.utils.map.Maps;
import me.piggypiglet.randomspawn.data.Config;
import me.piggypiglet.randomspawn.data.options.Options;
import me.piggypiglet.randomspawn.data.options.types.hook.Factions;
import me.piggypiglet.randomspawn.data.options.types.hook.GriefPrevention;
import me.piggypiglet.randomspawn.data.options.types.hook.Hooks;
import me.piggypiglet.randomspawn.data.options.types.list.List;
import me.piggypiglet.randomspawn.data.options.types.list.Lists;
import me.piggypiglet.randomspawn.data.spawn.Spawn;
import me.piggypiglet.randomspawn.data.spawn.types.RadiusSpawn;
import me.piggypiglet.randomspawn.data.spawn.types.SetSpawn;
import me.piggypiglet.randomspawn.data.spawn.types.Spawns;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.*;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@SuppressWarnings("unchecked")
public final class DataUtils {
    private static final Options DEFAULT = new Options(
            new Lists(
                    new List(
                            true, Collections.singleton("grass_block")
                    ),
                    new List(
                            false, new HashSet<>(Arrays.asList("lava", "water"))
                    )
            ),
            new Lists(
                    new List(
                            true, Collections.singleton("plains")
                    ),
                    new List(
                            false, new HashSet<>(Arrays.asList("jungle", "savanna"))
                    )
            ),
            new Lists(
                    new List(
                            true, Collections.singleton("world")
                    ),
                    new List(
                            false, new HashSet<>(Arrays.asList("world_nether", "world_the_end"))
                    )
            ),
            new Hooks(
                    new List(
                            false, new HashSet<>(Arrays.asList("worldguard", "worldborder", "factions", "griefprevention"))
                    ),
                    50,
                    new Lists(
                            new List(
                                    false, Collections.singleton("warzone")
                            ),
                            new List(
                                    true, new HashSet<>(Arrays.asList("spawn", "staff"))
                            )
                    ),
                    new Factions(
                            false, true, true, false, false
                    ),
                    new GriefPrevention(
                            false, false, true
                    )
            ),
            true,
            true
    );

    public static Config constructConfig(FileConfiguration config) {
        return new Config(
                constructOptions(((AbstractFileConfiguration) config.getConfigSection("options")).getAll()),
                constructSpawns((Map<String, Map<String, Object>>) config.get("data"))
        );
    }

    private static Map<String, Spawn> constructSpawns(Map<String, Map<String, Object>> data) {
        return data.entrySet().stream()
                .map(DataUtils::constructSpawn)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Map.Entry<String, Spawn> constructSpawn(Map.Entry<String, Map<String, Object>> spawn) {
        final Map<String, Object> data = spawn.getValue();
        final String name = spawn.getKey();
        final Spawns type = Spawns.valueOf(((String) data.get("type")).toUpperCase());
        final String permission = (String) data.getOrDefault("permission", "randomspawn.spawns." + name);
        final boolean enabled = (boolean) data.getOrDefault("enabled", true);
        final World world = Bukkit.getWorld((String) data.get("world"));
        final Options options = constructOptions(
                ((Map<String, Object>) data.getOrDefault("options", new HashMap<>())).entrySet().stream()
                        .flatMap(Maps::flatten)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );

        switch (type) {
            case SET:
                return new AbstractMap.SimpleEntry<>(
                        name,
                        new SetSpawn(
                                name, type, permission, enabled, world, options,
                                new HashSet<>((java.util.List<String>) data.get("locations")).stream()
                                        .map(s -> DataUtils.constructLocation(s, world))
                                        .collect(Collectors.toSet())
                        )
                );

            case CIRCLE:
            case SQUARE:
                final String[] location = ((String) data.get("center")).split(",");

                return new AbstractMap.SimpleEntry<>(
                        name,
                        new RadiusSpawn(
                                name, type, permission, enabled, world, options,
                                new double[] {Double.parseDouble(location[0]), Double.parseDouble(location[1])},
                                (int) data.get("radius")
                        )
                );

            default:
                return new AbstractMap.SimpleEntry<>("", null);
        }
    }

    private static Location constructLocation(String location, World world) {
        final String[] locParts = location.split(",");

        return new Location(
                world, Double.parseDouble(locParts[0]), Double.parseDouble(locParts[1]), Double.parseDouble(locParts[2]),
                Float.parseFloat(locParts[3]), Float.parseFloat(locParts[4])
        );
    }

    private static Options constructOptions(Map<String, Object> options) {
        return new Options(
                constructLists("blocks", options, DEFAULT.getBlocks()),
                constructLists("biomes", options, DEFAULT.getBiomes()),
                constructLists("worlds", options, DEFAULT.getWorlds()),
                constructHooks(options),
                (boolean) options.getOrDefault("respawn", DEFAULT.isRespawn()),
                (boolean) options.getOrDefault("safe_location", DEFAULT.isSafeLocation())
        );
    }

    private static Lists constructLists(String prefix, Map<String, Object> map, Lists def) {
        return new Lists(
                constructList(prefix + ".whitelist", map, def.getWhitelist()),
                constructList(prefix + ".blacklist", map, def.getBlacklist())
        );
    }

    private static List constructList(String prefix, Map<String, Object> map, List def) {
        return new List(
                (boolean) map.getOrDefault(prefix + ".enabled", def.isEnabled()),
                new HashSet<>((Collection<String>) map.getOrDefault(prefix + ".values", def.getValues()))
        );
    }

    private static Hooks constructHooks(Map<String, Object> map) {
        final Hooks def = DEFAULT.getHooks();

        return new Hooks(
                constructList("hooks", map, def.getHooks()),
                (int) map.getOrDefault("distance-from-claim", def.getDistanceFromClaim()),
                constructLists("hooks.worldguard", map, def.getWorldGuard()),
                constructFactions(map),
                constructGriefPrevention(map)
        );
    }

    private static Factions constructFactions(Map<String, Object> map) {
        final String prefix = "hooks.factions.";
        final Factions def = DEFAULT.getHooks().getFactions();

        return new Factions(
                (boolean) map.getOrDefault(prefix + "safezone", def.isSafezone()),
                (boolean) map.getOrDefault(prefix + "warzone", def.isWarzone()),
                (boolean) map.getOrDefault(prefix + "wilderness", def.isWilderness()),
                (boolean) map.getOrDefault(prefix + "enemy", def.isEnemy()),
                (boolean) map.getOrDefault(prefix + "own", def.isOwn())
        );
    }

    private static GriefPrevention constructGriefPrevention(Map<String, Object> map) {
        final String prefix = "hooks.griefprevention.";
        final GriefPrevention def = DEFAULT.getHooks().getGriefPrevention();

        return new GriefPrevention(
                (boolean) map.getOrDefault(prefix + "own", def.isOwn()),
                (boolean) map.getOrDefault(prefix + "other", def.isOther()),
                (boolean) map.getOrDefault(prefix + "wilderness", def.isWilderness())
        );
    }
}
