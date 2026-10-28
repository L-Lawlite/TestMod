package net.lawliet.testmod.worldgen.features;

import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.worldgen.FeatureSet;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.BlockReplacement;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class OreFeatureSets {
    public static final FeatureSet OVERWORLD_AZURITE_ORE = FeatureSet.create("overworld_azurite_ore");
    public static final FeatureSet NETHER_AZURITE_ORE = FeatureSet.create("nether_azurite_ore");
    public static final FeatureSet END_AZURITE_ORE = FeatureSet.create("end_azurite_ore");

    public static final PlacementModifier AZURITE_ORE_DISTRIBUTION = HeightRangePlacement.triangle(VerticalAnchor.absolute(-64),  VerticalAnchor.absolute(80));

    public static void bootstrapFeature(BootstrapContext<Feature> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceable = new TagMatchTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceable = new BlockMatchTest(Blocks.END_STONE);

        context.register(OVERWORLD_AZURITE_ORE.feature(), new OreFeature(
                List.of(
                        BlockReplacement.replace(stoneReplaceable, TestBlocks.AZURITE_ORE.get().defaultBlockState()),
                        BlockReplacement.replace(deepslateReplaceable, TestBlocks.AZURITE_DEEPSLATE_ORE.get().defaultBlockState())
                ), 9
        ));
        context.register(NETHER_AZURITE_ORE.feature(), new OreFeature(netherReplaceable, TestBlocks.AZURITE_NETHER_ORE.get().defaultBlockState(), 7));
        context.register(END_AZURITE_ORE.feature(), new OreFeature(endReplaceable, TestBlocks.AZURITE_END_ORE.get().defaultBlockState(), 12));
    }

    public static void bootstrapPlaced(BootstrapContext<PlacedFeature> context) {
        HolderGetter<Feature> features = context.lookup(Registries.FEATURE);
        PlacementUtils.register(context,
                OVERWORLD_AZURITE_ORE.placedFeature(),
                features.getOrThrow(OVERWORLD_AZURITE_ORE.feature()),
                OrePlacements.commonOrePlacement(12, AZURITE_ORE_DISTRIBUTION)
        );
        PlacementUtils.register(context,
                NETHER_AZURITE_ORE.placedFeature(),
                features.getOrThrow(NETHER_AZURITE_ORE.feature()),
                OrePlacements.commonOrePlacement(12, AZURITE_ORE_DISTRIBUTION)
        );
        PlacementUtils.register(context,
                END_AZURITE_ORE.placedFeature(),
                features.getOrThrow(END_AZURITE_ORE.feature()),
                OrePlacements.commonOrePlacement(12, AZURITE_ORE_DISTRIBUTION)
        );
    }


}
