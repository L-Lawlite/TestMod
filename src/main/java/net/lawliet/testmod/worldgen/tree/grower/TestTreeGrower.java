package net.lawliet.testmod.worldgen.tree.grower;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.worldgen.features.TreeFeatureSets;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;

public class TestTreeGrower {
    public static final TreeGrower DRIFTWOOD = new TreeGrower(
            TestMod.createIdentifier("driftwood").toString(),
            WeightedList.of(TreeFeatureSets.DRIFTWOOD_TREE.feature()),
            WeightedList.of(),
            WeightedList.of(),
            null
    );
}
