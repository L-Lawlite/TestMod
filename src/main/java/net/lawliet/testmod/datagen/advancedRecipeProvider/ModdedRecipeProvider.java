package net.lawliet.testmod.datagen.advancedRecipeProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.Nullable;

public abstract class ModdedRecipeProvider extends RecipeProvider {
    protected final String modId;

    protected ModdedRecipeProvider(HolderLookup.Provider registries, RecipeOutput output, String modId) {
        super(registries, output);
        this.modId = modId;
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
                .save(this.output, ResourceKey.create(Registries.RECIPE, Identifier.parse(unpackingRecipeId)));
        this.shaped(packedFormCategory, packedForm)
                .define('#', unpackedForm)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(packingRecipeGroup)
                .unlockedBy(getHasName(unpackedForm), this.has(unpackedForm))
                .save(this.output, ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(packingRecipeId, modId)));
    }
}
