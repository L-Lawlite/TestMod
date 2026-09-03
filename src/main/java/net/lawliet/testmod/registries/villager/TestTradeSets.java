package net.lawliet.testmod.registries.villager;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.tags.TestTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.TradeSets;
import net.minecraft.world.item.trading.VillagerTrade;

public class TestTradeSets {
    public static final ResourceKey<TradeSet> TEST_PROFESSION_LEVEL_1 = create(TestTags.Trades.TEST_PROFESSION_LEVEL_1);
    public static final ResourceKey<TradeSet> TEST_PROFESSION_LEVEL_2 = create(TestTags.Trades.TEST_PROFESSION_LEVEL_2);

    public static void bootstrap(BootstrapContext<TradeSet> context) {
        TradeSets.register(context, TEST_PROFESSION_LEVEL_1, TestTags.Trades.TEST_PROFESSION_LEVEL_1);
        TradeSets.register(context, TEST_PROFESSION_LEVEL_2, TestTags.Trades.TEST_PROFESSION_LEVEL_2);
    }

    private static ResourceKey<TradeSet> create(final String id) {
        return ResourceKey.create(Registries.TRADE_SET, Identifier.fromNamespaceAndPath(TestMod.MODID, id));
    }

    private static ResourceKey<TradeSet> create(TagKey<VillagerTrade> tradeTag) {
        return create(tradeTag.location().getPath());
    }


}
