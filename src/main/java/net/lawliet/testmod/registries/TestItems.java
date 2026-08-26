package net.lawliet.testmod.registries;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.food.TestConsumables;
import net.lawliet.testmod.food.TestFoods;
import net.lawliet.testmod.item.MetalDetectorItem;
import net.lawliet.testmod.item.TestToolMaterials;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TestItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TestMod.MODID);

    public static final DeferredItem<Item> AZURITE = ITEMS.registerSimpleItem("azurite");
    public static final DeferredItem<Item> RAW_AZURITE = ITEMS.registerSimpleItem("raw_azurite");
    public static final DeferredItem<Item> METAL_DETECTOR = ITEMS.registerItem("metal_detector", MetalDetectorItem::new, properties -> properties.durability(64));
    public static final DeferredItem<Item> ONION = ITEMS.registerSimpleItem("onion", properties -> properties.food(TestFoods.ONION, TestConsumables.ONION));
    public static final DeferredItem<Item> END_FIRE_STARTER = ITEMS.registerSimpleItem("end_fire_starter");
    public static final DeferredItem<Item> AZURITE_SWORD = ITEMS.registerSimpleItem("azurite_sword", properties -> properties.sword(TestToolMaterials.AZURITE, 3, -2.4f));
    public static final DeferredItem<Item> AZURITE_PICKAXE = ITEMS.registerSimpleItem("azurite_pickaxe", properties -> properties.pickaxe(TestToolMaterials.AZURITE, 1, -2.8f));
    public static final DeferredItem<Item> AZURITE_SHOVEL = ITEMS.registerItem("azurite_shovel", properties -> new ShovelItem( TestToolMaterials.AZURITE, 1.5f, -3f, properties));
    public static final DeferredItem<Item> AZURITE_AXE = ITEMS.registerItem("azurite_axe", properties -> new AxeItem( TestToolMaterials.AZURITE, 6f, -3.2f, properties));
    public static final DeferredItem<Item> AZURITE_HOE = ITEMS.registerItem("azurite_hoe", properties -> new HoeItem( TestToolMaterials.AZURITE, 0, -3.8f, properties));
    public static final DeferredItem<Item> AZURITE_SPEAR = ITEMS.registerSimpleItem("azurite_spear", properties -> properties.spear(TestToolMaterials.AZURITE, 0.95f, 0.7f, 0.7f, 3.5f, 13f, 8.5f, 5.1f, 13.37f, 4.67f));



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
    }
}
