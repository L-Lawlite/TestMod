package net.lawliet.testmod.entity.client.warturtle;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class WarTurtleRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState =  new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState sittingTransitionAnimationState = new AnimationState();
    public final AnimationState standingTransitionAnimationState = new AnimationState();
}
