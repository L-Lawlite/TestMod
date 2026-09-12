package net.lawliet.testmod.worldgen;

import net.lawliet.testmod.worldgen.features.OreFeatureSets;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * Gets {@link net.minecraft.world.level.levelgen.feature.ConfiguredFeature} and determine where/how many it is placed
 */
public class TestPlacedFeatures {

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        OreFeatureSets.bootstrapPlaced(context);
    }



}
