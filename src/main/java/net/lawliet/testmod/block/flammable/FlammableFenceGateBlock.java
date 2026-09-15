package net.lawliet.testmod.block.flammable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Optional;

public class FlammableFenceGateBlock extends FenceGateBlock {
    private final int flammability;
    private final int fireSpreadSpeed;

    public FlammableFenceGateBlock(WoodType type, Properties properties, int flammability, int fireSpreadSpeed) {
        super(type, properties);
        this.flammability = flammability;
        this.fireSpreadSpeed = fireSpreadSpeed;
    }

    @SuppressWarnings("unused")
    public FlammableFenceGateBlock(Properties properties, SoundEvent openSound, SoundEvent closeSound,  int flammability, int fireSpreadSpeed) {
        super(properties, openSound, closeSound);
        this.flammability = flammability;
        this.fireSpreadSpeed = fireSpreadSpeed;
    }

    @SuppressWarnings("unused")
    public FlammableFenceGateBlock(Optional<WoodType> type, Properties properties, Optional<SoundEvent> openSound, Optional<SoundEvent> closeSound,  int flammability, int fireSpreadSpeed) {
        super(type, properties, openSound, closeSound);
        this.flammability = flammability;
        this.fireSpreadSpeed = fireSpreadSpeed;
    }


    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return flammability > 0;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return flammability;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return fireSpreadSpeed;
    }
}
