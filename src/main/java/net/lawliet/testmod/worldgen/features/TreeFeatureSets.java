package net.lawliet.testmod.worldgen.features;

import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.worldgen.FeatureSet;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class TreeFeatureSets {
    public static final FeatureSet DRIFTWOOD_TREE = FeatureSet.create("driftwood");


    public static void bootstrapConfigured(BootstrapContext<ConfiguredFeature<?,?>> context) {
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        FeatureUtils.register(context, DRIFTWOOD_TREE.configuredFeature(), Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TestBlocks.DRIFTWOOD_LOG.get()),
                new UpwardsBranchingTrunkPlacer(4, 3, 4, ConstantInt.of(2), 0.4f, UniformInt.of(1,3), blocks.getOrThrow(BlockTags.REPLACEABLE_BY_TREES)),

                BlockStateProvider.simple(TestBlocks.DRIFTWOOD_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.DIRT)
        ).build());
    }

    public static void bootstrapPlaced(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        // NOTE: countExtra is weird method. In chance parameter 1/value should be an integer if it is not it gives error
        PlacementUtils.register(context, DRIFTWOOD_TREE.placedFeature(),
                configuredFeatures.getOrThrow(DRIFTWOOD_TREE.configuredFeature()),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(3, 0.1f, 2),
                        TestBlocks.DRIFTWOOD_SAPLING.get()
                        )
        );
    }
}
