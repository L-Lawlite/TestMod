package net.lawliet.testmod.registries;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.block.MagicBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static net.lawliet.testmod.registries.TestItems.ITEMS;

public class TestBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TestMod.MODID);

    public static final DeferredBlock<Block> AZURITE_BLOCK = registerSimpleBlock("azurite_block", properties -> properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.AMETHYST)
    );
    public static final DeferredBlock<Block> RAW_AZURITE_BLOCK = registerSimpleBlock("raw_azurite_block", properties -> properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.AMETHYST)
    );
    public static final DeferredBlock<Block> AZURITE_ORE = registerBlock("azurite_ore",properties ->  new DropExperienceBlock(UniformInt.of(2,4), properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
    ));
    public static final DeferredBlock<Block> AZURITE_DEEPSLATE_ORE = registerBlock("azurite_deepslate_ore", properties ->  new DropExperienceBlock(UniformInt.of(2,4), properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE)
    ));
    public static final DeferredBlock<Block> AZURITE_NETHER_ORE = registerBlock("azurite_nether_ore", properties ->  new DropExperienceBlock(UniformInt.of(2,4), properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.NETHERRACK)
    ));
    public static final DeferredBlock<Block> AZURITE_END_ORE = registerBlock("azurite_end_ore", properties ->  new DropExperienceBlock(UniformInt.of(2,4), properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
    ));

    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block", MagicBlock::new, properties ->
            properties.strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            );

    public static final DeferredBlock<Block> TEST_SHELF = registerBlock("test_shelf", ShelfBlock::new, properties -> properties.strength(2f,3f).ignitedByLava().sound(SoundType.SHELF));
    public static final DeferredBlock<Block> AZURITE_STAIRS = registerBlock("azurite_stairs", properties -> new StairBlock(
            AZURITE_BLOCK.get().defaultBlockState(),
            properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)
    ));
    public static final DeferredBlock<Block> AZURITE_SLAB = registerBlock("azurite_slab",SlabBlock::new, properties -> properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST));
//    public static final DeferredBlock<Block> AZURITE_P

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


    public static void addToTestBlockTab(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        output.accept(AZURITE_BLOCK.get());
        output.accept(RAW_AZURITE_BLOCK);
        output.accept(AZURITE_ORE);
        output.accept(AZURITE_DEEPSLATE_ORE);
        output.accept(AZURITE_NETHER_ORE);
        output.accept(AZURITE_END_ORE);
        output.accept(MAGIC_BLOCK);
        output.accept(TEST_SHELF);
        output.accept(AZURITE_STAIRS);
        output.accept(AZURITE_SLAB);

    }

    // Registry Helpers
    /**
     * Adds a new simple {@link Block} with the given {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties properties} to the list of entries to be registered and returns a {@link net.neoforged.neoforge.registries.DeferredHolder} that will be populated with the created block automatically. Also creates a simple {@link BlockItem} for that block.
     *
     * @param name       The new block's name. It will automatically have the {@linkplain DeferredRegister#getNamespace() namespace} prefixed.
     * @param properties The unary operator, which is passed a new {@link BlockBehaviour.Properties} for the created block.
     * @return A {@link DeferredHolder} that will track updates from the registry for this block.
     **/
    public static DeferredBlock<Block> registerSimpleBlock(String name, UnaryOperator<BlockBehaviour.Properties> properties) {
        return registerBlock(name, Block::new, properties);
    }

    /**
     * Adds a new simple {@link Block} with the given {@link BlockBehaviour.Properties properties} to the list of entries to be registered and returns a {@link DeferredHolder} that will be populated with the created block automatically. Also creates a simple {@link BlockItem} for that block. Also creates a simple {@link BlockItem} for that block.
     *
     * @param name       The new block's name. It will automatically have the {@linkplain DeferredRegister#getNamespace() namespace} prefixed.
     * @param properties The supplied properties for the created block.
     * @return A {@link DeferredHolder} that will track updates from the registry for this block.
     */
    public static DeferredBlock<Block> registerSimpleBlock(String name, Supplier<BlockBehaviour.Properties> properties) {
        return registerBlock(name, Block::new, properties);
    }


    /**
     * Adds a new simple {@link Block} with the default {@link BlockBehaviour.Properties properties} to the list of entries to be registered and returns a {@link DeferredHolder} that will be populated with the created block automatically. Also creates a simple {@link BlockItem} for that block. Also creates a simple {@link BlockItem} for that block.
     *
     * @param name The new block's name. It will automatically have the {@linkplain DeferredRegister#getNamespace() namespace} prefixed.
     * @return A {@link DeferredHolder} that will track updates from the registry for this block.
     */
    public static DeferredBlock<Block> registerSimpleBlock(String name) {
        return registerSimpleBlock(name, UnaryOperator.identity());
    }

    /**
     * Adds a new block to the list of entries to be registered and returns a {@link DeferredHolder} that will be populated with the created block automatically. Also creates a simple {@link BlockItem} for that block.
     *
     * @param name       The new block's name. It will automatically have the {@linkplain DeferredRegister#getNamespace() namespace} prefixed.
     * @param func       A factory for the new block. The factory should not cache the created block.
     * @param properties The unary operator, which is passed a new {@link BlockBehaviour.Properties} for the created block.
     * @return A {@link DeferredHolder} that will track updates from the registry for this block.
     * @see #registerBlock(String, Function, Supplier)
     * @see #registerBlock(String, Function)
     * @see #registerSimpleBlock(String, Supplier)
     * @see #registerSimpleBlock(String, UnaryOperator)
     * @see #registerSimpleBlock(String)
     */
    public static <B extends Block> DeferredBlock<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> func, UnaryOperator<BlockBehaviour.Properties> properties) {
        return registerBlock(name, func, () -> properties.apply(BlockBehaviour.Properties.of()));
    }

    /**
     * Adds a new block to the list of entries to be registered and returns a {@link DeferredHolder} that will be populated with the created block automatically. Also creates a simple {@link BlockItem} for that block.
     * This method uses the default {@link BlockBehaviour.Properties}.
     *
     * @param name The new block's name. It will automatically have the {@linkplain DeferredRegister#getNamespace() namespace} prefixed.
     * @param func A factory for the new block. The factory should not cache the created block.
     * @return A {@link DeferredHolder} that will track updates from the registry for this block.
     * @see #registerBlock(String, Function, Supplier)
     * @see #registerBlock(String, Function, UnaryOperator)
     * @see #registerSimpleBlock(String, Supplier)
     * @see #registerSimpleBlock(String, UnaryOperator)
     * @see #registerSimpleBlock(String)
     */
    public static <B extends Block> DeferredBlock<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> func) {
        return registerBlock(name, func, UnaryOperator.identity());
    }

    /**
     * Adds a new block to the list of entries to be registered and returns a {@link DeferredHolder} that will be populated with the created block automatically. Also creates a simple {@link BlockItem} for that block.
     *
     * @param name       The new block's name. It will automatically have the {@linkplain DeferredRegister#getNamespace() namespace} prefixed.
     * @param func       A factory for the new block. The factory should not cache the created block.
     * @param properties The supplied properties for the created block.
     * @return A {@link DeferredHolder} that will track updates from the registry for this block.
     **/
    public static <B extends Block> DeferredBlock<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> func, Supplier<BlockBehaviour.Properties> properties) {
        DeferredBlock<B> block = BLOCKS.register(name, key -> func.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, key))));
        ITEMS.registerSimpleBlockItem(block);
        return block;
    }



}
