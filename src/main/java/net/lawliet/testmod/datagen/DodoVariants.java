package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.entity.variant.DodoVariant;
import net.lawliet.testmod.registries.custom.CustomRegistry;
import net.minecraft.core.ClientAsset;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;

public interface DodoVariants {
    ResourceKey<DodoVariant> BLACK = createKey("black");
    ResourceKey<DodoVariant> BLUE = createKey("blue");

    static void bootstrap(BootstrapContext<DodoVariant> context) {
        registerForAnyCondition(context, BLACK, "entity/dodo/dodo");
        registerForAnyCondition(context, BLUE, "entity/dodo/dodo_blue");
    }

    private static void registerForAnyCondition(BootstrapContext<DodoVariant> context, ResourceKey<DodoVariant> name, String adultTexture) {
        register(context, name, adultTexture, SpawnPrioritySelectors.fallback(0));
    }

    private static void register(BootstrapContext<DodoVariant> context, ResourceKey<DodoVariant> name, String adultTexture, SpawnPrioritySelectors spawnConditions) {
        context.register(name, new DodoVariant(new ClientAsset.ResourceTexture(Identifier.fromNamespaceAndPath(TestMod.MODID, adultTexture)), spawnConditions));
    }

    private static ResourceKey<DodoVariant> createKey(String name) {
        return ResourceKey.create(CustomRegistry.DODO_VARIANT_KEY, Identifier.fromNamespaceAndPath(TestMod.MODID, name));
    }

}
