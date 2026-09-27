package net.lawliet.testmod.registries;

import net.lawliet.testmod.TestMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TestEntityTypes {
    public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(TestMod.MODID);

    public static final DeferredHolder<EntityType<?>,EntityType<Boat>> DRIFTWOOD_BOAT = registerEntityType("driftwood_boat",
            EntityType.Builder.of(boatFactory(TestItems.DRIFTWOOD_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
    );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> registerEntityType(String name, EntityType.Builder<T> sup) {
        return ENTITY_TYPES.register(name,() -> sup.build(ResourceKey.create(Registries.ENTITY_TYPE, TestMod.createIdentifier(name))));

    }

    public static EntityType.EntityFactory<Boat> boatFactory(Supplier<Item> boatItem) {
        return (entityType, level) -> new Boat(entityType, level, boatItem);
    }
}
