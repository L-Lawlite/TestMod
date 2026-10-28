package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.datagen.loot.sub_providers.TestExtraLootProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class TestGLMProvider extends GlobalLootModifierProvider {
    public TestGLMProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, TestMod.MODID);
    }

    @Override
    protected void start() {
        var blocks = registries.lookupOrThrow(Registries.BLOCK);
        add("rice_to_grass",
                new AddTableLootModifier(
                        Optional.of(Holder.direct(
                                        AnyOfCondition.anyOf(
                                                MatchBlock.blockMatches(blocks, Blocks.SHORT_GRASS),
                                                MatchBlock.blockMatches(blocks, Blocks.TALL_GRASS)
                                        ).build()
                        )), 10, TestExtraLootProvider.RICE));
        add("metal_detector_from_jungle_temple",
                new AddTableLootModifier(
                    Optional.of(Holder.direct(
                           new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("chests/jungle_temple")).build()))
                , 10, TestExtraLootProvider.METAL_DETECTOR_FOUND));
        add("onion_from_zombie",
                new AddTableLootModifier(
                        Optional.of(Holder.direct(
                                new LootTableIdCondition.Builder(Identifier.withDefaultNamespace("entities/zombie")).build()))
                , 10, TestExtraLootProvider.ONION));
    }
}
