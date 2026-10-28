package net.lawliet.testmod;

import net.lawliet.testmod.datagen.*;
import net.lawliet.testmod.datagen.TestDataMapProvider;
import net.lawliet.testmod.datagen.damagesource.TestDamageTypes;
import net.lawliet.testmod.datagen.loot.sub_providers.TestBlockInteractionLootProvider;
import net.lawliet.testmod.datagen.loot.sub_providers.TestBlockLootProvider;
import net.lawliet.testmod.datagen.loot.sub_providers.TestEntityLootProvider;
import net.lawliet.testmod.datagen.loot.sub_providers.TestExtraLootProvider;
import net.lawliet.testmod.datagen.painting.TestPainting;
import net.lawliet.testmod.datagen.painting.TestPaintingTagsProvider;
import net.lawliet.testmod.datagen.tags.TestBlockTagProvider;
import net.lawliet.testmod.datagen.tags.TestEntityTypesTagProvider;
import net.lawliet.testmod.datagen.tags.TestItemTagProvider;
import net.lawliet.testmod.datagen.villager.TestPoiTags;
import net.lawliet.testmod.datagen.villager.TestTradeSets;
import net.lawliet.testmod.datagen.villager.TestVillagerTradeTags;
import net.lawliet.testmod.datagen.villager.TestVillagerTrades;
import net.lawliet.testmod.registries.TestSounds;
import net.lawliet.testmod.registries.custom.CustomRegistry;
import net.lawliet.testmod.worldgen.TestBiomeModifiers;
import net.lawliet.testmod.worldgen.TestFeatures;
import net.lawliet.testmod.worldgen.TestPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = TestMod.MODID)
public class TestModDatagen {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        event.createProvider(TestModelProvider::new);
        event.createBlockAndItemTags(TestBlockTagProvider::new, TestItemTagProvider::new);
        event.createProvider(TestEntityTypesTagProvider::new);
        event.createProvider(TestDataMapProvider::new);
        event.createProvider(TestEquipmentAssetProvider::new);
        event.createProvider(TestPaintingTagsProvider::new);
        event.createProvider(TestSoundsProvider::new);
        event.createProvider(TestVillagerTradeTags::new);
        event.createProvider(TestPoiTags::new);
        event.createProvider(TestGLMProvider::new);
    }

    @SubscribeEvent
    public static void gatherDatapackData(GatherDataRegistryEntriesEvent event) {
        event.gatherFor(TestMod.MODID)
                .add(Registries.PAINTING_VARIANT, TestPainting::bootstrap)
                .add(Registries.JUKEBOX_SONG, TestSounds::bootstrap)
                .add(Registries.DAMAGE_TYPE, TestDamageTypes::bootstrap)
                .add(Registries.VILLAGER_TRADE, TestVillagerTrades::bootstrap)
                .add(Registries.TRADE_SET, TestTradeSets::bootstrap)
                .add(Registries.FEATURE, TestFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, TestPlacedFeatures::bootstrap)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, TestBiomeModifiers::bootstrap)
                .add(CustomRegistry.DODO_VARIANT_KEY, DodoVariants::bootstrap)
                .add(Registries.LOOT_TABLE, new LootTableProvider(Set.of(), List.of(
                        new LootTableProvider.SubProviderEntry(TestBlockLootProvider::new, LootContextParamSets.BLOCK),
                        new LootTableProvider.SubProviderEntry(TestBlockInteractionLootProvider::new, LootContextParamSets.BLOCK_INTERACT),
                        new LootTableProvider.SubProviderEntry(TestExtraLootProvider::new, LootContextParamSets.ALL_PARAMS),
                        new LootTableProvider.SubProviderEntry(TestEntityLootProvider::new, LootContextParamSets.ENTITY)
                )))
                .add(Registries.ADVANCEMENT, TestAdvancementProvider::new)
                .add(TestRecipeProvider.create());
    }
}
