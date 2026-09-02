package net.lawliet.testmod.effect;

import net.lawliet.testmod.damagesource.TestDamageTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class StinkyEffect extends MobEffect {
    public StinkyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        AABB boundingBox = mob.getBoundingBox().inflate(amplification + 1);
        List<Entity> entities = serverLevel.getEntities(mob, boundingBox);

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity livingEntity) {
                float distance = livingEntity.distanceTo(mob);
                livingEntity.hurtServer(serverLevel, TestDamageTypes.create(serverLevel, TestDamageTypes.STINKY), 0.25f * ((float)amplification + 1) / distance);
            }
        }

        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        if (tickCount % 60 == 0) {
            return true;
        }
        return super.shouldApplyEffectTickThisTick(tickCount, amplification);
    }
}
