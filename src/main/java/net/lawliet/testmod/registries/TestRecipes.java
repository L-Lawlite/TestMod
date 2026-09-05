package net.lawliet.testmod.registries;

import com.mojang.serialization.MapCodec;
import net.lawliet.testmod.TestMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import recipe.crystallizer.CrystallizerRecipe;

public class TestRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, TestMod.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, TestMod.MODID);

    public static final RecipeRegister<CrystallizerRecipe> CRYSTALLIZER_RECIPE = RecipeRegister.create("crystallizing", CrystallizerRecipe.CODEC, CrystallizerRecipe.STREAM_CODEC);


    public record RecipeRegister<T extends Recipe<?>>(DeferredHolder<RecipeSerializer<?>, RecipeSerializer<T>> serializer, DeferredHolder<RecipeType<?>, RecipeType<T>> type){
        public static <T extends Recipe<?>> RecipeRegister<T> create(String name, MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
            DeferredHolder<RecipeSerializer<?>, RecipeSerializer<T>> serializer = SERIALIZERS.register(name, () -> new RecipeSerializer<>(codec, streamCodec));
            DeferredHolder<RecipeType<?>, RecipeType<T>> recipeType = RECIPE_TYPES.register(name,() -> new RecipeType<>() {
                @Override
                public String toString() {
                    return name;
                }
            });
            return new RecipeRegister<>(serializer, recipeType);
        }
    }

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
        RECIPE_TYPES.register(bus);
    }
}
