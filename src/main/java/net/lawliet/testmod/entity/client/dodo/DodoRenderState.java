package net.lawliet.testmod.entity.client.dodo;

import net.lawliet.testmod.TestMod;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.AnimationState;

public class DodoRenderState extends LivingEntityRenderState {
    private static final Identifier DEFAULT_TEXTURE = Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/entity/dodo/dodo.png");
    public final AnimationState idleAnimationState = new AnimationState();
    public Identifier texture;

    public DodoRenderState() {
        this.texture = DEFAULT_TEXTURE;
    }
}
