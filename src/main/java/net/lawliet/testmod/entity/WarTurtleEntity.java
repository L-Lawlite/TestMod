package net.lawliet.testmod.entity;

import net.lawliet.testmod.entity.client.warturtle.WarTurtleRenderState;
import net.lawliet.testmod.registries.TestItems;
import net.lawliet.testmod.registries.entity.TestEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class WarTurtleEntity extends TamableAnimal {
    public final WarTurtleRenderState state = new WarTurtleRenderState();
    private int idleAnimationTimeout = 0;


    private static final String LAST_POSE_TICK_KEY = "LastPoseTick";
    private static final EntityDataAccessor<Long> LAST_POSE_TICK = SynchedEntityData.defineId(WarTurtleEntity.class, EntityDataSerializers.LONG);

    public WarTurtleEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.15d));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25d, Ingredient.of(TestItems.ONION), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1d));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.1d, 10f, 3f));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0d));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class,  4.0f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20d)
                .add(Attributes.MOVEMENT_SPEED, 0.25d)
                .add(Attributes.TEMPT_RANGE, 16d)
                .add(Attributes.FOLLOW_RANGE, 24d);

    }

    /// Animations
    private void setupAnimationState() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 20;
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.shouldUpdateSittingAnimations()) {
            this.state.standingTransitionAnimationState.stop();
            if (this.shouldPlaySittingTransitionAnimations()) {
                this.state.sittingTransitionAnimationState.startIfStopped(this.tickCount);
                this.state.sittingAnimationState.stop();
            } else {
                this.state.sittingTransitionAnimationState.stop();
                this.state.sittingAnimationState.startIfStopped(this.tickCount);
            }
        } else {
            this.state.sittingTransitionAnimationState.stop();
            this.state.sittingAnimationState.stop();
            this.state.standingTransitionAnimationState.animateWhen(this.isChangingPose() && this.getLastPosTickDelta() >= 0L, this.tickCount);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationState();
        }
    }

    private boolean shouldUpdateSittingAnimations() {
        return this.getLastPosTickDelta() < 0L != this.isSitting();
    }

    private boolean shouldPlaySittingTransitionAnimations() {
        return this.isSitting() && this.getLastPosTickDelta() < 40L && this.getLastPosTickDelta() >= 0L;

    }

    private long getLastPosTickDelta() {
        return this.level().getGameTime() - Math.abs(this.entityData.get(LAST_POSE_TICK));
    }

    private boolean isChangingPose() {
        long lastPosTickDelta = this.getLastPosTickDelta();
        return lastPosTickDelta < (this.isSitting() ? 40L: 52L);
    }

    /// Saving Data
    public void setLastPoseTick(long lastPosTick) {
        this.entityData.set(LAST_POSE_TICK, lastPosTick);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(LAST_POSE_TICK, 0L);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putLong(LAST_POSE_TICK_KEY, this.entityData.get(LAST_POSE_TICK));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        long poseTick = input.getLongOr(LAST_POSE_TICK_KEY, 0L);
        if (poseTick > 0L) {
            this.setPose(Pose.SITTING);
        }
        this.setLastPoseTick(poseTick);
    }

    ///Sitting
    private void toggleSitting() {
        if (this.isSitting()) {
            startStanding();
        } else {
            startSitting();
        }
    }

    private void startStanding() {
        if (!this.isSitting()) {
            return;
        }
        this.playSound(SoundEvents.CAMEL_STAND);
        this.setPose(Pose.STANDING);
        this.gameEvent(GameEvent.ENTITY_ACTION);
        this.setLastPoseTick(this.level().getGameTime());

        setInSittingPose(false);
        setOrderedToSit(false);

    }

    private void startSitting() {
        if (this.isSitting()) {
            return;
        }
        this.playSound(SoundEvents.CAMEL_SIT);
        this.setPose(Pose.SITTING);
        this.gameEvent(GameEvent.ENTITY_ACTION);
        this.setLastPoseTick(-this.level().getGameTime());

        setInSittingPose(true);
        setOrderedToSit(true);
    }


    private boolean isSitting() {
        return this.entityData.get(LAST_POSE_TICK) < 0L;
    }

    ///Breedable
    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(TestItems.ONION);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return TestEntityTypes.WAR_TURTLE.get().create(level, EntitySpawnReason.BREEDING);
    }

    /// Right Clicking
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        Item itemForTaming = Items.APPLE;

        if (itemStack.is(itemForTaming) && !isTame()) {
            if (!this.level().isClientSide()) {
                super.tame(player);
                this.navigation.recomputePath();
                this.setTarget(null);
                this.level().broadcastEntityEvent(this, EntityEvent.TAMING_SUCCEEDED);
                toggleSitting();
            }
            return this.feedFood(player, itemStack);
        }

        if (this.isTame() && hand == InteractionHand.MAIN_HAND && !isFood(itemStack))  {
            toggleSitting();
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    public InteractionResult feedFood(Player player, ItemStack itemStack) {
        boolean ateFood = this.handleEating(player, itemStack);
        if (ateFood) {
            itemStack.consume(1, player);
        }

        return !ateFood && !this.level().isClientSide() ? InteractionResult.PASS : InteractionResult.SUCCESS_SERVER;
    }

    protected boolean handleEating(Player player, ItemStack itemStack) {
        boolean itemUsed = false;
        float heal = 0.0F;
        int ageUp = 0;
        if (itemStack.is(Items.APPLE)) {
            heal = 3.0F;
            ageUp = 60;
        }

        if (this.getHealth() < this.getMaxHealth() && heal > 0.0F) {
            this.heal(heal);
            itemUsed = true;
        }

        if (this.isBaby() && ageUp > 0 && !this.isAgeLocked()) {
            this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1), this.getRandomY() + 0.5, this.getRandomZ(1), 0, 0, 0);
            if(!this.level().isClientSide()) {
                this.ageUp(ageUp);
                itemUsed = true;
            }
        }

       if (itemUsed) {
           this.eating();
           this.gameEvent(GameEvent.EAT);
       }

       return itemUsed;
    }

    private void eating() {
        if(!this.isSilent()) {
            SoundEvent eatingSound = this.getEatingSound();
            if (eatingSound != null) {
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(), eatingSound, this.getSoundSource(), 1, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }
        }
    }

    @Override
    public void travel(Vec3 input) {
        if (this.refuseToMove() && this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0, 1, 0));
            input = input.multiply(0, 1, 0);
        }
        super.travel(input);
    }

    private boolean refuseToMove() {
        return this.isSitting() || this.isChangingPose();
    }

    @Nullable
    protected SoundEvent getEatingSound() {
        return null;
    }

}
