package net.lawliet.testmod.registries;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.food.TestConsumables;
import net.lawliet.testmod.food.TestFoods;
import net.lawliet.testmod.item.DataTabletItem;
import net.lawliet.testmod.item.MetalDetectorItem;
import net.lawliet.testmod.item.TestArmorMaterials;
import net.lawliet.testmod.item.TestToolMaterials;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TestItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TestMod.MODID);

    public static final DeferredItem<Item> AZURITE = ITEMS.registerSimpleItem("azurite");
    public static final DeferredItem<Item> RAW_AZURITE = ITEMS.registerSimpleItem("raw_azurite");
    public static final DeferredItem<Item> METAL_DETECTOR = ITEMS.registerItem("metal_detector", MetalDetectorItem::new, properties -> properties.durability(64).repairable(Tags.Items.INGOTS_IRON));
    public static final DeferredItem<Item> END_FIRE_STARTER = ITEMS.registerSimpleItem("end_fire_starter");
    public static final DeferredItem<Item> AZURITE_SWORD = ITEMS.registerSimpleItem("azurite_sword", properties -> properties.sword(TestToolMaterials.AZURITE, 3, -2.4f));
    public static final DeferredItem<Item> AZURITE_PICKAXE = ITEMS.registerSimpleItem("azurite_pickaxe", properties -> properties.pickaxe(TestToolMaterials.AZURITE, 1, -2.8f));
    public static final DeferredItem<Item> AZURITE_SHOVEL = ITEMS.registerItem("azurite_shovel", properties -> new ShovelItem( TestToolMaterials.AZURITE, 1.5f, -3f, properties));
    public static final DeferredItem<Item> AZURITE_AXE = ITEMS.registerItem("azurite_axe", properties -> new AxeItem( TestToolMaterials.AZURITE, 6f, -3.2f, properties));
    public static final DeferredItem<Item> AZURITE_HOE = ITEMS.registerItem("azurite_hoe", properties -> new HoeItem( TestToolMaterials.AZURITE, 0, -3.8f, properties));
    public static final DeferredItem<Item> AZURITE_SPEAR = ITEMS.registerSimpleItem("azurite_spear", properties -> properties.spear(TestToolMaterials.AZURITE, 0.95f, 0.7f, 0.7f, 3.5f, 13f, 8.5f, 5.1f, 13.37f, 4.67f));

    public static final DeferredItem<Item> AZURITE_HELMET = ITEMS.registerSimpleItem("azurite_helmet", properties -> properties.humanoidArmor(TestArmorMaterials.AZURITE, ArmorType.HELMET));
    public static final DeferredItem<Item> AZURITE_CHESTPLATE = ITEMS.registerSimpleItem("azurite_chestplate", properties -> properties.humanoidArmor(TestArmorMaterials.AZURITE, ArmorType.CHESTPLATE));
    public static final DeferredItem<Item> AZURITE_LEGGINGS = ITEMS.registerSimpleItem("azurite_leggings", properties -> properties.humanoidArmor(TestArmorMaterials.AZURITE, ArmorType.LEGGINGS));
    public static final DeferredItem<Item> AZURITE_BOOTS = ITEMS.registerSimpleItem("azurite_boots", properties -> properties.humanoidArmor(TestArmorMaterials.AZURITE, ArmorType.BOOTS));
    public static final DeferredItem<Item> AZURITE_HORSE_ARMOR = ITEMS.registerSimpleItem("azurite_horse_armor", properties -> properties.horseArmor(TestArmorMaterials.AZURITE));
    public static final DeferredItem<Item> TEST_BOW = ITEMS.registerItem("test_bow", BowItem::new, properties -> properties.enchantable(1).durability(400));

    public static final DeferredItem<Item> DATA_TABLET = ITEMS.registerItem("data_tablet", DataTabletItem::new, properties -> properties.stacksTo(1));
    public static final DeferredItem<Item> BLIZZARD_STAFF = ITEMS.registerSimpleItem("blizzard_staff", properties -> properties.stacksTo(1));

    public static final DeferredItem<BlockItem> ONION = ITEMS.registerItem("onion", properties -> new BlockItem(TestBlocks.ONION.get(), properties.food(TestFoods.ONION, TestConsumables.ONION).useItemDescriptionPrefix()));
    public static final DeferredItem<BlockItem> GOJI_BERRIES = ITEMS.registerItem("goji_berries", properties -> new BlockItem(TestBlocks.GOJI_BERRY_BUSH.get(), properties.food(TestFoods.GOJI_BERRY)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        eventBus.addListener(TestItems::addCreative);
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    public static void addToTestItemTab(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        output.accept(AZURITE);
        output.accept(RAW_AZURITE);
        output.accept(ONION);
        output.accept(METAL_DETECTOR);
        output.accept(END_FIRE_STARTER);
        output.accept(AZURITE_SWORD);
        output.accept(AZURITE_PICKAXE);
        output.accept(AZURITE_SHOVEL);
        output.accept(AZURITE_AXE);
        output.accept(AZURITE_SPEAR);
        output.accept(AZURITE_HOE);
        output.accept(AZURITE_HELMET);
        output.accept(AZURITE_CHESTPLATE);
        output.accept(AZURITE_LEGGINGS);
        output.accept(AZURITE_BOOTS);
        output.accept(AZURITE_HORSE_ARMOR);
        output.accept(DATA_TABLET);
        output.accept(TEST_BOW);
        output.accept(BLIZZARD_STAFF);
        output.accept(GOJI_BERRIES);
    }
}
