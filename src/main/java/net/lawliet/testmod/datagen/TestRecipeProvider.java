package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.datagen.advanced_providers.ModdedRecipeProvider;
import net.lawliet.testmod.registries.TestItems;
import net.lawliet.testmod.tags.TestTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import recipe.datagen.CrystallizerRecipeBuilder;

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
        pressurePlate(TestBlocks.AZURITE_PRESSURE_PLATE, TestItems.AZURITE);
        fence(TestBlocks.AZURITE_FENCE, TestItems.AZURITE, TestBlocks.AZURITE_BLOCK, 6);
        fenceGate(TestBlocks.AZURITE_FENCE_GATE, TestItems.AZURITE, TestBlocks.AZURITE_BLOCK, 4);
        wall(RecipeCategory.BUILDING_BLOCKS, TestBlocks.AZURITE_WALLS, TestBlocks.AZURITE_BLOCK);
        createBlockRecipeUsingBuilder(TestBlocks.AZURITE_BUTTON, TestItems.AZURITE, this::buttonBuilder);
        createBlockRecipeUsingBuilder(TestBlocks.AZURITE_DOOR, TestItems.AZURITE,  this::doorBuilder);
        createBlockRecipeUsingBuilder(TestBlocks.AZURITE_TRAPDOOR, TestItems.AZURITE, this::trapdoorBuilder);
        swordBuilder(TestItems.AZURITE_SWORD, TestTags.Items.AZURITE_TOOL_MATERIAL).save(output);
        pickaxeBuilder(TestItems.AZURITE_PICKAXE, TestTags.Items.AZURITE_TOOL_MATERIAL).save(output);
        shovelBuilder(TestItems.AZURITE_SHOVEL, TestTags.Items.AZURITE_TOOL_MATERIAL).save(output);
        axeBuilder(TestItems.AZURITE_AXE, TestTags.Items.AZURITE_TOOL_MATERIAL).save(output);
        hoeBuilder(TestItems.AZURITE_HOE, TestTags.Items.AZURITE_TOOL_MATERIAL).save(output);
        spearBuilder(TestItems.AZURITE_SPEAR, TestTags.Items.AZURITE_TOOL_MATERIAL).save(output);
        helmetBuilder(TestItems.AZURITE_HELMET, TestItems.AZURITE).save(output);
        chestplateBuilder(TestItems.AZURITE_CHESTPLATE, TestItems.AZURITE).save(output);
        leggingsBuilder(TestItems.AZURITE_LEGGINGS, TestItems.AZURITE).save(output);
        bootsBuilder(TestItems.AZURITE_BOOTS, TestItems.AZURITE).save(output);
        makeAzuriteLamp();
        makeDataTablet();
        makeMetalDetector();
        makeTestBow();

        makeCrystallizerRecipe(Items.STICK, Items.END_ROD, 2);
        makeCrystallizerRecipe(TestItems.RAW_AZURITE, TestItems.AZURITE, 3);
        makeCrystallizerRecipe(Blocks.DIRT, Items.NETHER_STAR);
        makeCrystallizerRecipe(TestItems.GOJI_BERRIES, TestItems.END_FIRE_STARTER, 4);
        makeCrystallizerRecipe(Items.REDSTONE, TestItems.DATA_TABLET);
    }

    private void makeCrystallizerRecipe(RecipeCategory category, ItemLike ingredient, ItemLike result, int count) {
        CrystallizerRecipeBuilder.crystallizerRecipe(category, Ingredient.of(ingredient), result, count)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(output);
    }

    private void makeCrystallizerRecipe(RecipeCategory category, ItemLike ingredient, ItemLike result) {
        makeCrystallizerRecipe(category, ingredient, result, 1);
    }

    private void makeCrystallizerRecipe(ItemLike ingredient, ItemLike result) {
        makeCrystallizerRecipe(ingredient, result, 1);
    }

    private void makeCrystallizerRecipe(ItemLike ingredient, ItemLike result, int count) {
        makeCrystallizerRecipe(RecipeCategory.MISC,ingredient, result, count);
    }

    private void makeTestBow() {
        shaped(RecipeCategory.COMBAT, TestItems.TEST_BOW)
                .pattern("A ")
                .pattern(" B")
                .pattern("A ")
                .define('A', TestItems.AZURITE)
                .define('B', Tags.Items.TOOLS_BOW)
                .unlockedBy("has_bow", has(Tags.Items.TOOLS_BOW))
                .unlockedBy("has_azurite",  has(TestItems.AZURITE))
                .save(output);
    }

    private void makeMetalDetector() {
        shaped(RecipeCategory.MISC, TestItems.METAL_DETECTOR)
                .pattern("  S")
                .pattern("SI ")
                .pattern("B  ")
                .define('S', Items.STICK)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('B', Tags.Items.STORAGE_BLOCKS_IRON)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(output);
    }

    private void makeAzuriteLamp() {
        ItemLike item = TestItems.AZURITE;
        ItemLike lamp = Items.REDSTONE_LAMP;
        shaped(RecipeCategory.DECORATIONS, TestBlocks.AZURITE_LAMP)
                .pattern(" A ")
                .pattern("ALA")
                .pattern(" A ")
                .define('A', item)
                .define('L', lamp)
                .unlockedBy(getHasName(lamp), has(lamp))
                .unlockedBy(getHasName(item), has(item))
                .save(output);
    }

    private void makeDataTablet() {
        ItemLike item = TestItems.AZURITE;
        TagKey<Item> glass_pane = Tags.Items.GLASS_PANES;
        shaped(RecipeCategory.MISC, TestItems.DATA_TABLET)
                .pattern(" A ")
                .pattern("AGA")
                .pattern(" A ")
                .define('A', item)
                .define('G', glass_pane)
                .unlockedBy(getHasName(item), has(item))
                .unlockedBy("has_glass_pane", has(glass_pane))
                .save(output);
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
