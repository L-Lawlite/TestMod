package net.lawliet.testmod.registries;

import net.lawliet.testmod.TestMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TestStats {
    public static final DeferredRegister<Identifier> CUSTOM_STATS = DeferredRegister.create(BuiltInRegistries.CUSTOM_STAT, TestMod.MODID);

    public static final Supplier<Identifier> VALUABLES_FOUND = createStat("valuables_found");

    private static Supplier<Identifier> createStat(String key) {
        return CUSTOM_STATS.register(key, () -> Identifier.fromNamespaceAndPath(TestMod.MODID, key));
    }

    public static void register(IEventBus eventBus) {
        CUSTOM_STATS.register(eventBus);
    }
}
