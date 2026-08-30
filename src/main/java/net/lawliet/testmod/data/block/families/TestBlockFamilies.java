package net.lawliet.testmod.data.block.families;

import com.google.common.collect.Maps;
import net.lawliet.testmod.registries.TestBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class TestBlockFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();

    public static final BlockFamily AZURITE;


    private static BlockFamily.Builder familyBuilder(Block base) {
        BlockFamily.Builder builder = new BlockFamily.Builder(base);
        BlockFamily blockFamily = MAP.put(base, builder.getFamily());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + String.valueOf(BuiltInRegistries.BLOCK.getKey(base)));
        } else {
            return builder;
        }
    }

    static {
        AZURITE = familyBuilder(TestBlocks.AZURITE_BLOCK.get()).button(TestBlocks.AZURITE_BUTTON.get()).pressurePlate(TestBlocks.AZURITE_PRESSURE_PLATE.get())
                .stairs(TestBlocks.AZURITE_STAIRS.get()). slab(TestBlocks.AZURITE_SLAB.get()).recipeGroupPrefix("azurite").generateStonecutterRecipe().getFamily();
    }

}
