package net.lawliet.testmod.registries.worldgen.tree;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.worldgen.tree.foliage.placer.InvertedPyramidFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TestFoliagePlacerTypes {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, TestMod.MODID);

    public static final DeferredHolder<FoliagePlacerType<?> ,FoliagePlacerType<InvertedPyramidFoliagePlacer>> INVERTED_PYRAMID_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("inverted_pyramid_foliage_placer", () -> new FoliagePlacerType<>(InvertedPyramidFoliagePlacer.CODEC));

    public static void register(IEventBus bus) {
        FOLIAGE_PLACERS.register(bus);
    }
}
