package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.datagen.loot.sub_providers.TestExtraLootProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class TestGLMProvider extends GlobalLootModifierProvider {
    public TestGLMProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, TestMod.MODID);
    }

    @Override
    protected void start() {
        add("rice_to_grass",
                new AddTableLootModifier(new LootItemCondition[] {
                        AnyOfCondition.anyOf(
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS),
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS)
                        ).build()
                }, 1000, TestExtraLootProvider.RICE));
        add("metal_detector_from_jungle_temple",
                new AddTableLootModifier(new LootItemCondition[] {
                       new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/jungle_temple")).build()
                }, 1000, TestExtraLootProvider.METAL_DETECTOR_FOUND));
        add("onion_from_zombie",
                new AddTableLootModifier(new LootItemCondition[] {
                    new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("entities/zombie")).build()
                }, 1000, TestExtraLootProvider.ONION));
    }
}
