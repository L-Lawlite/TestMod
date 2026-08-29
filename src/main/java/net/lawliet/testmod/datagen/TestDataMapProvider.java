package net.lawliet.testmod.datagen;

import net.lawliet.testmod.registries.TestItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class TestDataMapProvider extends DataMapProvider {
    private static final int FUEL_BASE = 200;

    public TestDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(TestItems.END_FIRE_STARTER.getId(), new FurnaceFuel(FUEL_BASE * 24), false);

        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(TestItems.ONION.getId(), new Compostable(0.65f), false);
    }
}
