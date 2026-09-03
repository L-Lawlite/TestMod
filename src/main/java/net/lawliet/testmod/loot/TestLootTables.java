package net.lawliet.testmod.loot;

import net.lawliet.testmod.TestMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TestLootTables {
    private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet<>();
    private static final Set<ResourceKey<LootTable>> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);

    public static final ResourceKey<LootTable> HARVEST_SWEET_BERRY_BUSH = register("harvest/goji_berry_bush");

    private static ResourceKey<LootTable> register(String location) {
        return register(ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(TestMod.MODID, location)));
    }

    private static ResourceKey<LootTable> register(ResourceKey<LootTable> location) {
        if (LOCATIONS.add(location)) {
            return location;
        } else {
            throw new IllegalArgumentException(location.identifier() + " is already a registered built-in net.lawliet.testmod.loot table");
        }
    }

    public static Set<ResourceKey<LootTable>> all() {
        return IMMUTABLE_LOCATIONS;
    }
}
