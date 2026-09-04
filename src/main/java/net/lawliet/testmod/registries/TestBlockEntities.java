package net.lawliet.testmod.registries;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.block.entity.CrystallizerBlockEntity;
import net.lawliet.testmod.block.entity.PedestalBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TestBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TestMod.MODID);

    public static final Supplier<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BLOCK_ENTITY = BLOCK_ENTITIES.register("pedestal", () -> new BlockEntityType<>(
            PedestalBlockEntity::new,
            TestBlocks.PEDESTAL.get()
    ));
    public static final Supplier<BlockEntityType<CrystallizerBlockEntity>> CRYSTALLIZER_BLOCK_ENTITY = BLOCK_ENTITIES.register("crystallizer", () -> new BlockEntityType<>(
            CrystallizerBlockEntity::new,
            TestBlocks.CRYSTALLIZER.get()
    ));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
