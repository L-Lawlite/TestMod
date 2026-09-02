package net.lawliet.testmod.registries;

import net.lawliet.testmod.TestMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TestPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, TestMod.MODID);

    public static final DeferredHolder<Potion, Potion> STINKY_POTION = registerPotion("stinky_potion",
            new MobEffectInstance(TestMobEffects.STINKY, 1200, 0)
            );

    private static DeferredHolder<Potion, Potion> registerPotion(String name, MobEffectInstance... effects) {
        return POTIONS.register(name, () -> new Potion(name, effects));
    }

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

}
