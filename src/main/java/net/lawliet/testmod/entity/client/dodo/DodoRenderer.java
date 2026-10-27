package net.lawliet.testmod.entity.client.dodo;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lawliet.testmod.entity.DodoEntity;
import net.lawliet.testmod.registries.entity.client.TestModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class DodoRenderer extends MobRenderer<DodoEntity, DodoRenderState, DodoModel> {

    public DodoRenderer(EntityRendererProvider.Context context) {
        super(context, new DodoModel(context.bakeLayer(TestModelLayers.DODO)), 0.65f);
    }

    @Override
    @NotNull
    public Identifier getTextureLocation(DodoRenderState state) {
        return state.texture;
    }

    @Override
    @NotNull
    public DodoRenderState createRenderState() {
        return new DodoRenderState();
    }

    @Override
    public void extractRenderState(@NonNull DodoEntity entity, @NonNull DodoRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.state.idleAnimationState);
        state.texture = entity.getVariant().value().adultAssetInfo().texturePath();
    }

    @Override
    public void submit(DodoRenderState state, @NonNull PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState camera) {
        if (state.isBaby) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
