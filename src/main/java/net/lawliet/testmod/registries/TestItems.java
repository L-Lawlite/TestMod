package net.lawliet.testmod.registries;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.datagen.TestContextIntProviders;
import net.lawliet.testmod.food.TestConsumables;
import net.lawliet.testmod.food.TestFoods;
import net.lawliet.testmod.item.*;
import net.lawliet.testmod.registries.entity.TestEntityTypes;
import net.lawliet.testmod.tags.TestTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
public class TestItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TestMod.MODID);
    public static final DeferredRegister.Items NON_CREATIVE_ITEMS = DeferredRegister.createItems(TestMod.MODID);
    public static final DeferredRegister.Items OPERATOR_ITEMS = DeferredRegister.createItems(TestMod.MODID);

    public static final DeferredItem<Item> AZURITE = ITEMS.registerSimpleItem("azurite");
    public static final DeferredItem<Item> RAW_AZURITE = ITEMS.registerSimpleItem("raw_azurite");
    public static final DeferredItem<Item> METAL_DETECTOR = ITEMS.registerItem("metal_detector", MetalDetectorItem::new, properties -> properties.durability(64).repairable(Tags.Items.INGOTS_IRON));
    public static final DeferredItem<Item> END_FIRE_STARTER = ITEMS.registerSimpleItem("end_fire_starter", properties -> properties.cookingFuel(TestContextIntProviders.COOKING_TIME_END_FIRE_STARTER));
    public static final DeferredItem<Item> AZURITE_SWORD = ITEMS.registerSimpleItem("azurite_sword", properties -> properties.sword(TestToolMaterials.AZURITE, 3, -2.4f));
    public static final DeferredItem<Item> AZURITE_PICKAXE = ITEMS.registerSimpleItem("azurite_pickaxe", properties -> properties.pickaxe(TestToolMaterials.AZURITE, 1, -2.8f));
    public static final DeferredItem<Item> AZURITE_SHOVEL = ITEMS.registerSimpleItem("azurite_shovel", properties -> properties.shovel(TestToolMaterials.AZURITE, 1.5f, -3f));
    public static final DeferredItem<Item> AZURITE_AXE = ITEMS.registerSimpleItem("azurite_axe", properties -> properties.axe(TestToolMaterials.AZURITE, 6f, -3.2f));
    public static final DeferredItem<Item> AZURITE_HOE = ITEMS.registerSimpleItem("azurite_hoe", properties -> properties.hoe(TestToolMaterials.AZURITE, 0, -3.8f));
    public static final DeferredItem<Item> AZURITE_SPEAR = ITEMS.registerSimpleItem("azurite_spear", properties -> properties.spear(TestToolMaterials.AZURITE, 0.95f, 0.7f, 0.7f, 3.5f, 13f, 8.5f, 5.1f, 13.37f, 4.67f));

    public static final DeferredItem<Item> AZURITE_HELMET = ITEMS.registerSimpleItem("azurite_helmet", properties -> properties.humanoidArmor(TestArmorMaterials.AZURITE, ArmorType.HELMET));
    public static final DeferredItem<Item> AZURITE_CHESTPLATE = ITEMS.registerSimpleItem("azurite_chestplate", properties -> properties.humanoidArmor(TestArmorMaterials.AZURITE, ArmorType.CHESTPLATE));
    public static final DeferredItem<Item> AZURITE_LEGGINGS = ITEMS.registerSimpleItem("azurite_leggings", properties -> properties.humanoidArmor(TestArmorMaterials.AZURITE, ArmorType.LEGGINGS));
    public static final DeferredItem<Item> AZURITE_BOOTS = ITEMS.registerSimpleItem("azurite_boots", properties -> properties.humanoidArmor(TestArmorMaterials.AZURITE, ArmorType.BOOTS));
    public static final DeferredItem<Item> AZURITE_HORSE_ARMOR = ITEMS.registerSimpleItem("azurite_horse_armor", properties -> properties.horseArmor(TestArmorMaterials.AZURITE));
    public static final DeferredItem<Item> TEST_BOW = ITEMS.registerItem("test_bow", BowItem::new, properties -> properties.enchantable(1).durability(400));
    public static final DeferredItem<Item> RADIATION_STAFF = ITEMS.registerSimpleItem("radiation_staff", properties -> properties.rarity(Rarity.RARE).stacksTo(1));

    public static final DeferredItem<Item> DATA_TABLET = ITEMS.registerItem("data_tablet", DataTabletItem::new, properties -> properties.stacksTo(1));
    public static final DeferredItem<Item> BLIZZARD_STAFF = ITEMS.registerSimpleItem("blizzard_staff", properties -> properties.stacksTo(1));

    public static final DeferredItem<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.registerSimpleItem("bar_brawl_music_disc", properties -> properties.jukeboxPlayable(TestSounds.BAR_BRAWL.key()).rarity(Rarity.EPIC).stacksTo(1));

    public static final DeferredItem<BlockItem> ONION = ITEMS.registerItem("onion", properties -> new BlockItem(TestBlocks.ONION.get(), properties.food(TestFoods.ONION, TestConsumables.ONION).useItemDescriptionPrefix().compostable(ContextIntProviders.COMPOSTABLE_MEDIUM)));
    public static final DeferredItem<BlockItem> GOJI_BERRIES = ITEMS.registerItem("goji_berries", properties -> new BlockItem(TestBlocks.GOJI_BERRY_BUSH.get(), properties.food(TestFoods.GOJI_BERRY).compostable(ContextIntProviders.COMPOSTABLE_LOW)));
    public static final DeferredItem<BlockItem> RICE = ITEMS.registerItem("rice", properties -> new PlaceOnShallowWaterBlockItem(TestBlocks.RICE.get(), properties.compostable(ContextIntProviders.COMPOSTABLE_LOW_MEDIUM), TestTags.Blocks.RICE_FARMLAND));
    public static final DeferredItem<Item> DRIFTWOOD_BOAT = ITEMS.registerItem("driftwood_boat", properties -> new BoatItem(TestEntityTypes.DRIFTWOOD_BOAT.get(), properties.stacksTo(1).cookingFuel(ContextIntProviders.COOKING_TIME_BOATS)));
    public static final DeferredItem<BlockItem> TEST_SHELF = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.TEST_SHELF, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS).component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
    public static final DeferredItem<BlockItem> DRIFTWOOD_LOG = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_LOG, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
    public static final DeferredItem<BlockItem> DRIFTWOOD_WOOD = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_WOOD, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
    public static final DeferredItem<BlockItem> STRIPPED_DRIFTWOOD_LOG = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.STRIPPED_DRIFTWOOD_LOG, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
    public static final DeferredItem<BlockItem> STRIPPED_DRIFTWOOD_WOOD = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.STRIPPED_DRIFTWOOD_WOOD, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
    public static final DeferredItem<BlockItem> DRIFTWOOD_PLANKS = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_PLANKS, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
    public static final DeferredItem<BlockItem> DRIFTWOOD_STAIRS = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_STAIRS, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
    public static final DeferredItem<BlockItem> DRIFTWOOD_SLAB = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_SLAB, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
    public static final DeferredItem<BlockItem> DRIFTWOOD_PRESSURE_PLATE = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_PRESSURE_PLATE, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
    public static final DeferredItem<BlockItem> DRIFTWOOD_BUTTON = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_BUTTON, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
    public static final DeferredItem<BlockItem> DRIFTWOOD_FENCE = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_FENCE, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
    public static final DeferredItem<BlockItem> DRIFTWOOD_FENCE_GATE = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_FENCE_GATE, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
    public static final DeferredItem<BlockItem> DRIFTWOOD_SHELF = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_SHELF, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS).component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
    public static final DeferredItem<BlockItem> DRIFTWOOD_SAPLING = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_SAPLING, properties -> properties.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS).compostable(ContextIntProviders.COMPOSTABLE_LOW));
    public static final DeferredItem<BlockItem> DRIFTWOOD_LEAVES = NON_CREATIVE_ITEMS.registerSimpleBlockItem(TestBlocks.DRIFTWOOD_LEAVES, properties -> properties.compostable(ContextIntProviders.COMPOSTABLE_LOW));


    public static final DeferredItem<Item> DODO_SPAWN_EGG = ITEMS.registerItem("dodo_spawn_egg", SpawnEggItem::new, properties -> properties.spawnEgg(TestEntityTypes.DODO.get()));
    public static final DeferredItem<Item> WAR_TURTLE_SPAWN_EGG = ITEMS.registerItem("war_turtle_spawn_egg", SpawnEggItem::new, properties -> properties.spawnEgg(TestEntityTypes.WAR_TURTLE.get()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        NON_CREATIVE_ITEMS.register(eventBus);
        OPERATOR_ITEMS.register(eventBus);
        eventBus.addListener(TestItems::addCreative);
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    public static void addToTestItemTab(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        for (var item: ITEMS.getEntries()) {
            output.accept(item.get());
        }
    }
}
