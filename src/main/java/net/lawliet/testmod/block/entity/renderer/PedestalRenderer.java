package net.lawliet.testmod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.lawliet.testmod.block.entity.PedestalBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class PedestalRenderer implements BlockEntityRenderer<PedestalBlockEntity, PedestalRendererState> {
    private final ItemModelResolver itemModelResolver;

    public PedestalRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public PedestalRendererState createRenderState() {
        return new PedestalRendererState();
    }

    @Override
    public void extractRenderState(PedestalBlockEntity blockEntity, PedestalRendererState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.level = blockEntity.getLevel();
        assert blockEntity.getLevel() != null;
        state.rotation = (blockEntity.getLevel().getGameTime() + partialTicks * 0.5f) % 360f;
        itemModelResolver.updateForTopItem(state.itemStackRenderState, blockEntity.getItem(), ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
    }

    @Override
    public void submit(PedestalRendererState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        poseStack.translate(0.5D, 1.15D, 0.5D);
        poseStack.scale(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.rotation));

        state.itemStackRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

        poseStack.popPose();
    }


}
