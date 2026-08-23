package net.lawliet.testmod.food;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;

public class TestConsumables {
    public static final Consumable ONION = Consumables.defaultFood()
            .onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.HUNGER)).build();
}
