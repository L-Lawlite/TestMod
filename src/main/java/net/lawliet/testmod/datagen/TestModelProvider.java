package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
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
    }
}
