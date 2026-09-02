package net.lawliet.testmod.event;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

@EventBusSubscriber(modid = TestMod.MODID)
public class LivingEntityEvents {

    @SubscribeEvent
    public static void onLivingEntityVisibilityEvent(LivingEvent.LivingVisibilityEvent event) {
        if (event.getEntity() instanceof LivingEntity livingEntity && livingEntity.hasEffect(TestMobEffects.STINKY)) {
            MobEffectInstance mobEffect = livingEntity.getEffect(TestMobEffects.STINKY);
            assert mobEffect != null;
            int effectAmplifier =  mobEffect.getAmplifier();
            event.modifyVisibility(1 + effectAmplifier * 0.2f);
        }

    }
}
