package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.block.TestBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
                .add(TestBlocks.AZURITE_ORE.get())
                .add(TestBlocks.AZURITE_DEEPSLATE_ORE.get())
                .add(TestBlocks.AZURITE_NETHER_ORE.get())
                .add(TestBlocks.AZURITE_END_ORE.get())
                .add(TestBlocks.MAGIC_BLOCK.get())
        ;

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(TestBlocks.AZURITE_ORE.get())
                .add(TestBlocks.AZURITE_DEEPSLATE_ORE.get())
                .add(TestBlocks.AZURITE_NETHER_ORE.get())
                .add(TestBlocks.AZURITE_END_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(TestBlocks.MAGIC_BLOCK.get());
    }
}
