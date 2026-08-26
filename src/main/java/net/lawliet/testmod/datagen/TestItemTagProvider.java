package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestItems;
import net.lawliet.testmod.tags.TestTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagCopyingItemTagProvider;

import java.util.concurrent.CompletableFuture;

public class TestItemTagProvider extends BlockTagCopyingItemTagProvider {
    public TestItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, TestMod.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        copy(TestTags.Blocks.AZURITE_ORES, TestTags.Items.AZURITE_ORES);
        tag(TestTags.Items.TRANSFORMABLE_ITEMS).add(TestItems.AZURITE.get());
        copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(BlockTags.FENCES, Tags.Items.FENCES);
        copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.DOORS, ItemTags.DOORS);
        copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
    }
}
