package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.block.GojiBerryBlock;
import net.lawliet.testmod.block.crop.RiceCropBlock;
import net.lawliet.testmod.block.state.TestBlockStateProperties;
import net.lawliet.testmod.block.crop.OnionBlock;
import net.lawliet.testmod.item.TestEquipmentAssets;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.registries.TestDataComponent;
import net.lawliet.testmod.registries.TestItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.conditional.HasComponent;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Optional;

public class TestModelProvider extends ModelProvider {
    public TestModelProvider(PackOutput output) {
        super(output, TestMod.MODID);
    }

    private static ItemModelGenerators itemModels;
    private static BlockModelGenerators blockModels;

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        TestModelProvider.itemModels = itemModels;
        TestModelProvider.blockModels = blockModels;

        itemModels.generateFlatItem(TestItems.AZURITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(TestItems.RAW_AZURITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(TestItems.METAL_DETECTOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(TestItems.END_FIRE_STARTER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(TestItems.AZURITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(TestItems.AZURITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(TestItems.AZURITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(TestItems.AZURITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(TestItems.AZURITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateSpear(TestItems.AZURITE_SPEAR.get());
        itemModels.generateTrimmableItem(TestItems.AZURITE_HELMET.get(), TestEquipmentAssets.AZURITE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(TestItems.AZURITE_CHESTPLATE.get(), TestEquipmentAssets.AZURITE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(TestItems.AZURITE_LEGGINGS.get(), TestEquipmentAssets.AZURITE, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(TestItems.AZURITE_BOOTS.get(), TestEquipmentAssets.AZURITE, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModels.generateFlatItem(TestItems.AZURITE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        this.createDataTablet(TestItems.DATA_TABLET.get());
        this.createBow(TestItems.TEST_BOW.get());
        itemModels.generateFlatItem(TestItems.BAR_BRAWL_MUSIC_DISC.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(TestItems.RADIATION_STAFF.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.declareCustomModelItem(TestItems.BLIZZARD_STAFF.get());

        blockModels.createTrivialCube(TestBlocks.RAW_AZURITE_BLOCK.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_ORE.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_DEEPSLATE_ORE.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_NETHER_ORE.get());
        blockModels.createTrivialCube(TestBlocks.AZURITE_END_ORE.get());
        blockModels.createTrivialCube(TestBlocks.MAGIC_BLOCK.get());
        blockModels.createShelf(TestBlocks.TEST_SHELF.get(), Blocks.STRIPPED_ACACIA_LOG);
        blockModels.family(TestBlocks.AZURITE_BLOCK.get())
                .stairs(TestBlocks.AZURITE_STAIRS.get())
                .slab(TestBlocks.AZURITE_SLAB.get())
                .pressurePlate(TestBlocks.AZURITE_PRESSURE_PLATE.get())
                .button(TestBlocks.AZURITE_BUTTON.get())
                .fence(TestBlocks.AZURITE_FENCE.get())
                .fenceGate(TestBlocks.AZURITE_FENCE_GATE.get())
                .wall(TestBlocks.AZURITE_WALLS.get())
                .door(TestBlocks.AZURITE_DOOR.get())
                .trapdoor(TestBlocks.AZURITE_TRAPDOOR.get());
        this.createLamp(TestBlocks.AZURITE_LAMP.get(), TestBlockStateProperties.CLICKED);
        blockModels.createCropBlock(TestBlocks.ONION.get(), OnionBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(TestBlocks.GOJI_BERRY_BUSH.get(), GojiBerryBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(TestBlocks.RICE.get(), RiceCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockModels.createHorizontallyRotatedBlock(TestBlocks.CRYSTALLIZER.get(), TexturedModel.ORIENTABLE);

        blockModels.createNonTemplateModelBlock(TestBlocks.PEDESTAL.get());


    }

    public void createLamp(Block block, BooleanProperty litProperty) {
        MultiVariant off = BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(block, blockModels.modelOutput));
        MultiVariant on = BlockModelGenerators.plainVariant(blockModels.createSuffixedVariant(block, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(BlockModelGenerators.createBooleanModelDispatch(litProperty, on, off)));
    }

    public void createDataTablet(Item item) {
        ItemModel.Unbaked unbakedDataTablet = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked unbakedDataTabletOn = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_on", ModelTemplates.FLAT_ITEM));
        itemModels.itemModelOutput.register(item,
                new ClientItem(
                        new ConditionalItemModel.Unbaked(Optional.empty(),
                                new HasComponent(TestDataComponent.BLOCK_DATA.get(), false) , unbakedDataTabletOn, unbakedDataTablet
                        ),
                        new ClientItem.Properties(false, false, 1f)
                ));
    }

    public void createBow(Item item) {
        itemModels.createFlatItemModel(item, ModelTemplates.BOW);
        itemModels.generateBow(item);
    }

    @SuppressWarnings("unused")
    public void createLamp(Block block) {
        createLamp(block, BlockStateProperties.LIT);
    }
}
