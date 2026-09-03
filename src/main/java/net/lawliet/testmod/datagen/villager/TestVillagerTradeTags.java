package net.lawliet.testmod.datagen.villager;

import net.lawliet.testmod.tags.TestTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VillagerTradesTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TestVillagerTradeTags extends VillagerTradesTagsProvider {

    public TestVillagerTradeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        addTradeTag(VillagerTradeTags.FARMER_LEVEL_1, List.of(TestVillagerTrades.FARMER_1_EMERALD_ONION)).build();
        addTradeTag(VillagerTradeTags.FARMER_LEVEL_2, List.of(TestVillagerTrades.FARMER_2_GOJI_BERRIES_EMERALD));
        addTradeTag(VillagerTradeTags.LIBRARIAN_LEVEL_1, List.of(TestVillagerTrades.LIBRARIAN_1_AZURITE_ENCHANTED));
        addTradeTag(TestTags.Trades.TEST_PROFESSION_LEVEL_1, List.of(TestVillagerTrades.TEST_PROFESSION_1_EMERALD_RAW_AZURITE, TestVillagerTrades.TEST_PROFESSION_1_EMERALD_METAL_DETECTOR));
        addTradeTag(TestTags.Trades.TEST_PROFESSION_LEVEL_2, List.of(TestVillagerTrades.TEST_PROFESSION_2_AZURITE_MAGIC_BLOCK, TestVillagerTrades.TEST_PROFESSION_2_EMERALD_DATA_TABLET));

    }

    private TagBuilder addTradeTag(TagKey<VillagerTrade> villagerTrade, List<ResourceKey<VillagerTrade>> trades) {
        TagBuilder builder = getOrCreateRawBuilder(villagerTrade);
        for(ResourceKey<VillagerTrade> resourceKey : trades) {
            builder.add(TagEntry.element(resourceKey.identifier()));
        }
        return builder;
    }

}
