package net.lawliet.testmod.worldgen;

import net.lawliet.testmod.worldgen.features.OreFeatureSets;
import net.lawliet.testmod.worldgen.features.TreeFeatureSets;
import net.lawliet.testmod.worldgen.features.VegetationFeatureSets;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.feature.Feature;

/**
 * Gets {@link Feature} and determine where/how many it is placed
 */
public class TestPlacedFeatures {

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        OreFeatureSets.bootstrapPlaced(context);
        TreeFeatureSets.bootstrapPlaced(context);
        VegetationFeatureSets.bootstrapPlaced(context);

    }



}
