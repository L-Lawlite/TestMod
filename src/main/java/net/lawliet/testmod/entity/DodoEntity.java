package net.lawliet.testmod.entity;

import net.lawliet.testmod.datagen.DodoVariants;
import net.lawliet.testmod.entity.client.dodo.DodoRenderState;
import net.lawliet.testmod.entity.variant.DodoVariant;
import net.lawliet.testmod.registries.TestItems;
import net.lawliet.testmod.registries.custom.CustomRegistry;
import net.lawliet.testmod.registries.entity.TestEntityTypes;
import net.lawliet.testmod.registries.entity.serializer.TestEntityDataSerializers;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.VariantUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class DodoEntity extends Animal {
    private static final EntityDataAccessor<Holder<DodoVariant>> DATA_VARIANT_ID = SynchedEntityData.defineId(DodoEntity.class, TestEntityDataSerializers.DODO_VARIANT.get());
    private static final ResourceKey<DodoVariant> DEFAULT_VARIANT = DodoVariants.BLACK;
    public final DodoRenderState state = new DodoRenderState();
    private int idleAnimationTimeout = 0;

    public DodoEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }


    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0 , new FloatGoal(this));
        goalSelector.addGoal(1, new PanicGoal(this, 2));
        goalSelector.addGoal(2, new BreedGoal(this, 1.25D));
        goalSelector.addGoal(3, new TemptGoal(this, 1.25d, stack -> stack.is(TestItems.GOJI_BERRIES), false));
        goalSelector.addGoal(4, new FollowParentGoal(this, 1.25d));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 7));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    /// Animations
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

    /// Variants
    @Override
    protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_VARIANT_ID, VariantUtils.getDefaultOrAny(this.registryAccess(), DEFAULT_VARIANT));
    }


    public Holder<DodoVariant> getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }


    private void setVariant(Holder<DodoVariant> variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }



    @Override
    protected void addAdditionalSaveData(@NonNull ValueOutput output) {
        super.addAdditionalSaveData(output);
        VariantUtils.writeVariant(output, this.getVariant());
    }


    @Override
    protected void readAdditionalSaveData(@NonNull ValueInput input) {
        super.readAdditionalSaveData(input);
        VariantUtils.readVariant(input, CustomRegistry.DODO_VARIANT_KEY).ifPresent(this::setVariant);
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NonNull ServerLevelAccessor level, @NonNull DifficultyInstance difficulty, @NonNull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        VariantUtils.selectVariantToSpawn(SpawnContext.create(level, this.blockPosition()), CustomRegistry.DODO_VARIANT_KEY).ifPresent(this::setVariant);
        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    /// Sound
    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.NAUTILUS_DEATH;
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.PARROT_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(@NonNull DamageSource source) {
        return SoundEvents.PARROT_HURT;
    }

    /// Breeding
    @Override
    public boolean isFood(@NonNull ItemStack itemStack) {
        return itemStack.is(TestItems.GOJI_BERRIES);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(@NonNull ServerLevel serverLevel, @NonNull AgeableMob partner) {
        DodoEntity baby = TestEntityTypes.DODO.get().create(serverLevel, EntitySpawnReason.BREEDING);
        if (baby != null && partner instanceof DodoEntity partnerDodo) {
            baby.setVariant(this.random.nextBoolean() ? this.getVariant() : partnerDodo.getVariant());
        }

        return baby;
    }

}
