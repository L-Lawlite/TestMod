package net.lawliet.testmod.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.gui.menu.CrystallizerMenu;
import net.lawliet.testmod.registries.TestBlocks;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.Nullable;
import recipe.crystallizer.CrystallizerRecipe;

import java.util.List;

public class CrystallizerRecipeCategory implements IRecipeCategory<RecipeHolder<CrystallizerRecipe>> {
    public static final Identifier TEXTURE = TestMod.createIdentifier("textures/gui/crystallizer/crystallizer_gui.png");
    private final IDrawable icon;
    private final IDrawable overlay;

    private final int XOffset = 5;
    private final int YOffset = 5;

    public CrystallizerRecipeCategory(IGuiHelper helper) {
        this.overlay = helper.createDrawable(TEXTURE, XOffset, YOffset, 165, 75);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, getFirstItemStack());
    }

    public static List<Block> getBlocks() {
        return List.of(
                TestBlocks.CRYSTALLIZER.get()
        );
    }

    public static ItemStack getFirstItemStack() {
        return new ItemStack(getBlocks().getFirst());
    }

    public static ItemStack[] getItemStacks() {
        return getBlocks().stream().map(ItemStack::new).toArray(ItemStack[]::new);
    }

    /**
     * @return the type of recipe that this category handles.
     * @since 9.5.0
     */
    @Override
    public IRecipeType<RecipeHolder<CrystallizerRecipe>> getRecipeType() {
        return ModJEIPlugin.CRYSTALLIZER.recipeType();
    }

    /**
     * Returns a text component representing the name of this recipe type.
     * Drawn at the top of the recipe GUI pages for this category.
     *
     * @since 7.6.4
     */
    @Override
    public Component getTitle() {
        return TestBlocks.CRYSTALLIZER.get().getName();
    }

    /**
     * Returns the width of recipe layouts that are drawn for this recipe category.
     *
     * @apiNote in 27.0.0 getBackground was removed, and implementing this method became mandatory.
     * @since 11.5.0
     */
    @Override
    public int getWidth() {
        return this.overlay.getWidth();
    }

    /**
     * Returns the height of recipe layouts that are drawn for this recipe category.
     *
     * @apiNote in 27.0.0 getBackground was removed, and implementing this method became mandatory.
     * @since 11.5.0
     */
    @Override
    public int getHeight() {
        return this.overlay.getHeight();
    }

    /**
     * Icon for the category tab.
     * You can use {@link IGuiHelper#createDrawableIngredient(IIngredientType, Object)}
     * to create a drawable from an ingredient.
     * <p>
     * If null is returned here, JEI will try to use the first crafting station as the icon.
     *
     * @return icon to draw on the category tab, max size is 16x16 pixels.
     */
    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    /**
     * Sets all the recipe's ingredients by filling out an instance of {@link IRecipeLayoutBuilder}.
     * This is used by JEI for lookups, to figure out what ingredients are inputs and outputs for a recipe.
     *
     */
    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<CrystallizerRecipe> recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, CrystallizerMenu.INPUT_SLOT_X - XOffset, CrystallizerMenu.INPUT_SLOT_Y - YOffset).add(recipe.value().inputItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, CrystallizerMenu.OUTPUT_SLOT_X - XOffset, CrystallizerMenu.OUTPUT_SLOT_Y - YOffset).add(recipe.value().output());
    }

    @Override
    public void draw(RecipeHolder<CrystallizerRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);
    }
}
