package net.lawliet.testmod.worldgen;

import net.lawliet.testmod.worldgen.features.OreFeatureSets;
import net.lawliet.testmod.worldgen.features.TreeFeatureSets;
import net.lawliet.testmod.worldgen.features.VegetationFeatureSets;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.Feature;

/**
 * Describe how something looks like, How it is built
 */
public class TestFeatures {

    public static void bootstrap(BootstrapContext<Feature> context) {
        OreFeatureSets.bootstrapFeature(context);
        TreeFeatureSets.bootstrapFeature(context);
        VegetationFeatureSets.bootstrapFeature(context);
    }

}
