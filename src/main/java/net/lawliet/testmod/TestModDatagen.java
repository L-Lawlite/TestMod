package net.lawliet.testmod;

import net.lawliet.testmod.datagen.*;
import net.lawliet.testmod.datagen.advancedRecipeProvider.TestDataMapProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = TestMod.MODID)
public class TestModDatagen {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        event.createProvider(TestModelProvider::new);
        event.createBlockAndItemTags(TestBlockTagProvider::new, TestItemTagProvider::new);

        event.createProvider(((output, lookupProvider) -> new LootTableProvider(
                output,
                Set.of(),
                List.of(
                        new LootTableProvider.SubProviderEntry(TestBlockLootProvider::new, LootContextParamSets.BLOCK)
                ),
                lookupProvider
        )));
        event.createProvider(TestRecipeProvider.Runner::new);
        event.createProvider(TestDataMapProvider::new);
        event.createProvider(TestEquipmentAssetProvider::new);
    }
}
