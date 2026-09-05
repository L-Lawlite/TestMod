package recipe.datagen;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.Nullable;

public abstract class SingleInputItemRecipeBuilder implements RecipeBuilder {
    protected final RecipeCategory category;
    protected final ItemStackTemplate result;
    protected final Ingredient ingredient;
    protected final RecipeUnlockAdvancementBuilder advancementBuilder =  new RecipeUnlockAdvancementBuilder();
    protected @Nullable String group;

    protected SingleInputItemRecipeBuilder(RecipeCategory recipeCategory,Ingredient ingredient, ItemStackTemplate result) {
        this.result = result;
        this.ingredient = ingredient;
        this.category = recipeCategory;
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return this.getDefaultRecipeId(this.result);
    }

    private ResourceKey<Recipe<?>> getDefaultRecipeId(Identifier identifier) {
        return ResourceKey.create(Registries.RECIPE,identifier);
    }

    protected ResourceKey<Recipe<?>> getDefaultRecipeId(ItemInstance result, String prefix, String modId) {
        Identifier resultIdentifier = result.typeHolder().unwrapKey().orElseThrow().identifier().withPrefix(prefix);
        return getDefaultRecipeId(Identifier.fromNamespaceAndPath(modId, resultIdentifier.getPath()));
    }

    private ResourceKey<Recipe<?>> getDefaultRecipeId(ItemInstance result, String prefix) {
        Identifier resultIdentifier = result.typeHolder().unwrapKey().orElseThrow().identifier().withPrefix(prefix);
        return getDefaultRecipeId(resultIdentifier);
    }

    protected ResourceKey<Recipe<?>> getDefaultRecipeId(ItemInstance result) {
        return this.getDefaultRecipeId(result, "");
    }

}
