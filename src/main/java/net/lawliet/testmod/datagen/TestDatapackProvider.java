package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.datagen.damagesource.TestDamageTypes;
import net.lawliet.testmod.datagen.painting.TestPainting;
import net.lawliet.testmod.datagen.villager.TestVillagerTrades;
import net.lawliet.testmod.registries.TestSounds;
import net.lawliet.testmod.registries.villager.TestTradeSets;
import net.lawliet.testmod.worldgen.TestBiomeModifiers;
import net.lawliet.testmod.worldgen.TestConfiguredFeatures;
import net.lawliet.testmod.worldgen.TestPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TestDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.PAINTING_VARIANT, TestPainting::bootstrap)
            .add(Registries.JUKEBOX_SONG, TestSounds::bootstrap)
            .add(Registries.DAMAGE_TYPE, TestDamageTypes::bootstrap)
            .add(Registries.VILLAGER_TRADE, TestVillagerTrades::bootstrap)
            .add(Registries.TRADE_SET, TestTradeSets::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, TestConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, TestPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, TestBiomeModifiers::bootstrap)
            ;

    public TestDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TestMod.MODID));
    }


}
