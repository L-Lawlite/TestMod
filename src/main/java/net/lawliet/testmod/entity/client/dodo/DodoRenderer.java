package net.lawliet.testmod.entity.client.dodo;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.entity.DodoEntity;
import net.lawliet.testmod.registries.entity.client.TestModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class DodoRenderer extends MobRenderer<DodoEntity, DodoRenderState, DodoModel> {
    public DodoRenderer(EntityRendererProvider.Context context) {
        super(context, new DodoModel(context.bakeLayer(TestModelLayers.DODO)), 0.65f);
    }

    @Override
    @NotNull
    public Identifier getTextureLocation(DodoRenderState state) {
        return Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/entity/dodo/dodo.png");
    }

    @Override
    @NotNull
    public DodoRenderState createRenderState() {
        return new DodoRenderState();
    }

    @Override
    public void extractRenderState(DodoEntity entity, DodoRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.state.idleAnimationState);
    }
}
