package net.lawliet.testmod.datagen.tags;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestItems;
import net.lawliet.testmod.tags.TestTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockItemTags;
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
        tag(TestTags.Items.TRANSFORMABLE_ITEMS).add(TestItems.AZURITE.key());
        copy(BlockTags.BUTTONS, BlockItemTags.BUTTONS.item());
        copy(BlockTags.SLABS, BlockItemTags.SLABS.item());
        copy(BlockTags.STAIRS, BlockItemTags.STAIRS.item());
        copy(Tags.BlockItems.STORAGE_BLOCKS.block(), Tags.BlockItems.STORAGE_BLOCKS.item());
        copy(Tags.BlockItems.ORES.block(), Tags.BlockItems.ORES.item());
        copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.DOORS, BlockItemTags.DOORS.item());
        copy(BlockTags.TRAPDOORS, BlockItemTags.TRAPDOORS.item());
        tag(TestTags.Items.AZURITE_TOOL_MATERIAL).add(TestItems.AZURITE.key());
        tag(TestTags.Items.AZURITE_REPAIRABLE).addTag(TestTags.Items.AZURITE_TOOL_MATERIAL);
        tag(ItemTags.SWORDS).add(TestItems.AZURITE_SWORD.key());
        tag(ItemTags.PICKAXES).add(TestItems.AZURITE_PICKAXE.key());
        tag(ItemTags.AXES).add(TestItems.AZURITE_AXE.key());
        tag(ItemTags.SHOVELS).add(TestItems.AZURITE_SHOVEL.key());
        tag(ItemTags.HOES).add(TestItems.AZURITE_HOE.key());
        tag(ItemTags.SPEARS).add(TestItems.AZURITE_SPEAR.key());
        tag(ItemTags.HEAD_ARMOR).add(TestItems.AZURITE_HELMET.key());
        tag(ItemTags.CHEST_ARMOR).add(TestItems.AZURITE_CHESTPLATE.key());
        tag(ItemTags.LEG_ARMOR).add(TestItems.AZURITE_LEGGINGS.key());
        tag(ItemTags.FOOT_ARMOR).add(TestItems.AZURITE_BOOTS.key());
        tag(ItemTags.BOW_ENCHANTABLE).add(TestItems.TEST_BOW.key());
        tag(Tags.Items.TOOLS_BOW).add(TestItems.TEST_BOW.key());
        copy(BlockItemTags.FENCES.block(), BlockItemTags.FENCES.item());

        tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(TestItems.BAR_BRAWL_MUSIC_DISC.key());
        copy(BlockItemTags.LEAVES.block(), BlockItemTags.LEAVES.item());
        copy(BlockItemTags.PLANKS.block(), BlockItemTags.PLANKS.item());
        copy(BlockItemTags.LOGS_THAT_BURN.block(), BlockItemTags.LOGS_THAT_BURN.item());
        copy(TestTags.Blocks.DRIFTWOOD_LOGS, TestTags.Items.DRIFTWOOD_LOGS);
        copy(BlockItemTags.WOODEN_SHELVES.block(), BlockItemTags.WOODEN_SHELVES.item());
        tag(ItemTags.BOATS).add(TestItems.DRIFTWOOD_BOAT.key());
    }
}
