package net.lawliet.testmod.datagen.advancedRecipeProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public abstract class ModdedRecipeProvider extends RecipeProvider {

    private final String modId;

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
                    .save(this.output, getResourceKey(getItemName(result) + fromDesc + "_" + getItemName(item)));
        }
    }

    protected void solidStair(RecipeCategory category, ItemLike stairBlock, ItemLike baseBlock) {
        solidStair(category, stairBlock, baseBlock, null);
    }

    protected void solidStair(RecipeCategory category, ItemLike stair, ItemLike base, @Nullable String group) {
        stairBuilder(stair, Ingredient.of(base))
                .unlockedBy(getHasName(base), has(base))
                .group(group)
                .save(output);
       stoneCutterRecipe(category, stair, base, 1);
    }

    protected void solidSlab(RecipeCategory category, ItemLike slab, ItemLike base) {
        slab(category, slab, base);
        stoneCutterRecipe(category, slab, base, 2);
    }

    protected void stoneCutterRecipe(RecipeCategory category, ItemLike result, ItemLike base, int count) {
        stoneCutterRecipeBuilder(category, result, base, count).save(output,getResourceKey(getConversionRecipeName(result, base)));
    }

    protected void woodenStair(RecipeCategory category, ItemLike stairBlock, ItemLike baseBlock, @Nullable String group) {
        stairBuilder(stairBlock, Ingredient.of(baseBlock))
                .unlockedBy(getHasName(baseBlock), has(baseBlock))
                .group(group)
                .save(output);
    }

    protected void woodenSlab(RecipeCategory category, ItemLike slabBlock, ItemLike baseBlock) {
        slab(category, slabBlock, baseBlock);
    }

    protected void button(ItemLike result, ItemLike base, @Nullable String group) {
        buttonBuilder(result, Ingredient.of(base))
                .unlockedBy(getHasName(base), has(base))
                .group(group)
                .save(output);
    }

    protected void button(ItemLike result, ItemLike base) {
        button(result, base, null);
    }

    protected SingleItemRecipeBuilder stoneCutterRecipeBuilder(RecipeCategory category, ItemLike result, ItemLike base, int count){
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(base), category, result, count).unlockedBy(getHasName(base), this.has(base));
    }

    private ResourceKey<Recipe<?>> getResourceKey(String string) {
        return getResourceKey(modId, string);
    }

    protected ResourceKey<Recipe<?>> getResourceKey(String modId,String string) {
        return ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(modId, string));
    }

}
