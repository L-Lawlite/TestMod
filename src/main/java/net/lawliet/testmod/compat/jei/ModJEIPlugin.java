package net.lawliet.testmod.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.gui.screen.CrystallizerScreen;
import net.lawliet.testmod.registries.TestRecipes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.lawliet.testmod.recipe.crystallizer.CrystallizerRecipe;

import java.util.List;

@JeiPlugin
public class ModJEIPlugin implements IModPlugin {
    private static RecipeMap syncedRecipes = RecipeMap.EMPTY;

    public static final RecipeData<CrystallizerRecipe> CRYSTALLIZER = RecipeData.create(TestRecipes.CRYSTALLIZER_RECIPE);


    /**
     * The unique ID for this mod plugin.
     * The namespace of the ID should be your mod's modId.
     */
    @Override
    public Identifier getPluginUid() {
        return TestMod.createIdentifier("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new CrystallizerRecipeCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(CRYSTALLIZER.recipeType(), getRecipes(syncedRecipes, CRYSTALLIZER.recipeRegister().type().get()));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CrystallizerScreen.class, 74, 30, 22, 20, CRYSTALLIZER.recipeType());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(CRYSTALLIZER.recipeType(), CrystallizerRecipeCategory.getItemStacks());
    }



    @EventBusSubscriber(modid = TestMod.MODID)
    public static class ServerRecipeSync {
        @SubscribeEvent
        public static void onDatapackSync(OnDatapackSyncEvent event) {
            for (var recipe: TestRecipes.RECIPE_TYPES.getEntries()) {
                event.sendRecipes(recipe.get());
            }
        }
    }

    public static  <I extends RecipeInput, T extends Recipe<I>> List<RecipeHolder<T>> getRecipes(RecipeMap recipeMap, RecipeType<T> recipeType) {
        return recipeMap.byType(recipeType).stream().toList();
    }

    @EventBusSubscriber(modid = TestMod.MODID, value = Dist.CLIENT)
    public static class ClientRecipeSync {
        @SubscribeEvent
        public static void onRecipeReceived(RecipesReceivedEvent event) {
            syncedRecipes = event.getRecipeMap();
        }
    }

    public record RecipeData<T extends Recipe<?>>(IRecipeType<RecipeHolder<T>> recipeType, TestRecipes.RecipeRegister<T> recipeRegister) {

        public static <T extends  Recipe<?>> RecipeData<T> create(TestRecipes.RecipeRegister<T> recipeRegister) {
            return new RecipeData<>(createRecipeHolder(recipeRegister.name()), recipeRegister);
        }

        @SuppressWarnings("unchecked")
        private static <T> IRecipeType<T> createRecipeHolder(String path) {
            return (IRecipeType<T>) IRecipeType.create(TestMod.createIdentifier(path), RecipeHolder.class);
        }



    }
}
