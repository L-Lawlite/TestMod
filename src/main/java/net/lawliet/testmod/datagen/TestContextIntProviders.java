package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.providers.number.ints.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class TestContextIntProviders {
    public static final ResourceKey<ContextIntProvider> COOKING_TIME_END_FIRE_STARTER = createKey("cooking/time_end_fire_starter");

    public static void bootstrap(BootstrapContext<ContextIntProvider> context) {
        int baseFuelTime = 200;
        var predicates = context.lookup(Registries.PREDICATE);
        Holder.Reference<ContextIntProvider> normalBurnTime = context.register(ContextIntProviders.COOKING_NORMAL_BURN_TIME_REDUCTION_FACTOR, new ConstantValue(1));
        Holder.Reference<ContextIntProvider> fastBurnTime = context.register(ContextIntProviders.COOKING_FAST_BURN_TIME_REDUCTION_FACTOR, new ConstantValue(2));
        context.register(COOKING_TIME_END_FIRE_STARTER, ContextIntProviders.cooking(predicates, normalBurnTime, fastBurnTime, 24 * baseFuelTime));

    }

    private static ResourceKey<ContextIntProvider> createKey(String name) {
        return ResourceKey.create(Registries.CONTEXT_INT_PROVIDER, Identifier.fromNamespaceAndPath(TestMod.MODID, name));
    }
}
