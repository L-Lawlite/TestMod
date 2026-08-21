package net.lawliet.testmod.block;

import net.lawliet.testmod.TestMod;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TestBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TestMod.MODID);

    public static final DeferredBlock<Block> AZURITE_BLOCK = BLOCKS.registerSimpleBlock("azurite_block", properties -> properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.AMETHYST)
    );
    public static final DeferredBlock<Block> RAW_AZURITE_BLOCK = BLOCKS.registerSimpleBlock("raw_azurite_block", properties -> properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.AMETHYST)
    );
    public static final DeferredBlock<Block> AZURITE_ORE = BLOCKS.registerBlock("azurite_ore",properties ->  new DropExperienceBlock(UniformInt.of(2,4), properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
    ));
    public static final DeferredBlock<Block> AZURITE_DEEPSLATE_ORE = BLOCKS.registerBlock("azurite_deepslate_ore", properties ->  new DropExperienceBlock(UniformInt.of(2,4), properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE)
    ));
    public static final DeferredBlock<Block> AZURITE_NETHER_ORE = BLOCKS.registerBlock("azurite_nether_ore", properties ->  new DropExperienceBlock(UniformInt.of(2,4), properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.NETHERRACK)
    ));
    public static final DeferredBlock<Block> AZURITE_END_ORE = BLOCKS.registerBlock("azurite_end_ore", properties ->  new DropExperienceBlock(UniformInt.of(2,4), properties
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
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

    }
}
