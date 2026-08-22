package net.lawliet.testmod.datagen.advancedRecipeProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.Nullable;

import java.util.List;

public abstract class ModdedRecipeProvider extends RecipeProvider {

    protected ModdedRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    /*
    Nine Block Storage Recipes with namespace
    */
    protected void nineBlockStorageRecipes(RecipeCategory unpackedFormCategory, ItemLike unpackedForm, RecipeCategory packedFormCategory, ItemLike packedForm, String modId) {
        this.nineBlockStorageRecipes(
                unpackedFormCategory, unpackedForm, packedFormCategory, packedForm, getSimpleRecipeName(packedForm), null, getSimpleRecipeName(unpackedForm), null, modId
        );
    }

    protected void nineBlockStorageRecipesWithCustomPacking(
            RecipeCategory unpackedFormCategory,
            ItemLike unpackedForm,
            RecipeCategory packedFormCategory,
            ItemLike packedForm,
            String packingRecipeId,
            String packingRecipeGroup,
            String modId
    ) {
        this.nineBlockStorageRecipes(
                unpackedFormCategory, unpackedForm, packedFormCategory, packedForm, packingRecipeId, packingRecipeGroup, getSimpleRecipeName(unpackedForm), null, modId
        );
    }

    protected void nineBlockStorageRecipesRecipesWithCustomUnpacking(
            RecipeCategory unpackedFormCategory,
            ItemLike unpackedForm,
            RecipeCategory packedFormCategory,
            ItemLike packedForm,
            String unpackingRecipeId,
            String unpackingRecipeGroup,
            String modId
    ) {
        this.nineBlockStorageRecipes(
                unpackedFormCategory, unpackedForm, packedFormCategory, packedForm, getSimpleRecipeName(packedForm), null, unpackingRecipeId, unpackingRecipeGroup, modId
        );
    }

    protected void nineBlockStorageRecipes(
            RecipeCategory unpackedFormCategory,
            ItemLike unpackedForm,
            RecipeCategory packedFormCategory,
            ItemLike packedForm,
            String packingRecipeId,
            @Nullable String packingRecipeGroup,
            String unpackingRecipeId,
            @Nullable String unpackingRecipeGroup,
            String modId
    ) {
        this.shapeless(unpackedFormCategory, unpackedForm, 9)
                .requires(packedForm)
                .group(unpackingRecipeGroup)
                .unlockedBy(getHasName(packedForm), this.has(packedForm))
                .save(this.output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(modId, unpackingRecipeId)));
        this.shaped(packedFormCategory, packedForm)
                .define('#', unpackedForm)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(packingRecipeGroup)
                .unlockedBy(getHasName(unpackedForm), this.has(unpackedForm))
                .save(this.output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(modId, packingRecipeId)));
    }

    protected void oreSmelting(List<ItemLike> smeltables, RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result, float experience, int cookingTime, String group, String modId) {
        this.oreCooking(SmeltingRecipe::new, smeltables, craftingCategory, cookingCategory, result, experience, cookingTime, group, "_from_smelting", modId);

    }


    protected void oreBlasting(
            List<ItemLike> smeltables,
            RecipeCategory craftingCategory,
            CookingBookCategory cookingCategory,
            ItemLike result,
            float experience,
            int cookingTime,
            String group,
            String modId
    ) {
        this.oreCooking(BlastingRecipe::new, smeltables, craftingCategory, cookingCategory, result, experience, cookingTime, group, "_from_blasting", modId);
    }

    protected <T extends AbstractCookingRecipe> void oreCooking(
            AbstractCookingRecipe.Factory<T> factory,
            List<ItemLike> smeltables,
            RecipeCategory craftingCategory,
            CookingBookCategory cookingCategory,
            ItemLike result,
            float experience,
            int cookingTime,
            String group,
            String fromDesc,
            String modId
    ) {
        for (ItemLike item : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(item), craftingCategory, cookingCategory, result, experience, cookingTime, factory)
                    .group(group)
                    .unlockedBy(getHasName(item), this.has(item))
                    .save(this.output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(modId, getItemName(result) + fromDesc + "_" + getItemName(item))));
        }
    }
}
