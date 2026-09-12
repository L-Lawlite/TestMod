package net.lawliet.testmod.worldgen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.worldgen.features.OreFeatureSets;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * Neoforge class to Place feature in specific biome
 */
public class TestBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_OVERWORLD_AZURITE_ORE = registerKey("add_overworld_azurite_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_AZURITE_ORE = registerKey("add_nether_azurite_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_AZURITE_ORE = registerKey("add_end_azurite_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_OVERWORLD_AZURITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(OreFeatureSets.OVERWORLD_AZURITE_ORE.placedFeature())),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        /* For individual biomes
            context.register(ADD_OVERWORLD_AZURITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                    HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.DESERT)),
                    HolderSet.direct(placedFeatures.getOrThrow(OreFeatureSets.OVERWORLD_AZURITE_ORE.placedFeature())),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ));
         */

        context.register(ADD_NETHER_AZURITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(OreFeatureSets.NETHER_AZURITE_ORE.placedFeature())),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_END_AZURITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(OreFeatureSets.END_AZURITE_ORE.placedFeature())),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }

    public static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Identifier.fromNamespaceAndPath(TestMod.MODID, name));
    }
}
