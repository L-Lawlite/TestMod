package net.lawliet.testmod.datagen;

import net.lawliet.testmod.registries.TestBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Flammable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Transformable;

import java.util.concurrent.CompletableFuture;

public class TestDataMapProvider extends DataMapProvider {

    public TestDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        builder(NeoForgeDataMaps.FLAMMABLE)
                .add(TestBlocks.DRIFTWOOD_LOG, new Flammable(FireBlock.IGNITE_HARD, FireBlock.BURN_HARD), false)
                .add(TestBlocks.DRIFTWOOD_WOOD, new Flammable(FireBlock.IGNITE_HARD, FireBlock.BURN_HARD), false)
                .add(TestBlocks.STRIPPED_DRIFTWOOD_LOG, new Flammable(FireBlock.IGNITE_HARD, FireBlock.BURN_HARD), false)
                .add(TestBlocks.STRIPPED_DRIFTWOOD_WOOD, new Flammable(FireBlock.IGNITE_HARD, FireBlock.BURN_HARD), false)
                .add(TestBlocks.DRIFTWOOD_PLANKS, new Flammable(FireBlock.IGNITE_HARD, FireBlock.BURN_MEDIUM), false)
                .add(TestBlocks.DRIFTWOOD_SLAB, new Flammable(FireBlock.IGNITE_HARD, FireBlock.BURN_MEDIUM), false)
                .add(TestBlocks.DRIFTWOOD_FENCE_GATE, new Flammable(FireBlock.IGNITE_HARD, FireBlock.BURN_MEDIUM), false)
                .add(TestBlocks.DRIFTWOOD_FENCE, new Flammable(FireBlock.IGNITE_HARD, FireBlock.BURN_MEDIUM), false)
                .add(TestBlocks.DRIFTWOOD_STAIRS, new Flammable(FireBlock.IGNITE_HARD, FireBlock.BURN_MEDIUM), false)
                .add(TestBlocks.DRIFTWOOD_LEAVES, new Flammable(FireBlock.IGNITE_EASY, FireBlock.BURN_EASY), false)
                .add(TestBlocks.DRIFTWOOD_SHELF, new Flammable(FireBlock.IGNITE_EASY, FireBlock.BURN_MEDIUM), false)
                .add(TestBlocks.TEST_SHELF, new Flammable(FireBlock.IGNITE_EASY, FireBlock.BURN_MEDIUM), false)
                ;
        builder(NeoForgeDataMaps.TRANSFORMABLES)
                .add(TestBlocks.DRIFTWOOD_LOG.get().builtInRegistryHolder() ,Transformable.stripping(TestBlocks.DRIFTWOOD_LOG.get(), TestBlocks.STRIPPED_DRIFTWOOD_LOG.get()), false)
                .add(TestBlocks.DRIFTWOOD_WOOD.get().builtInRegistryHolder() ,Transformable.stripping(TestBlocks.DRIFTWOOD_WOOD.get(), TestBlocks.STRIPPED_DRIFTWOOD_WOOD.get()), false)
                ;
    }
}
