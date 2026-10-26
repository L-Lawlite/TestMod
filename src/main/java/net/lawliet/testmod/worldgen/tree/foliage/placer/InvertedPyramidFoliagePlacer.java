package net.lawliet.testmod.worldgen.tree.foliage.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.lawliet.testmod.registries.worldgen.tree.TestFoliagePlacerTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class InvertedPyramidFoliagePlacer extends FoliagePlacer {
    private final int height;

    public static final MapCodec<InvertedPyramidFoliagePlacer>  CODEC = RecordCodecBuilder.mapCodec(instance -> foliagePlacerParts(instance)
            .and(Codec.intRange(0, 16).fieldOf("height").forGetter(inst -> inst.height))
            .apply(instance, InvertedPyramidFoliagePlacer::new));

    public InvertedPyramidFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return TestFoliagePlacerTypes.INVERTED_PYRAMID_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(WorldGenLevel level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int treeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {
        for(int i = 0; i < foliageHeight; i++) {
            int currentRadius = leafRadius + i - 1;
            placeLeavesRow(level, foliageSetter, random, config, foliageAttachment.pos().above(i+1), currentRadius, 0, foliageAttachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource randomSource, int i, int i1, int i2, int i3, boolean b) {
        return false;
    }
}
