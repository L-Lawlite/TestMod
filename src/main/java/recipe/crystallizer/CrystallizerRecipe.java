package recipe.crystallizer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.lawliet.testmod.registries.TestRecipes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record CrystallizerRecipe(Ingredient inputItem, ItemStackTemplate output) implements Recipe<CrystallizerRecipeInput> {
    public static final MapCodec<CrystallizerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(CrystallizerRecipe::inputItem),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(CrystallizerRecipe::output)
    ).apply(instance, CrystallizerRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CrystallizerRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, CrystallizerRecipe::inputItem,
            ItemStackTemplate.STREAM_CODEC, CrystallizerRecipe::output,
            CrystallizerRecipe::new
    );

    @Override
    public boolean matches(CrystallizerRecipeInput input, Level level) {
        if(level.isClientSide()) {
            return false;
        }
        final int INPUT_SLOT = 0;
        return inputItem.test(input.getItem(INPUT_SLOT));
    }

    @Override
    public ItemStack assemble(CrystallizerRecipeInput input) {
        return output.create().copy();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "Crystallizing";
    }

    @Override
    public RecipeSerializer<? extends Recipe<CrystallizerRecipeInput>> getSerializer() {
        return TestRecipes.CRYSTALLIZER_RECIPE.serializer().get();
    }

    @Override
    public RecipeType<? extends Recipe<CrystallizerRecipeInput>> getType() {
        return TestRecipes.CRYSTALLIZER_RECIPE.type().get();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }
}
