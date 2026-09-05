package recipe.datagen;

import net.lawliet.testmod.TestMod;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import recipe.crystallizer.CrystallizerRecipe;

public class CrystallizerRecipeBuilder extends SingleInputItemRecipeBuilder {
    protected CrystallizerRecipeBuilder(RecipeCategory category, Ingredient ingredient, ItemStackTemplate result) {
        super(category, ingredient, result);
    }

    public static CrystallizerRecipeBuilder crystallizerRecipe(RecipeCategory category,Ingredient ingredient, ItemLike result, int count) {
        return new CrystallizerRecipeBuilder(category, ingredient, new ItemStackTemplate(result.asItem(), count));
    }

    public static CrystallizerRecipeBuilder crystallizerRecipe(RecipeCategory category, Ingredient ingredient, ItemLike result) {
        return crystallizerRecipe(category, ingredient, result, 1);
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> key) {
        CrystallizerRecipe recipe = new  CrystallizerRecipe(this.ingredient, this.result);
        output.accept(key, recipe, this.advancementBuilder.build(output, key, this.category));
    }


    protected ResourceKey<Recipe<?>> getDefaultRecipeId(ItemInstance result) {
        return getDefaultRecipeId(result, "crystallizer/", TestMod.MODID);
    }
}
