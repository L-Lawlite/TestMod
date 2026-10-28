package net.lawliet.testmod.datagen.loot.sub_providers;

import net.lawliet.testmod.loot.TestLootTables;
import net.lawliet.testmod.block.GojiBerryBlock;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.registries.TestItems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class TestBlockInteractionLootProvider implements LootTableSubProvider {
    protected final LootTableSubProvider.Context output;
    private final HolderGetter<Enchantment> enchantments;
    private final HolderGetter<Block> blocks;


    public TestBlockInteractionLootProvider(LootTableSubProvider.Context context) {
        this.output = context;
        this.enchantments = context.lookup(Registries.ENCHANTMENT);
        this.blocks = output.lookup(Registries.BLOCK);

    }

    @Override
    public void run() {
        makeGojiBushBlockLoot();
    }

    private void makeGojiBushBlockLoot() {
        this.output.accept(TestLootTables.HARVEST_GOJI_BERRY_BUSH,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(TestItems.GOJI_BERRIES)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                .when(MatchBlock.blockMatches(blocks, TestBlocks.GOJI_BERRY_BUSH.get(),
                                        StatePropertiesPredicate.Builder.properties().hasProperty(GojiBerryBlock.AGE, 3)))))
                        .withPool(LootPool.lootPool().add(LootItem.lootTableItem(TestItems.GOJI_BERRIES)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))));
    }
}
