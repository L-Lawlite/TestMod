package net.lawliet.testmod.worldgen;

import net.lawliet.testmod.TestMod;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * Neoforge class to Place feature in specific biome
 */
public class TestBiomeModifiers {

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

    }

    public static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Identifier.fromNamespaceAndPath(TestMod.MODID, name));
    }
}
