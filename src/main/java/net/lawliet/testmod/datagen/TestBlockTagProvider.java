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
                .add(TestBlocks.AZURITE_BLOCK.get())
                .add(TestBlocks.RAW_AZURITE_BLOCK.get())
                .addTag(TestTags.Blocks.AZURITE_ORES)
                .add(TestBlocks.MAGIC_BLOCK.get())
                .add(TestBlocks.AZURITE_STAIRS.get())
                .add(TestBlocks.AZURITE_SLAB.get())
                .add(TestBlocks.AZURITE_PRESSURE_PLATE.get())
                .add(TestBlocks.AZURITE_DOOR.get())
                .add(TestBlocks.AZURITE_TRAPDOOR.get())
        ;

        tag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(TestTags.Blocks.AZURITE_ORES);

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(TestBlocks.MAGIC_BLOCK.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(TestBlocks.TEST_SHELF.get());

        tag(TestTags.Blocks.AZURITE_ORES)
                .add(TestBlocks.AZURITE_ORE.get())
                .add(TestBlocks.AZURITE_DEEPSLATE_ORE.get())
                .add(TestBlocks.AZURITE_NETHER_ORE.get())
                .add(TestBlocks.AZURITE_END_ORE.get())
        ;

        tag(TestTags.Blocks.METAL_DETECTABLE)
                .addTag(Tags.Blocks.ORES_IRON)
                .addTag(Tags.Blocks.BARS_IRON)
                .addTag(Tags.Blocks.STORAGE_BLOCKS_IRON)
                .addTag(Tags.Blocks.STORAGE_BLOCKS_RAW_IRON);

        tag(BlockTags.STAIRS)
                .add(TestBlocks.AZURITE_STAIRS.get());
        tag(BlockTags.SLABS)
            .add(TestBlocks.AZURITE_SLAB.get());
        tag(Tags.Blocks.ORES)
                .addTag(TestTags.Blocks.AZURITE_ORES);
        tag(Tags.Blocks.STORAGE_BLOCKS)
                .add(TestBlocks.AZURITE_BLOCK.get())
                .add(TestBlocks.RAW_AZURITE_BLOCK.get());
        tag(BlockTags.PRESSURE_PLATES)
                .add(TestBlocks.AZURITE_PRESSURE_PLATE.get());
        tag(BlockTags.BUTTONS)
                .add(TestBlocks.AZURITE_BUTTON.get());
        tag(BlockTags.FENCES)
                .add(TestBlocks.AZURITE_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(TestBlocks.AZURITE_FENCE_GATE.get());
        tag(BlockTags.WALLS)
                .add(TestBlocks.AZURITE_WALLS.get());

        tag(BlockTags.DOORS)
                .add(TestBlocks.AZURITE_DOOR.get());

        tag(BlockTags.TRAPDOORS)
                .add(TestBlocks.AZURITE_TRAPDOOR.get());
    }
}
