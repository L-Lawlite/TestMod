package net.lawliet.testmod.worldgen.features;

import net.lawliet.testmod.block.GojiBerryBlock;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.worldgen.FeatureSet;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class VegetationFeatureSets {
    public static final FeatureSet GOJI_BERRY_BUSH = FeatureSet.create("goji_berry_bush");

    public static void bootstrapFeature(BootstrapContext<Feature> context) {
        context.register(GOJI_BERRY_BUSH.feature(),
                new SimpleBlockFeature(BlockStateProvider.of(TestBlocks.GOJI_BERRY_BUSH.get().defaultBlockState().setValue(GojiBerryBlock.AGE, 3)))
            );
    }

    public static void bootstrapPlaced(BootstrapContext<PlacedFeature> context) {
        HolderGetter<Feature> features = context.lookup(Registries.FEATURE);
        PlacementUtils.register(context, GOJI_BERRY_BUSH.placedFeature(),
                features.getOrThrow(GOJI_BERRY_BUSH.feature()),
                List.of(
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        CountPlacement.of(96),
                        OffsetPlacement.ofTriangle(7, 3), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.DOWN, Blocks.GRASS_BLOCK)))
                )
        );
    }
}
