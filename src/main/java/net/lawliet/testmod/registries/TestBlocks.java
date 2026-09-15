package net.lawliet.testmod.registries;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.block.*;
import net.lawliet.testmod.block.crop.RiceCropBlock;
import net.lawliet.testmod.block.flammable.*;
import net.lawliet.testmod.block.state.TestBlockStateProperties;
import net.lawliet.testmod.block.crop.OnionBlock;
import net.lawliet.testmod.block.type.TestBlockSetType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
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
    public static final DeferredBlock<Block> AZURITE_SLAB = registerBlock("azurite_slab",SlabBlock::new, properties -> properties.strength(0.5f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> AZURITE_PRESSURE_PLATE = registerBlock("azurite_pressure_plate", properties -> new PressurePlateBlock(
            BlockSetType.IRON,
            properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .forceSolidOn().noCollision()
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final DeferredBlock<Block> AZURITE_BUTTON = registerBlock("azurite_button", properties -> new ButtonBlock(
            BlockSetType.IRON,
            2,
            properties.noCollision().pushReaction(PushReaction.DESTROY).strength(0.5f)
    ));
    public static final DeferredBlock<Block> AZURITE_FENCE = registerBlock("azurite_fence", FenceBlock::new, properties -> properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).forceSolidOn());
    public static final DeferredBlock<Block> AZURITE_FENCE_GATE = registerBlock("azurite_fence_gate", properties -> new FenceGateBlock(new WoodType("azurite", BlockSetType.IRON), properties.forceSolidOn().strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final DeferredBlock<Block> AZURITE_WALLS = registerBlock("azurite_walls", WallBlock::new, properties -> properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> AZURITE_DOOR = registerBlock("azurite_door", properties -> new DoorBlock(
            BlockSetType.IRON,
            properties.strength(2f).noOcclusion().pushReaction(PushReaction.DESTROY).requiresCorrectToolForDrops()
    ));
    public static final DeferredBlock<Block> AZURITE_TRAPDOOR = registerBlock("azurite_trapdoor", properties -> new TrapDoorBlock(
            BlockSetType.IRON,
            properties.noCollision().pushReaction(PushReaction.DESTROY).strength(2f).requiresCorrectToolForDrops().isValidSpawn(Blocks::never)
    ));

    public static final DeferredBlock<Block> AZURITE_LAMP = registerBlock("azurite_lamp", AzuriteLampBlock::new,
            properties -> properties.strength(3f).sound(SoundType.GLASS).lightLevel(state -> state.getValue(TestBlockStateProperties.CLICKED) ? 15 : 0).isValidSpawn(Blocks::always)
            );

    public static final DeferredBlock<Block> PEDESTAL = registerBlock("pedestal", PedestalBlock::new,
            properties -> properties.strength(2).requiresCorrectToolForDrops().sound(SoundType.STONE));

    public static final DeferredBlock<Block> ONION = BLOCKS.registerBlock("onion_crop", OnionBlock::new,
            properties -> properties.randomTicks().instabreak().noCollision().pushReaction(PushReaction.DESTROY).sound(SoundType.CROP));
    public static final DeferredBlock<Block> GOJI_BERRY_BUSH = BLOCKS.registerBlock("goji_berry_bush", GojiBerryBlock::new,
            properties -> properties.randomTicks().noCollision().pushReaction(PushReaction.DESTROY).sound(SoundType.SWEET_BERRY_BUSH));
    public static final DeferredBlock<Block> RICE = BLOCKS.registerBlock("rice_crop", RiceCropBlock::new, properties -> properties.randomTicks().instabreak().noCollision().pushReaction(PushReaction.DESTROY).sound(SoundType.CROP));

    public static final DeferredBlock<Block> CRYSTALLIZER = registerBlock("crystallizer", CrystallizerBlock::new, properties -> properties.strength(2f).requiresCorrectToolForDrops().lightLevel(state -> state.getValue(CrystallizerBlock.LIT) ? 7 : 0));

    public static final DeferredBlock<Block> DRIFTWOOD_LOG = registerBlock("driftwood_log", properties ->
            new FlammableRotatedPillarBlock(properties
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2)
                    .sound(SoundType.CHERRY_WOOD)
                    .ignitedByLava()
                    , 5, 5));
    public static final DeferredBlock<Block> DRIFTWOOD_WOOD = registerBlock("driftwood_wood", properties ->
            new FlammableRotatedPillarBlock(properties
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2)
                    .sound(SoundType.CHERRY_WOOD)
                    .ignitedByLava()
                    , 5, 5));
    public static final DeferredBlock<Block> STRIPPED_DRIFTWOOD_LOG = registerBlock("stripped_driftwood_log", properties ->
            new FlammableRotatedPillarBlock(properties
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2)
                    .sound(SoundType.CHERRY_WOOD)
                    .ignitedByLava()
                    , 5, 5));
    public static final DeferredBlock<Block> STRIPPED_DRIFTWOOD_WOOD = registerBlock("stripped_driftwood_wood", properties ->
            new FlammableRotatedPillarBlock(properties
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2)
                    .sound(SoundType.CHERRY_WOOD)
                    .ignitedByLava()
                    , 5, 5));

    public static final DeferredBlock<Block> DRIFTWOOD_PLANKS = registerBlock("driftwood_planks", properties ->
            new FlammableBlock(properties
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2, 3)
                    .sound(SoundType.CHERRY_WOOD)
                    .ignitedByLava()
                    , 20, 5));
    public static final DeferredBlock<Block> DRIFTWOOD_LEAVES = registerBlock("driftwood_leaves", properties ->
            new FlammableUntintedParticleLeavesBlock(0.01f, ParticleTypes.CHERRY_LEAVES, properties
                    .mapColor(MapColor.COLOR_GREEN)
                    .strength(0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating(Blocks::never)
                    .isViewBlocking(Blocks::never)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(Blocks::never)
                    , 60, 30));

    public static final DeferredBlock<Block> DRIFTWOOD_STAIRS = registerBlock("driftwood_stairs", properties -> new FlammableStairBlock(
            DRIFTWOOD_PLANKS.get().defaultBlockState(),
            properties.mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2, 3)
                    .sound(SoundType.CHERRY_WOOD)
                    .ignitedByLava()
            , 20, 5
    ));
    public static final DeferredBlock<Block> DRIFTWOOD_SLAB = registerBlock("driftwood_slab", properties -> new FlammableSlabBlock(properties
            .mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2, 3)
            .sound(SoundType.CHERRY_WOOD)
            .ignitedByLava()
            , 20, 5
    ));
    public static final DeferredBlock<Block> DRIFTWOOD_PRESSURE_PLATE = registerBlock("driftwood_pressure_plate", properties -> new PressurePlateBlock(
            TestBlockSetType.DRIFTWOOD,
            properties.mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2, 3)
                    .sound(SoundType.CHERRY_WOOD)
                    .ignitedByLava()
    ));
    public static final DeferredBlock<Block> DRIFTWOOD_BUTTON = registerBlock("driftwood_button", properties -> new ButtonBlock(
            TestBlockSetType.DRIFTWOOD,
            2,
            properties.noCollision().pushReaction(PushReaction.DESTROY).strength(0.5f)
    ));
    public static final DeferredBlock<Block> DRIFTWOOD_FENCE = registerBlock("driftwood_fence", properties -> new FlammableFenceBlock(
            properties.mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2, 3)
                    .sound(SoundType.CHERRY_WOOD)
                    .ignitedByLava()
            , 20, 5
    ));
    public static final DeferredBlock<Block> DRIFTWOOD_FENCE_GATE = registerBlock("driftwood_fence_gate", properties -> new FlammableFenceGateBlock(
            new WoodType("driftwood", TestBlockSetType.DRIFTWOOD),
            properties.mapColor(MapColor.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2, 3)
                .sound(SoundType.CHERRY_WOOD)
                .ignitedByLava(),
            20, 5
    ));
    public static final DeferredBlock<Block> DRIFTWOOD_SHELF = registerBlock("driftwood_shelf", properties -> new FlammableShelfBlock(
            properties.mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.SHELF)
                    .ignitedByLava()
                    .strength(2.0F, 3.0F)
                    , 30, 20
    ));


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
        output.accept(AZURITE_PRESSURE_PLATE);
        output.accept(AZURITE_BUTTON);
        output.accept(AZURITE_FENCE);
        output.accept(AZURITE_FENCE_GATE);
        output.accept(AZURITE_WALLS);
        output.accept(AZURITE_DOOR);
        output.accept(AZURITE_TRAPDOOR);
        output.accept(AZURITE_LAMP);
        output.accept(PEDESTAL);
        output.accept(CRYSTALLIZER);
        output.accept(DRIFTWOOD_LOG);
        output.accept(DRIFTWOOD_WOOD);
        output.accept(STRIPPED_DRIFTWOOD_LOG);
        output.accept(STRIPPED_DRIFTWOOD_WOOD);
        output.accept(DRIFTWOOD_PLANKS);
        output.accept(DRIFTWOOD_STAIRS);
        output.accept(DRIFTWOOD_SLAB);
        output.accept(DRIFTWOOD_BUTTON);
        output.accept(DRIFTWOOD_PRESSURE_PLATE);
        output.accept(DRIFTWOOD_FENCE);
        output.accept(DRIFTWOOD_FENCE_GATE);
        output.accept(DRIFTWOOD_LEAVES);
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
    @SuppressWarnings("unused")
    public static DeferredBlock<Block> registerSimpleBlock(String name, Supplier<BlockBehaviour.Properties> properties) {
        return registerBlock(name, Block::new, properties);
    }


    /**
     * Adds a new simple {@link Block} with the default {@link BlockBehaviour.Properties properties} to the list of entries to be registered and returns a {@link DeferredHolder} that will be populated with the created block automatically. Also creates a simple {@link BlockItem} for that block. Also creates a simple {@link BlockItem} for that block.
     *
     * @param name The new block's name. It will automatically have the {@linkplain DeferredRegister#getNamespace() namespace} prefixed.
     * @return A {@link DeferredHolder} that will track updates from the registry for this block.
     */
    @SuppressWarnings("unused")
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
