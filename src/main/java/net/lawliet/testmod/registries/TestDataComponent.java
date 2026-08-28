package net.lawliet.testmod.registries;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.data.component.BlockData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class TestDataComponent {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, TestMod.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockData>> BLOCK_DATA = registerComponent("block_data", builder -> builder.persistent(BlockData.CODEC).networkSynchronized(BlockData.STREAM_CODEC));

    /**
     * @param name The new data component's name. It will automatically have the {@linkplain DeferredRegister#getNamespace() namespace} prefixed.
     * @param builder The unary operator, which is passed a new {@link DataComponentType.Builder} for the created Data component.
     * @return A {@link DeferredHolder} that will track updates from the registry for this entry.
     * There is another definition of this method as well
     * @see DeferredRegister#register(String, Function)
     */
    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> registerComponent(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return DATA_COMPONENT_TYPES.register(name, () -> builder.apply(DataComponentType.builder()).build());
    }


    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
