package net.lawliet.testmod.datagen.loot.sub_providers;

import net.lawliet.testmod.registries.entity.TestEntityTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.stream.Stream;

public class TestEntityLootProvider extends EntityLootSubProvider {
    private final HolderGetter<Enchantment> enchantments;

    public TestEntityLootProvider(LootTableSubProvider.Context context) {
        super(FeatureFlags.REGISTRY.allFlags(), context);
        this.enchantments = output.lookup(Registries.ENCHANTMENT);
    }

    @Override
    public void generate() {
        add(TestEntityTypes.DODO.get(),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Items.FEATHER)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(1.0f, 3.0f)))
                        ).when(LootItemKilledByPlayerCondition.killedByPlayer())
                )
        );

        noLootTable(TestEntityTypes.WAR_TURTLE.get());
    }

    private void noLootTable(EntityType<?> entityType) {
        add(entityType, LootTable.lootTable());
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return TestEntityTypes.ENTITY_TYPES.getEntries().stream().map(Holder::value);
    }
}
