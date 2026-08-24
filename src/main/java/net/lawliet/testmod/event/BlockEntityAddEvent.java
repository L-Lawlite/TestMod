package net.lawliet.testmod.event;

import net.lawliet.testmod.registries.TestBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

@EventBusSubscriber
public class BlockEntityAddEvent {

    @SubscribeEvent
    public static void onBlockEntityAdd(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SHELF, TestBlocks.TEST_SHELF.get());
    }
}
