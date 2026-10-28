package net.lawliet.testmod.registries.custom;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.entity.variant.DodoVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewDatapackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;


@EventBusSubscriber(modid = TestMod.MODID)
public class CustomRegistry {
    public static final ResourceKey<Registry<DodoVariant>> DODO_VARIANT_KEY = createRegistryKey( "dodo_variant");

    public static <T> ResourceKey<Registry<T>> createRegistryKey(String name) {
        return ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(TestMod.MODID, name));
    }

    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event) {
    }

    @SubscribeEvent
    public static void registerDatapackRegistries(NewDatapackRegistryEvent event) {
        event.worldRegistry(DODO_VARIANT_KEY, DodoVariant.DIRECT_CODEC, DodoVariant.NETWORK_CODEC);
    }

}
