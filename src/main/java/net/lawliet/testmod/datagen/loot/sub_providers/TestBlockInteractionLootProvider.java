package net.lawliet.testmod.datagen.loot.sub_providers;

import net.lawliet.testmod.loot.TestLootTables;
import net.lawliet.testmod.block.GojiBerryBlock;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.registries.TestItems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class TestBlockInteractionLootProvider implements LootTableSubProvider {
    protected final HolderLookup.Provider registry;
    private static HolderLookup.RegistryLookup<Enchantment> enchantments;


    public TestBlockInteractionLootProvider(HolderLookup.Provider registries) {
        this.registry = registries;
        enchantments = registries.lookupOrThrow(Registries.ENCHANTMENT);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        makeBushBlockLoot(output);

    }

    private static void makeBushBlockLoot(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(TestLootTables.HARVEST_SWEET_BERRY_BUSH,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(TestItems.GOJI_BERRIES)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TestBlocks.GOJI_BERRY_BUSH.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GojiBerryBlock.AGE, 3)))))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(TestItems.GOJI_BERRIES)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))));
    }
}
