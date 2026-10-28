package net.lawliet.testmod.datagen;

import net.lawliet.testmod.datagen.advanced_providers.BasicBrewingProvider;
import net.lawliet.testmod.registries.TestPotions;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Blocks;

public class TestBrewingProvider extends BasicBrewingProvider {
    protected TestBrewingProvider(RecipeOutput output) {
        super(output);
    }

    @Override
    protected void buildMixes() {
        this.buildStartMix(Blocks.DIRT.asItem(), TestPotions.STINKY_POTION);
    }

}
