package net.lawliet.testmod.block.entity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.level.Level;

public class PedestalRendererState extends BlockEntityRenderState {
    public Level level;
    public float rotation;

    public final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
}
