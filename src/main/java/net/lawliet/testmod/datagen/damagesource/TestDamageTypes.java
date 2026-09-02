package net.lawliet.testmod.datagen.damagesource;

import net.lawliet.testmod.TestMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class TestDamageTypes {
    public static final ResourceKey<DamageType> STINKY = createDamageType("stinky");

    private static ResourceKey<DamageType> createDamageType(String key) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(TestMod.MODID, key));
    }

    /** For datagen **/
    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(STINKY, new DamageType("stinky", 0.1f, DamageEffects.HURT));
    }

    public static DamageSource create(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key));
    }
}
