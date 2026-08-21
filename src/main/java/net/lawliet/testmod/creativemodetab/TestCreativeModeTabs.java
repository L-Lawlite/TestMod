package net.lawliet.testmod.creativemodetab;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.item.TestItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TestCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MODID);

    public static final Supplier<CreativeModeTab> AZURITE_ITEMS_TAB = CREATIVE_MODE_TABS.register("azurite_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(TestItems.AZURITE.get()))
                    .title(Component.translatable("creativetab.testmod.test_items"))
                    .displayItems(TestItems::addToTestItemTab)
                    .build());

    public static final Supplier<CreativeModeTab> AZURITE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("azurite_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(TestItems.RAW_AZURITE.get()))
                    .title(Component.translatable("creativetab.testmod.test_blocks"))
                    .displayItems(TestItems::addToTestItemTab)
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
