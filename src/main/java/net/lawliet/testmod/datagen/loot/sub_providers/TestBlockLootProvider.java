package net.lawliet.testmod.datagen.loot.sub_providers;

import net.lawliet.testmod.block.GojiBerryBlock;
import net.lawliet.testmod.block.crop.OnionBlock;
import net.lawliet.testmod.block.crop.RiceCropBlock;
import net.lawliet.testmod.registries.TestBlocks;
import net.lawliet.testmod.registries.TestItems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class TestBlockLootProvider extends BlockLootSubProvider {
    private static HolderLookup.RegistryLookup<Enchantment> enchantments;

    public TestBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
        enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
    }

    @Override
    protected void generate() {
        dropSelf(TestBlocks.AZURITE_BLOCK.get());
        dropSelf(TestBlocks.RAW_AZURITE_BLOCK.get());
        dropSelf(TestBlocks.MAGIC_BLOCK.get());
        dropSelf(TestBlocks.TEST_SHELF.get());
        dropSelf(TestBlocks.AZURITE_STAIRS.get());
        dropSelf(TestBlocks.AZURITE_PRESSURE_PLATE.get());
        dropSelf(TestBlocks.AZURITE_BUTTON.get());
        dropSelf(TestBlocks.AZURITE_FENCE.get());
        dropSelf(TestBlocks.AZURITE_FENCE_GATE.get());
        dropSelf(TestBlocks.AZURITE_WALLS.get());
        dropSelf(TestBlocks.AZURITE_TRAPDOOR.get());
        dropSelf(TestBlocks.AZURITE_LAMP.get());
        dropSelf(TestBlocks.PEDESTAL.get());
        dropSelf(TestBlocks.CRYSTALLIZER.get());

        add(TestBlocks.AZURITE_DOOR.get(), this::createDoorTable);
        add(TestBlocks.AZURITE_SLAB.get(), this::createSlabItemTable);
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

        makeOnionLootTable();
        makeGojiBushLootTable();
        makeRiceCropLootTable();

    }

    private void makeGojiBushLootTable() {
        add(TestBlocks.GOJI_BERRY_BUSH.get(), block -> applyExplosionDecay(
                        block, LootTable.lootTable().withPool(
                                    LootPool.lootPool().when(
                                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(TestBlocks.GOJI_BERRY_BUSH.get())
                                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GojiBerryBlock.AGE, 3))
                                    )
                                    .add(LootItem.lootTableItem(TestItems.GOJI_BERRIES))
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                    .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                ).withPool(
                                    LootPool.lootPool().when(
                                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(TestBlocks.GOJI_BERRY_BUSH.get())
                                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GojiBerryBlock.AGE, 2))
                                    )
                                    .add(LootItem.lootTableItem(TestItems.GOJI_BERRIES))
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                    .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                )
        );
    }

    private void makeRiceCropLootTable() {
        Block rice = TestBlocks.RICE.get();
        LootItemCondition.Builder isCropMaxAge = LootItemBlockStatePropertyCondition.hasBlockStateProperties(rice).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RiceCropBlock.AGE, RiceCropBlock.MAX_AGE));
        this.add(rice, createCropDrops(rice, rice.asItem(), rice.asItem(), isCropMaxAge));
    }

    private void makeOnionLootTable() {
        Block onion = TestBlocks.ONION.get();
        LootItemCondition.Builder isCropMaxAge = LootItemBlockStatePropertyCondition.hasBlockStateProperties(onion).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OnionBlock.AGE, OnionBlock.MAX_AGE));
        this.add(onion, this.applyExplosionDecay(onion,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool().add(LootItem.lootTableItem(onion))
                        )
                        .withPool(LootPool.lootPool().when(isCropMaxAge).add(LootItem.lootTableItem(onion)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(enchantments.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));
    }

    protected LootTable.Builder createMultipleOreDrops(Block block, ItemLike item, NumberProvider count) {
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
