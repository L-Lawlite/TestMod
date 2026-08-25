package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.datagen.advancedRecipeProvider.ModdedRecipeProvider;
import net.lawliet.testmod.registries.TestItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TestRecipeProvider extends ModdedRecipeProvider {
    public TestRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output, TestMod.MODID);
    }

    @Override
    protected void buildRecipes() {
        nineBlockStorageRecipes(
                RecipeCategory.MISC,
                TestItems.AZURITE.get(),
                RecipeCategory.BUILDING_BLOCKS,
                TestBlocks.AZURITE_BLOCK.asItem(),
                TestMod.MODID
        );

        List<ItemLike> AZURITE_SMELTING = List.of(
                TestItems.RAW_AZURITE,
                TestBlocks.AZURITE_ORE,
                TestBlocks.AZURITE_DEEPSLATE_ORE,
                TestBlocks.AZURITE_NETHER_ORE,
                TestBlocks.AZURITE_END_ORE
        );
        oreSmelting(AZURITE_SMELTING, RecipeCategory.MISC, CookingBookCategory.MISC, TestItems.AZURITE, 0.25f, 200, "azurite", TestMod.MODID);
        oreBlasting(AZURITE_SMELTING, RecipeCategory.MISC, CookingBookCategory.MISC, TestItems.AZURITE, 0.25f, 100, "azurite", TestMod.MODID);

        solidStair(RecipeCategory.BUILDING_BLOCKS, TestBlocks.AZURITE_STAIRS, TestBlocks.AZURITE_BLOCK);
        solidSlab(RecipeCategory.BUILDING_BLOCKS, TestBlocks.AZURITE_SLAB, TestBlocks.AZURITE_BLOCK);
    }


    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new TestRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Test Mod Recipes";
        }
    }
}
