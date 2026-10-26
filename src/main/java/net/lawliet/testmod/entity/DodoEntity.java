package net.lawliet.testmod.entity;

import net.lawliet.testmod.entity.client.dodo.DodoRenderState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class DodoEntity extends PathfinderMob {
    public final DodoRenderState state = new DodoRenderState();
    private int idleAnimationTimeout = 0;

    public DodoEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }


    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0 , new FloatGoal(this));
        goalSelector.addGoal(1, new PanicGoal(this, 2));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 7));
        goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    private void setupAnimationState() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.state.idleAnimationState.start(this.tickCount);
        } else  {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationState();
        }
    }


    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.TEMPT_RANGE, 16)
                .add(Attributes.FOLLOW_RANGE, 24);
    }
}
