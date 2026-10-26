package net.lawliet.testmod.event;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.entity.client.dodo.DodoModel;
import net.lawliet.testmod.entity.client.dodo.DodoRenderer;
import net.lawliet.testmod.registries.entity.client.TestModelLayers;
import net.lawliet.testmod.registries.entity.TestEntityTypes;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = TestMod.MODID)
public class EntityRendererEvent {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TestEntityTypes.DRIFTWOOD_BOAT.get(), context -> new BoatRenderer(context, TestModelLayers.DRIFTWOOD_BOAT));
        event.registerEntityRenderer(TestEntityTypes.DODO.get(), DodoRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TestModelLayers.DRIFTWOOD_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(TestModelLayers.DODO, DodoModel::createBodyLayer);
    }


}
