package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.tags.TestTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
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
                .add(TestBlocks.AZURITE_BLOCK.getKey())
                .add(TestBlocks.RAW_AZURITE_BLOCK.getKey())
                .addTag(TestTags.Blocks.AZURITE_ORES)
                .add(TestBlocks.MAGIC_BLOCK.getKey())
                .add(TestBlocks.AZURITE_STAIRS.getKey())
                .add(TestBlocks.AZURITE_SLAB.getKey())
                .add(TestBlocks.AZURITE_PRESSURE_PLATE.getKey())
                .add(TestBlocks.AZURITE_DOOR.getKey())
                .add(TestBlocks.AZURITE_TRAPDOOR.getKey())
                .add(TestBlocks.PEDESTAL.getKey())
        ;

        tag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(TestTags.Blocks.AZURITE_ORES);

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(TestBlocks.TEST_SHELF.getKey());

        tag(TestTags.Blocks.AZURITE_ORES)
                .add(TestBlocks.AZURITE_ORE.getKey())
                .add(TestBlocks.AZURITE_DEEPSLATE_ORE.getKey())
                .add(TestBlocks.AZURITE_NETHER_ORE.getKey())
                .add(TestBlocks.AZURITE_END_ORE.getKey())
        ;

        tag(TestTags.Blocks.METAL_DETECTABLE)
                .addTag(Tags.Blocks.ORES_IRON)
                .addTag(Tags.Blocks.BARS_IRON)
                .addTag(Tags.Blocks.STORAGE_BLOCKS_IRON)
                .addTag(Tags.Blocks.STORAGE_BLOCKS_RAW_IRON);

        tag(BlockTags.STAIRS)
                .add(TestBlocks.AZURITE_STAIRS.getKey());
        tag(BlockTags.SLABS)
            .add(TestBlocks.AZURITE_SLAB.getKey());
        tag(Tags.Blocks.ORES)
                .addTag(TestTags.Blocks.AZURITE_ORES);
        tag(Tags.Blocks.STORAGE_BLOCKS)
                .add(TestBlocks.AZURITE_BLOCK.getKey())
                .add(TestBlocks.RAW_AZURITE_BLOCK.getKey());
        tag(BlockTags.PRESSURE_PLATES)
                .add(TestBlocks.AZURITE_PRESSURE_PLATE.getKey());
        tag(BlockTags.BUTTONS)
                .add(TestBlocks.AZURITE_BUTTON.getKey());
        tag(BlockTags.FENCES)
                .add(TestBlocks.AZURITE_FENCE.getKey());
        tag(BlockTags.FENCE_GATES)
                .add(TestBlocks.AZURITE_FENCE_GATE.getKey());
        tag(BlockTags.WALLS)
                .add(TestBlocks.AZURITE_WALLS.getKey());

        tag(BlockTags.DOORS)
                .add(TestBlocks.AZURITE_DOOR.getKey());

        tag(BlockTags.TRAPDOORS)
                .add(TestBlocks.AZURITE_TRAPDOOR.getKey());

        tag(TestTags.Blocks.NEED_AZURITE_TOOLS)
                .add(TestBlocks.MAGIC_BLOCK.getKey())
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(TestTags.Blocks.INCORRECT_FOR_AZURITE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(TestTags.Blocks.NEED_AZURITE_TOOLS);

        tag(BlockTags.INCORRECT_FOR_IRON_TOOL).addTag(TestTags.Blocks.NEED_AZURITE_TOOLS);
        tag(BlockTags.INCORRECT_FOR_COPPER_TOOL).addTag(TestTags.Blocks.NEED_AZURITE_TOOLS);
        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL).addTag(TestTags.Blocks.NEED_AZURITE_TOOLS);
        tag(BlockTags.INCORRECT_FOR_STONE_TOOL).addTag(TestTags.Blocks.NEED_AZURITE_TOOLS);
        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL).addTag(TestTags.Blocks.NEED_AZURITE_TOOLS);

        tag(BlockTags.CROPS).add(TestBlocks.ONION.getKey());
    }
}
