package net.lawliet.testmod.block.crop;

import net.lawliet.testmod.registries.TestItems;
import net.lawliet.testmod.tags.TestTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RiceCropBlock extends CropBlock {
    public RiceCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return TestItems.RICE.get();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return hasSufficientLight(level, pos) && mayPlaceOn(state, level, pos);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        BlockState state2Below = level.getBlockState(pos.below(2));
        return stateBelow.is(Blocks.WATER) && hasValidBlockBelow(state2Below);
    }

    protected boolean hasValidBlockBelow(BlockState state2Below) {
        return  state2Below.is(TestTags.Blocks.RICE_FARMLAND);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true);
        }
        super.randomTick(state, level, pos, random);
    }

    @Override
    public void growCrops(Level level, BlockPos pos, BlockState state) {
        if (!canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true);
            return;
        }
        super.growCrops(level, pos, state);
    }
}
