package net.lawliet.testmod.worldgen;

import net.lawliet.testmod.worldgen.features.OreFeatureSets;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

/**
 * Describe how something looks like, How it is built
 */
public class TestConfiguredFeatures {

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        OreFeatureSets.bootstrapConfigured(context);
    }

}
