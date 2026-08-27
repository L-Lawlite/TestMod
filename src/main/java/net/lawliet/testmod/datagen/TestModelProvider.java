package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.item.TestEquipmentAssets;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.registries.TestItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;

public class TestModelProvider extends ModelProvider {
    public TestModelProvider(PackOutput output) {
        super(output, TestMod.MODID);
    }

    private static ItemModelGenerators itemModels;
    private static BlockModelGenerators blockModels;

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        TestModelProvider.itemModels = itemModels;
        TestModelProvider.blockModels = blockModels;

        itemModels.generateFlatItem(TestItems.AZURITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(TestItems.RAW_AZURITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(TestItems.METAL_DETECTOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(TestItems.ONION.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(TestItems.END_FIRE_STARTER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(TestItems.AZURITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(TestItems.AZURITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(TestItems.AZURITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(TestItems.AZURITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(TestItems.AZURITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateSpear(TestItems.AZURITE_SPEAR.get());
        itemModels.generateTrimmableItem(TestItems.AZURITE_HELMET.get(), TestEquipmentAssets.AZURITE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(TestItems.AZURITE_CHESTPLATE.get(), TestEquipmentAssets.AZURITE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(TestItems.AZURITE_LEGGINGS.get(), TestEquipmentAssets.AZURITE, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(TestItems.AZURITE_BOOTS.get(), TestEquipmentAssets.AZURITE, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModels.generateFlatItem(TestItems.AZURITE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);

        blockModels.createTrivialCube(TestBlocks.RAW_AZURITE_BLOCK.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_ORE.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_DEEPSLATE_ORE.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_NETHER_ORE.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_END_ORE.get());
        blockModels.createTrivialCube(TestBlocks.MAGIC_BLOCK.get());
        blockModels.createShelf(TestBlocks.TEST_SHELF.get(), Blocks.STRIPPED_ACACIA_LOG);
        blockModels.family(TestBlocks.AZURITE_BLOCK.get())
                .stairs(TestBlocks.AZURITE_STAIRS.get())
                .slab(TestBlocks.AZURITE_SLAB.get())
                .pressurePlate(TestBlocks.AZURITE_PRESSURE_PLATE.get())
                .button(TestBlocks.AZURITE_BUTTON.get())
                .fence(TestBlocks.AZURITE_FENCE.get())
                .fenceGate(TestBlocks.AZURITE_FENCE_GATE.get())
                .wall(TestBlocks.AZURITE_WALLS.get())
                .door(TestBlocks.AZURITE_DOOR.get())
                .trapdoor(TestBlocks.AZURITE_TRAPDOOR.get());

    }
}
