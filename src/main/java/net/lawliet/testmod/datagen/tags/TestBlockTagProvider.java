package net.lawliet.testmod.datagen.tags;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.tags.TestTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class TestBlockTagProvider extends BlockTagsProvider {
    public TestBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TestMod.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(TestBlocks.AZURITE_BLOCK.key())
            .add(TestBlocks.RAW_AZURITE_BLOCK.key())
            .addTag(TestTags.Blocks.AZURITE_ORES)
            .add(TestBlocks.MAGIC_BLOCK.key())
            .add(TestBlocks.AZURITE_STAIRS.key())
            .add(TestBlocks.AZURITE_SLAB.key())
            .add(TestBlocks.AZURITE_PRESSURE_PLATE.key())
            .add(TestBlocks.AZURITE_DOOR.key())
            .add(TestBlocks.AZURITE_TRAPDOOR.key())
            .add(TestBlocks.PEDESTAL.key())
            .add(TestBlocks.CRYSTALLIZER.key())
        ;

        tag(BlockTags.NEEDS_IRON_TOOL)
            .addTag(TestTags.Blocks.AZURITE_ORES);

        tag(BlockTags.MINEABLE_WITH_AXE)
            .add(TestBlocks.TEST_SHELF.key());

        tag(TestTags.Blocks.AZURITE_ORES)
            .add(TestBlocks.AZURITE_ORE.key())
            .add(TestBlocks.AZURITE_DEEPSLATE_ORE.key())
            .add(TestBlocks.AZURITE_NETHER_ORE.key())
            .add(TestBlocks.AZURITE_END_ORE.key())
        ;

        tag(TestTags.Blocks.METAL_DETECTABLE)
            .addTag(Tags.BlockItems.ORES_IRON.block())
            .addTag(Tags.BlockItems.BARS_IRON.block())
            .addTag(Tags.BlockItems.STORAGE_BLOCKS_IRON.block())
            .addTag(Tags.BlockItems.STORAGE_BLOCKS_RAW_IRON.block());

        tag(BlockTags.STAIRS)
                .add(TestBlocks.AZURITE_STAIRS.key())
                .add(TestBlocks.DRIFTWOOD_STAIRS.key())
        ;
        tag(BlockTags.SLABS)
            .add(TestBlocks.AZURITE_SLAB.key())
            .add(TestBlocks.DRIFTWOOD_SLAB.key())
        ;
        tag(Tags.BlockItems.ORES.block())
            .addTag(TestTags.Blocks.AZURITE_ORES);
        tag(Tags.BlockItems.STORAGE_BLOCKS.block())
            .add(TestBlocks.AZURITE_BLOCK.key())
            .add(TestBlocks.RAW_AZURITE_BLOCK.key());
        tag(BlockTags.PRESSURE_PLATES)
            .add(TestBlocks.AZURITE_PRESSURE_PLATE.key())
            .add(TestBlocks.AZURITE_PRESSURE_PLATE.key())
        ;
        tag(BlockTags.BUTTONS)
            .add(TestBlocks.AZURITE_BUTTON.key())
            .add(TestBlocks.DRIFTWOOD_BUTTON.key());
        tag(BlockTags.FENCES)
            .add(TestBlocks.AZURITE_FENCE.key())
            .add(TestBlocks.DRIFTWOOD_FENCE.key())
        ;
        tag(BlockTags.FENCE_GATES)
            .add(TestBlocks.AZURITE_FENCE_GATE.key())
            .add(TestBlocks.DRIFTWOOD_FENCE_GATE.key())
        ;
        tag(BlockTags.WALLS)
            .add(TestBlocks.AZURITE_WALLS.key());

        tag(BlockTags.DOORS)
            .add(TestBlocks.AZURITE_DOOR.key());

        tag(BlockTags.TRAPDOORS)
            .add(TestBlocks.AZURITE_TRAPDOOR.key());

        tag(TestTags.Blocks.NEED_AZURITE_TOOLS)
            .add(TestBlocks.MAGIC_BLOCK.key())
            .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(TestTags.Blocks.INCORRECT_FOR_AZURITE_TOOL)
            .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
            .remove(TestTags.Blocks.NEED_AZURITE_TOOLS);
        tag(TestTags.Blocks.SHALLOW_WATER_FLOOR)
            .addTag(BlockTags.DIRT)
            .addTag(BlockTags.GROWS_CROPS)
            .addTag(BlockTags.SUPPORTS_CROPS)
            .add(BlockItemIds.GRASS_BLOCK.block())
        ;
        tag(TestTags.Blocks.RICE_FARMLAND)
            .addTag(TestTags.Blocks.SHALLOW_WATER_FLOOR)
            .add(BlockItemIds.CLAY.block())
            .add(BlockItemIds.MYCELIUM.block())
        ;

        tag(BlockTags.INCORRECT_FOR_IRON_TOOL).addTag(TestTags.Blocks.NEED_AZURITE_TOOLS);
        tag(BlockTags.INCORRECT_FOR_COPPER_TOOL).addTag(TestTags.Blocks.NEED_AZURITE_TOOLS);
        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL).addTag(TestTags.Blocks.NEED_AZURITE_TOOLS);
        tag(BlockTags.INCORRECT_FOR_STONE_TOOL).addTag(TestTags.Blocks.NEED_AZURITE_TOOLS);
        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL).addTag(TestTags.Blocks.NEED_AZURITE_TOOLS);

        tag(BlockTags.CROPS).add(TestBlocks.ONION.key());

        tag(BlockTags.LEAVES).add(TestBlocks.DRIFTWOOD_LEAVES.key());
        tag(BlockTags.PLANKS).add(TestBlocks.DRIFTWOOD_PLANKS.key());
        tag(TestTags.Blocks.DRIFTWOOD_LOGS)
            .add(TestBlocks.DRIFTWOOD_LOG.key())
            .add(TestBlocks.DRIFTWOOD_WOOD.key())
            .add(TestBlocks.STRIPPED_DRIFTWOOD_LOG.key())
            .add(TestBlocks.STRIPPED_DRIFTWOOD_WOOD.key())
        ;
        tag(BlockItemTags.LOGS_THAT_BURN.block()).addTag(TestTags.Blocks.DRIFTWOOD_LOGS);
        tag(BlockTags.WOODEN_SHELVES).add(TestBlocks.DRIFTWOOD_SHELF.key());
        tag(BlockTags.FLOWER_POTS).add(TestBlocks.DRIFTWOOD_SAPLING.key());
    }
}
