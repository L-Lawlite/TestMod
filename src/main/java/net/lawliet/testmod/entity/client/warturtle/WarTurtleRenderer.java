package net.lawliet.testmod.entity.client.warturtle;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.entity.WarTurtleEntity;
import net.lawliet.testmod.registries.entity.client.TestModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

public class WarTurtleRenderer extends MobRenderer<WarTurtleEntity, WarTurtleRenderState, WarTurtleModel> {
    public WarTurtleRenderer(EntityRendererProvider.Context context) {
        super(context, new WarTurtleModel(context.bakeLayer(TestModelLayers.WAR_TURTLE)), 0.8f);
    }

    @Override
    public Identifier getTextureLocation(WarTurtleRenderState state) {
        return Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/entity/war_turtle/war_turtle.png");
    }

    @Override
    public WarTurtleRenderState createRenderState() {
        return new WarTurtleRenderState();
    }

    @Override
    public void extractRenderState(WarTurtleEntity entity, WarTurtleRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.state.idleAnimationState);
        state.standingTransitionAnimationState.copyFrom(entity.state.standingTransitionAnimationState);
        state.sittingAnimationState.copyFrom(entity.state.sittingAnimationState);
        state.sittingTransitionAnimationState.copyFrom(entity.state.sittingTransitionAnimationState);
    }

    @Override
    public void submit(WarTurtleRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (state.isBaby) {
            poseStack.scale(0.35f, 0.35f, 0.35f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
