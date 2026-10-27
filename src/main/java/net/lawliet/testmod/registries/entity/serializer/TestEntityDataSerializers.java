package net.lawliet.testmod.registries.entity.serializer;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.entity.variant.DodoVariant;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class TestEntityDataSerializers {
    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, TestMod.MODID);

    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Holder<DodoVariant>>> DODO_VARIANT = register("dodo_variant", DodoVariant.STREAM_CODEC);


    public static <T> DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Holder<T>>> register(String name, StreamCodec<? super RegistryFriendlyByteBuf, Holder<T>> codec) {
        return ENTITY_DATA_SERIALIZERS.register(name, () -> EntityDataSerializer.forValueType(codec));
    }

    public static void register(IEventBus bus) {
        ENTITY_DATA_SERIALIZERS.register(bus);
    }
}
