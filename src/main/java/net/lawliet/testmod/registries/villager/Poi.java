package net.lawliet.testmod.registries.villager;

import com.google.common.collect.ImmutableSet;
import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

public class Poi {
    public static final DeferredRegister<PoiType> POI_TYPE = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, TestMod.MODID);

    public static final DeferredHolder<PoiType, PoiType> TEST_PROFESSION_POI = POI_TYPE.register("test_profession_poi", () -> new PoiType(getBlockStates(TestBlocks.MAGIC_BLOCK.get()), 1, 1));

    public static void register(IEventBus eventBus) {
        POI_TYPE.register(eventBus);
    }

    private static Set<BlockState> getBlockStates(Block block) {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }
}
