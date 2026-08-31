package net.lawliet.testmod.datagen;

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
        tag(TestTags.Items.TRANSFORMABLE_ITEMS).add(TestItems.AZURITE.getKey());
        copy(BlockTags.BUTTONS, BlockItemTags.BUTTONS.item());
        copy(BlockTags.SLABS, BlockItemTags.SLABS.item());
        copy(BlockTags.STAIRS, BlockItemTags.STAIRS.item());
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(BlockTags.FENCES, Tags.Items.FENCES);
        copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.DOORS, BlockItemTags.DOORS.item());
        copy(BlockTags.TRAPDOORS, BlockItemTags.TRAPDOORS.item());
        tag(TestTags.Items.AZURITE_TOOL_MATERIAL).add(TestItems.AZURITE.getKey());
        tag(TestTags.Items.AZURITE_REPAIRABLE).addTag(TestTags.Items.AZURITE_TOOL_MATERIAL);
        tag(ItemTags.SWORDS).add(TestItems.AZURITE_SWORD.getKey());
        tag(ItemTags.PICKAXES).add(TestItems.AZURITE_PICKAXE.getKey());
        tag(ItemTags.AXES).add(TestItems.AZURITE_AXE.getKey());
        tag(ItemTags.SHOVELS).add(TestItems.AZURITE_SHOVEL.getKey());
        tag(ItemTags.HOES).add(TestItems.AZURITE_HOE.getKey());
        tag(ItemTags.SPEARS).add(TestItems.AZURITE_SPEAR.getKey());
        tag(ItemTags.HEAD_ARMOR).add(TestItems.AZURITE_HELMET.getKey());
        tag(ItemTags.CHEST_ARMOR).add(TestItems.AZURITE_CHESTPLATE.getKey());
        tag(ItemTags.LEG_ARMOR).add(TestItems.AZURITE_LEGGINGS.getKey());
        tag(ItemTags.FOOT_ARMOR).add(TestItems.AZURITE_BOOTS.getKey());
        tag(ItemTags.BOW_ENCHANTABLE).add(TestItems.TEST_BOW.getKey());
        tag(Tags.Items.TOOLS_BOW).add(TestItems.TEST_BOW.getKey());

        tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(TestItems.BAR_BRAWL_MUSIC_DISC.getKey());
    }
}
