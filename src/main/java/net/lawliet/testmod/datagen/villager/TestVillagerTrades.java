package net.lawliet.testmod.datagen.villager;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.registries.TestItems;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.item.trading.VillagerTrades;

public class TestVillagerTrades {
    public static final ResourceKey<VillagerTrade> FARMER_1_EMERALD_ONION = createVillagerTrade("farmer/1/emerald_onion");
    public static final ResourceKey<VillagerTrade> FARMER_2_GOJI_BERRIES_EMERALD = createVillagerTrade("farmer/2/goji_berries_emerald");
    public static final ResourceKey<VillagerTrade> LIBRARIAN_1_AZURITE_AND_BOOK_ENCHANTED_BOOK = createVillagerTrade("librarian/1/azurite_and_book_enchanted_book");

    public static final ResourceKey<VillagerTrade> TEST_PROFESSION_1_EMERALD_METAL_DETECTOR = createVillagerTrade("test_profession/1/emerald_metal_detector");
    public static final ResourceKey<VillagerTrade> TEST_PROFESSION_1_EMERALD_RAW_AZURITE = createVillagerTrade("test_profession/1/emerald_raw_azurite");

    public static final ResourceKey<VillagerTrade> TEST_PROFESSION_2_EMERALD_DATA_TABLET = createVillagerTrade("test_profession/2/emerald_metal_detector");
    public static final ResourceKey<VillagerTrade> TEST_PROFESSION_2_AZURITE_MAGIC_BLOCK = createVillagerTrade("test_profession/2/azurite_magic_block");


    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        var items = context.lookup(Registries.ITEM);
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        HolderSet<Enchantment> enchantmentsForBooks = enchantments.getOrThrow(EnchantmentTags.TRADEABLE);
        HolderSet<Enchantment> doubleTradePrice = enchantments.getOrThrow(EnchantmentTags.DOUBLE_TRADE_PRICE);

        context.register(FARMER_1_EMERALD_ONION, VillagerTrade.builder(
                new TradeCost(Items.EMERALD, 4),
                new ItemStackTemplate(TestItems.ONION),
                12, 6, 0.05f
        ).build());

        context.register(FARMER_2_GOJI_BERRIES_EMERALD, VillagerTrade.builder(
                new TradeCost(TestItems.GOJI_BERRIES, 40),
                new ItemStackTemplate(Items.EMERALD),
                12, 6, 0.05f
        ).build());
        context.register(LIBRARIAN_1_AZURITE_AND_BOOK_ENCHANTED_BOOK,
                VillagerTrade.builder(
                        new TradeCost(TestItems.AZURITE, 0),
                        new TradeCost(Items.BOOK, 1),
                        new ItemStackTemplate(Items.ENCHANTED_BOOK), 12, 1, 0.2f)
                        .addModifiers(VillagerTrades.enchantedBook(items, enchantmentsForBooks))
                        .doubleTradePriceEnchantments(doubleTradePrice)
                        .build());
        context.register(TEST_PROFESSION_1_EMERALD_METAL_DETECTOR,
                VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 12),
                        new ItemStackTemplate(TestItems.METAL_DETECTOR),
                        8, 12, 0.5f
                ).build());
        context.register(TEST_PROFESSION_1_EMERALD_RAW_AZURITE,
                VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 10),
                        new ItemStackTemplate(TestItems.RAW_AZURITE),
                        8, 12, 0.5f
                ).build());
        context.register(TEST_PROFESSION_2_EMERALD_DATA_TABLET,
                VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 2),
                        new ItemStackTemplate(TestItems.DATA_TABLET),
                        8, 12, 0.5f
                ).build());
        context.register(TEST_PROFESSION_2_AZURITE_MAGIC_BLOCK,
                VillagerTrade.builder(
                        new TradeCost(TestItems.AZURITE, 10),
                        new ItemStackTemplate(TestBlocks.MAGIC_BLOCK.asItem()),
                        8, 12, 0.5f
                ).build());

    }

    private static ResourceKey<VillagerTrade> createVillagerTrade(String key) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(TestMod.MODID, key));
    }
}
