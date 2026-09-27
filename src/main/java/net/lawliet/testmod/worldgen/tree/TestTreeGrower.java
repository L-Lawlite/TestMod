package net.lawliet.testmod.worldgen.tree;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.worldgen.features.TreeFeatureSets;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class TestTreeGrower {
    public static final TreeGrower DRIFTWOOD = new TreeGrower(
            TestMod.createIdentifier("driftwood").toString(),
            Optional.empty(),
            Optional.of(TreeFeatureSets.DRIFTWOOD_TREE.configuredFeature()),
            Optional.empty()
    );
}
