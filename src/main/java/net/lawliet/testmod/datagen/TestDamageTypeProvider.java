package net.lawliet.testmod.datagen;

import net.lawliet.testmod.damagesource.TestDamageTypes;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageType;

public class TestDamageTypeProvider {

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(TestDamageTypes.STINKY, new DamageType("stinky", 0.1f, DamageEffects.HURT));
    }
}
