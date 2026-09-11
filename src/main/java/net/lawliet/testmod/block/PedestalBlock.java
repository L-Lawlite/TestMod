package net.lawliet.testmod.block;

import com.mojang.serialization.MapCodec;
import net.lawliet.testmod.block.entity.PedestalBlockEntity;
import net.lawliet.testmod.gui.inferface.ITabbedBlock;
import net.lawliet.testmod.gui.menu.BaseContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class PedestalBlock extends BaseEntityBlock implements ITabbedBlock {
    public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    public static final MapCodec<PedestalBlock> CODEC = simpleCodec(PedestalBlock::new);

    public PedestalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new PedestalBlockEntity(worldPosition, blockState);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, ItemStack toolStack, boolean willHarvest, FluidState fluid) {
        if(level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity) {
            pedestalBlockEntity.drops();
            level.updateNeighbourForOutputSignal(pos, this);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, toolStack, willHarvest, fluid);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity) {
            //Open Menu
            if(player.isCrouching()) {
                return this.openGui(player, level, pos) ?  InteractionResult.SUCCESS : InteractionResult.PASS;
            }

            // Insert
            if(pedestalBlockEntity.isEmpty() && !itemStack.isEmpty()) {
                pedestalBlockEntity.insertItem(itemStack);
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 2.0F);
            }
            // Extract
            else if (!pedestalBlockEntity.isEmpty()) {
                ItemStack stack = pedestalBlockEntity.getItem();
                pedestalBlockEntity.clearContents();
                if(!player.getInventory().add(stack)) {
                  player.drop(stack, false);
                }
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }

        return InteractionResult.SUCCESS;
    }

    /**
     * Called when the block is activated to open the UI. Override to return false for blocks with no inventory
     *
     * @param player Player instance
     * @param level  World instance
     * @param pos    Block position
     * @return true if the GUI opened, false if not
     */
    @Override
    public boolean openGui(Player player, Level level, BlockPos pos) {
        if(!level.isClientSide()) {
            if(level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity) {
                MenuProvider menuProvider = new SimpleMenuProvider(pedestalBlockEntity, Component.translatable("block.testmod.pedestal"));
                player.openMenu(menuProvider, pos);
                if(player.containerMenu instanceof BaseContainerMenu<?> menu) {
                    menu.syncOnOpen((ServerPlayer) player);
                }
            } else {
                throw new IllegalStateException("Container Provider missing");
            }
        }
        return true;
    }
}
