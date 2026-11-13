package net.lawliet.testmod.entity.client.warturtle;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class WarTurtleRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState =  new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState sittingTransitionAnimationState = new AnimationState();
    public final AnimationState standingTransitionAnimationState = new AnimationState();

    public boolean hasTier1Chest = false;
    public boolean hasTier2Chest = false;
    public boolean hasTier3Chest = false;

    public ItemStack bodyItem = ItemStack.EMPTY;
    @Nullable
    public DyeColor dyeColor;
}
