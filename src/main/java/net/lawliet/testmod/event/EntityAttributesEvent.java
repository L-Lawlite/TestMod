package net.lawliet.testmod.event;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.entity.DodoEntity;
import net.lawliet.testmod.entity.WarTurtleEntity;
import net.lawliet.testmod.registries.entity.TestEntityTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = TestMod.MODID)
public class EntityAttributesEvent {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(TestEntityTypes.DODO.get(), DodoEntity.createAttributes().build());
        event.put(TestEntityTypes.WAR_TURTLE.get(), WarTurtleEntity.createAttributes().build());
    }
}
