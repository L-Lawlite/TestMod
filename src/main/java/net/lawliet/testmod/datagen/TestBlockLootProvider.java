package net.lawliet.testmod.datagen;

import net.lawliet.testmod.block.TestBlocks;
import net.lawliet.testmod.item.TestItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class TestBlockLootProvider extends BlockLootSubProvider {
    public TestBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(TestBlocks.AZURITE_BLOCK.get());
        dropSelf(TestBlocks.RAW_AZURITE_BLOCK.get());
        dropSelf(TestBlocks.MAGIC_BLOCK.get());
        add(
                TestBlocks.AZURITE_ORE.get(),
                createOreDrop(TestBlocks.AZURITE_ORE.get(), TestItems.RAW_AZURITE.get())
        );
        add(
                TestBlocks.AZURITE_DEEPSLATE_ORE.get(),
                createOreDrop(TestBlocks.AZURITE_DEEPSLATE_ORE.get(), TestItems.RAW_AZURITE.get())
        );
        add(
                TestBlocks.AZURITE_NETHER_ORE.get(),
                createOreDrop(TestBlocks.AZURITE_NETHER_ORE.get(), TestItems.RAW_AZURITE.get())
        );
        add(
                TestBlocks.AZURITE_END_ORE.get(),
                createMultipleOreDrops(TestBlocks.AZURITE_END_ORE.get(), TestItems.RAW_AZURITE.get(), 2, 3)
        );


    }

    protected LootTable.Builder createMultipleOreDrops(Block block, ItemLike item, NumberProvider count) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(count)) // count is like UniformGenerator.between(2.0F, 5.0F)
                                .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    protected LootTable.Builder createMultipleOreDrops(Block block, ItemLike item, float minDrop, float maxDrop) {
        return createMultipleOreDrops(block, item, UniformGenerator.between(minDrop, maxDrop));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return TestBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
