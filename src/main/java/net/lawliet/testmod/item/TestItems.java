package net.lawliet.testmod.item;

import net.lawliet.testmod.block.TestBlocks;
import net.lawliet.testmod.TestMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TestItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TestMod.MODID);

    public static final DeferredItem<Item> AZURITE = ITEMS.registerSimpleItem("azurite");
    public static final DeferredItem<Item> RAW_AZURITE = ITEMS.registerSimpleItem("raw_azurite");


    public static final DeferredItem<BlockItem> AZURITE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(TestBlocks.AZURITE_BLOCK);
    public static final DeferredItem<BlockItem> RAW_AZURITE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(TestBlocks.RAW_AZURITE_BLOCK);
    public static final DeferredItem<BlockItem> AZURITE_ORE_ITEM = ITEMS.registerSimpleBlockItem(TestBlocks.AZURITE_ORE);
    public static final DeferredItem<BlockItem> AZURITE_DEEPSLATE_ORE_ITEM = ITEMS.registerSimpleBlockItem(TestBlocks.AZURITE_DEEPSLATE_ORE);
    public static final DeferredItem<BlockItem> AZURITE_NETHER_ORE_ITEM = ITEMS.registerSimpleBlockItem(TestBlocks.AZURITE_NETHER_ORE);
    public static final DeferredItem<BlockItem> AZURITE_END_ORE_ITEM = ITEMS.registerSimpleBlockItem(TestBlocks.AZURITE_END_ORE);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        eventBus.addListener(TestItems::addCreative);
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    public static void addToTestItemTab(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        output.accept(AZURITE);
        output.accept(RAW_AZURITE);
    }
}
