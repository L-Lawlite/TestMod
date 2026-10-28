package net.lawliet.testmod.datagen.advanced_providers;

import net.minecraft.data.recipes.BrewingProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;

public abstract class BasicBrewingProvider extends BrewingProvider {
    protected BasicBrewingProvider(RecipeOutput output) {
        super(output);
    }

    @Override
    protected void addContainers() {
        this.addContainer(Items.LINGERING_POTION);
        this.addContainer(Items.POTION);
        this.addContainer(Items.SPLASH_POTION);
    }

    @Override
    protected void addContainerTransformations() {
        this.addContainerTransformation(Items.POTION, Items.GUNPOWDER, Items.SPLASH_POTION);
        this.addContainerTransformation(Items.SPLASH_POTION, Items.DRAGON_BREATH, Items.LINGERING_POTION);
    }
}
