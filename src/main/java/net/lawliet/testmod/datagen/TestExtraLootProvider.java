package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class TestExtraLootProvider implements LootTableSubProvider {
    private final HolderLookup.Provider provider;

    public static final ResourceKey<LootTable> RICE = createGLM("rice");
    public static final ResourceKey<LootTable> METAL_DETECTOR_FOUND = createGLM("metal_detector_found");
    public static final ResourceKey<LootTable> ONION = createGLM("onion");

    public TestExtraLootProvider(HolderLookup.Provider provider) {
        this.provider = provider;
    }

    private static ResourceKey<LootTable> createGLM(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(TestMod.MODID,"glm/" + path));
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(RICE,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.25f))
                        .add(LootItem.lootTableItem(TestItems.RICE))
                )
        );

        output.accept(METAL_DETECTOR_FOUND,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(TestItems.METAL_DETECTOR))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)))
        ));

        output.accept(ONION,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(provider, 0.025F, 0.01F))
                        .add(LootItem.lootTableItem(TestItems.ONION))
        ));
    }
}
