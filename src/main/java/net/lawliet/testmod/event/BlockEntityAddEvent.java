package net.lawliet.testmod.event;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.block.entity.renderer.PedestalRenderer;
import net.lawliet.testmod.registries.TestBlockEntities;
import net.lawliet.testmod.registries.TestBlocks;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

@EventBusSubscriber(modid = TestMod.MODID)
public class BlockEntityAddEvent {

    @SubscribeEvent
    public static void onBlockEntityAdd(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityTypes.SHELF, TestBlocks.TEST_SHELF.get());
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TestBlockEntities.PEDESTAL_BLOCK_ENTITY.get(), PedestalRenderer::new);
    }
}
