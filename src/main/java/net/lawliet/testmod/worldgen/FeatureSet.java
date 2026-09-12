package net.lawliet.testmod.worldgen;

import net.lawliet.testmod.TestMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record FeatureSet(String key, ResourceKey<ConfiguredFeature<?, ?>> configuredFeature, ResourceKey<PlacedFeature> placedFeature) {
    public static FeatureSet create(String key) {
        return new FeatureSet(key, createConfiguredFeature(key), createPlacementFeature(key));
    }

    public static ResourceKey<PlacedFeature> createPlacementFeature(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(TestMod.MODID, name));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeature(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(TestMod.MODID, name));
    }

}
