package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagCopyingItemTagProvider;

import java.util.concurrent.CompletableFuture;

public class TestItemTagProvider extends BlockTagCopyingItemTagProvider {
    public TestItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, TestMod.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
