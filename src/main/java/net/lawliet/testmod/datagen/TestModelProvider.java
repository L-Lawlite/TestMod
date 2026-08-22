package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.block.TestBlocks;
import net.lawliet.testmod.item.TestItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

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

        blockModels.createTrivialCube(TestBlocks.AZURITE_BLOCK.get());
        blockModels.createTrivialCube(TestBlocks.RAW_AZURITE_BLOCK.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_ORE.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_DEEPSLATE_ORE.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_NETHER_ORE.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_END_ORE.get());
    }
}
