package net.lawliet.testmod.worldgen.tree.trunk.placer;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.worldgen.tree.SpiralTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TestTruckPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, TestMod.MODID);

    public static final Supplier<TrunkPlacerType<SpiralTrunkPlacer>> SPIRAL_TRUNK_PLACER = TRUNK_PLACER.register("spiral_trunk_placer", () -> new TrunkPlacerType<>(SpiralTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}
