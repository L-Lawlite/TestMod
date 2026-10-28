package net.lawliet.testmod.datagen.loot.sub_providers;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestItems;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class TestExtraLootProvider implements LootTableSubProvider {
    private final LootTableSubProvider.Context output;
    private final HolderGetter<Enchantment> enchantments;


    public static final ResourceKey<LootTable> RICE = createGLM("rice");
    public static final ResourceKey<LootTable> METAL_DETECTOR_FOUND = createGLM("metal_detector_found");
    public static final ResourceKey<LootTable> ONION = createGLM("onion");

    public TestExtraLootProvider(LootTableSubProvider.Context context) {
        this.output = context;
        this.enchantments = context.lookup(Registries.ENCHANTMENT);
    }

    private static ResourceKey<LootTable> createGLM(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(TestMod.MODID,"glm/" + path));
    }

    @Override
    public void run() {
        this.output.accept(RICE,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.25f))
                        .add(LootItem.lootTableItem(TestItems.RICE))
                )
        );

        output.accept(METAL_DETECTOR_FOUND,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(TestItems.METAL_DETECTOR))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
        ));

        output.accept(ONION,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments, 0.025F, 0.01F))
                        .add(LootItem.lootTableItem(TestItems.ONION))
        ));
    }
}
