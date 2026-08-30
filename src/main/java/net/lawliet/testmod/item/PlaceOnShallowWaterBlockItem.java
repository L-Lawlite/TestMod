package net.lawliet.testmod.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PlaceOnShallowWaterBlockItem extends PlaceOnWaterBlockItem {
    private final TagKey<Block> allowedBlocks;

    public PlaceOnShallowWaterBlockItem(Block block, Properties properties, TagKey<Block> allowAbleBlockTag) {
        super(block, properties);
        this.allowedBlocks = allowAbleBlockTag;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        BlockState blockBelow = level.getBlockState(hitResult.getBlockPos().below());
        if (!blockBelow.is(this.allowedBlocks)) {
            return InteractionResult.PASS;
        }
        return super.use(level, player, hand);
    }


}
