package net.lawliet.testmod.entity.goal;

import net.lawliet.testmod.entity.BabyManEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class BabyManSlamAttackGoal extends Goal {

    //TICK RELATED FIELD
    /// Tick he hits the ground
    private static final int SLAM_TICK = 13;
    /// Tick he is no longer grounded
    private static final int GROUNDED_END_TICK = 49;
    /// Total animation time
    public static final int ANIMATION_TICKS = 3 * 20;

    private static final double ATTACK_RANGE = 5.0d;

    private static final int PARTICLE_COUNT = 48;

    /// Ticks player have to parry
    private static final int PARRY_WINDOW = 6;
    private static final double PARRY_FACING = 0.5;
    private static final float LATE_BLOCK_DISABLE_SECONDS = 1.5f;

    private static final int DEFAULT_COOLDOWN = 60;

    private final BabyManEntity entity;
    private LivingEntity target;
    private int attackTick;
    private int cooldown;
    private boolean hasSlammed;
    private boolean frozen;

    public BabyManSlamAttackGoal(BabyManEntity entity) {
        this.entity = entity;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (this.cooldown > 0) {
            this.cooldown--;
            return false;
        }

        LivingEntity currentTarget = this.entity.getTarget();
        if (currentTarget == null || !currentTarget.isAlive() || !this.entity.onGround()) {
            return false;
        }

        if (this.entity.distanceToSqr(currentTarget) > ATTACK_RANGE * ATTACK_RANGE) {
            return false;
        }

        this.target = currentTarget;
        return this.entity.hasLineOfSight(currentTarget);
    }

    @Override
    public boolean canContinueToUse() {
        return this.attackTick < ANIMATION_TICKS;
    }

    @Override
    public boolean isInterruptable() {
        return false;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    private double attackDamage() {
        return this.entity.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public void start() {
        this.attackTick = 0;
        this.hasSlammed = false;
        this.frozen = false;

        this.entity.getNavigation().stop();
        this.entity.setSlamming(true);
        this.entity.setSlamTick(-1);
        this.entity.triggerAnim(BabyManEntity.ATTACK_CONTROLLER, BabyManEntity.SLAM_TRIGGER);

        if (this.target != null) {
            this.entity.getLookControl().setLookAt(this.target, 30f, 30f);
        }

        Vec3 lunge = Vec3.ZERO;
        if(target != null) {
            lunge = new Vec3(this.target.getX() - this.entity.getX(), 0, this.target.getZ() - this.entity.getZ()).normalize().scale(0.18d);
        }

        this.entity.setDeltaMovement(lunge.x, 0.55, lunge.z);
        this.entity.playSound(SoundEvents.RAVAGER_ROAR, 1f, 1.4f);
    }

    @Override
    public void tick() {
        this.attackTick++;
        this.entity.getNavigation().stop();

        if (this.target != null && this.attackTick <= SLAM_TICK) {
            this.entity.getLookControl().setLookAt(this.target, 30f, 30f);
        }

        if (this.attackTick == SLAM_TICK - 5) {
            Vec3 deltaMovement = this.entity.getDeltaMovement().scale(0.4);
            this.entity.setDeltaMovement(deltaMovement.x, -1.4, deltaMovement.z);
        }

        if (!this.hasSlammed && this.attackTick >= SLAM_TICK &&
            (this.entity.onGround() || this.attackTick >= SLAM_TICK + 4)
        ) {
            this.hasSlammed = true;
            this.entity.setDeltaMovement(0, this.entity.getDeltaMovement().y, 0);
            this.entity.resetFallDistance();

            if(this.entity.level() instanceof ServerLevel serverLevel) {
                this.doSlam(serverLevel);
            }
        }

        if (this.hasSlammed && !this.frozen && this.entity.onGround()) {
            this.frozen = true;
            this.entity.setNoGravity(true);
        }

        if (this.frozen) {
            this.entity.setDeltaMovement(Vec3.ZERO);
            this.entity.setSlamTick(this.attackTick);
        } else {
            this.entity.setSlamTick(-1);
        }

        // When the entity is on ground it is vulnerable
        this.entity.setSlammingVulnerable(this.attackTick >= SLAM_TICK && this.attackTick <= GROUNDED_END_TICK);
    }

    @Override
    public void stop() {
        this.entity.setSlamming(false);
        this.entity.setSlammingVulnerable(false);
        this.entity.setSlamTick(-1);
        this.entity.setNoGravity(false);

        this.hasSlammed = false;
        this.frozen = false;
        this.target = null;
        this.attackTick = 0;
        this.cooldown = DEFAULT_COOLDOWN;
    }

    private void doSlam(ServerLevel level) {
        Vec3 pos = this.entity.position();
        level.playSound(null, pos.x(), pos.y(), pos.z(), SoundEvents.GENERIC_EXPLODE.value(), SoundSource.HOSTILE, 1.2f, 0.75f);
        level.playSound(null, pos.x(), pos.y(), pos.z(), SoundEvents.RAVAGER_STUNNED, SoundSource.HOSTILE, 1.0f, 0.8f);
        spawnRing(level, pos.x, pos.y, pos.z);
        damageAround(level);
    }

    private void spawnRing(ServerLevel level, double x, double y, double z) {
        BlockPos below = this.entity.blockPosition().below();
        BlockState belowState = level.getBlockState(below);
        boolean belowIsAir = belowState.isAir();

        for(int i = 0; i < PARTICLE_COUNT; i++) {
            double angle = (Math.PI * 2.0 / PARTICLE_COUNT) * i;
            double dirX = Math.cos(angle);
            double dirZ = Math.sin(angle);

            double posX = x + dirX * 0.6;
            double posZ = z + dirZ * 0.6;

            level.sendParticles(ParticleTypes.CLOUD, posX, y + 0.1, posZ, 0, dirX, 0.08, dirZ, 0.55);

            if(!belowIsAir && i % 2 == 0) {
                level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, belowState), posX, y + 0.1, posZ, 0, dirX, 0.35, dirZ, 0.6);
            }
        }

        level.sendParticles(ParticleTypes.EXPLOSION, x, y + 0.2, z, 1, 0, 0, 0, 0);
    }

    private void damageAround(ServerLevel level) {
        AABB area = this.entity.getBoundingBox().inflate(ATTACK_RANGE, 1.5, ATTACK_RANGE);
        for (LivingEntity entityInRange: level.getEntitiesOfClass(LivingEntity.class, area, entity -> entity.isAlive() && !(entity instanceof BabyManEntity))) {
            double distSqr = entityInRange.distanceToSqr(this.entity);
            if (distSqr > ATTACK_RANGE * ATTACK_RANGE) {
                continue;
            }

            if (!entityInRange.onGround() && entityInRange.getY() > this.entity.getY() + 1) {
                continue;
            }

            if (isParrying(entityInRange)) {
                Vec3 pos = entityInRange.position();
                entityInRange.hurtServer(level, level.damageSources().mobAttack(this.entity), (float) this.attackDamage());
                level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.SHIELD_BLOCK.value(), SoundSource.PLAYERS, 1f, 1.6f);
                level.sendParticles(ParticleTypes.CRIT, pos.x, entityInRange.getEyeY() - 0.2, pos.z, 12, 0.25f, 0.25f, 0.25f, 0.15f);
                continue;
            }

            this.entity.doHurtTarget(level, entityInRange);

            if (entityInRange.isBlocking()) {
                disableBlockingItem(level, entityInRange);
            }

            Vec3 push = entityInRange.position().subtract(this.entity.position()).normalize().scale(0.75);
            entityInRange.push(push.x, 0.5, push.z);
            entityInRange.syncVelocity = true;
        }
    }

    private boolean isParrying(LivingEntity entityInRange) {
        if (!entityInRange.isBlocking()) {
            return false;
        }

        Vec3 look = entityInRange.getLookAngle();
        Vec3 toBabyMan = this.entity.position().subtract(entityInRange.position());
        double horizontalLength = Math.sqrt(toBabyMan.x * toBabyMan.x + toBabyMan.z * toBabyMan.z);
        double lookLength = Math.sqrt(look.x * look.x + look.z * look.z);
        double facing = (look.x * toBabyMan.x + look.z * toBabyMan.z) / (horizontalLength * lookLength);
//        double facing = toBabyMan.dot(look);
        if (facing < PARRY_FACING) {
            return false;
        }

        int activeTicks = entityInRange.getTicksUsingItem() - blockDelayTicks(entityInRange);
        return activeTicks >= 0 && activeTicks <= PARRY_WINDOW;
    }

    private static int blockDelayTicks(LivingEntity entity) {
        ItemStack stack = entity.getUseItem();
        BlocksAttacks blocksAttacks = stack.get(DataComponents.BLOCKS_ATTACKS);

        return blocksAttacks == null ? 0 : blocksAttacks.blockDelayTicks();
    }

    private static void disableBlockingItem(ServerLevel level, LivingEntity entityInRange) {
        ItemStack stack = entityInRange.getUseItem();
        BlocksAttacks blocksAttacks = stack.get(DataComponents.BLOCKS_ATTACKS);

        if (blocksAttacks != null) {
            blocksAttacks.disable(level, entityInRange, LATE_BLOCK_DISABLE_SECONDS, stack);
        }
    }
}
