package net.lawliet.testmod.item;

import net.lawliet.testmod.data.component.BlockData;
import net.lawliet.testmod.registries.TestDataComponent;
import net.lawliet.testmod.registries.TestItems;
import net.lawliet.testmod.tags.TestTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;


public class MetalDetectorItem extends Item {
    protected int range = 64;

    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos positionClicked = context.getClickedPos();
        Player player = context.getPlayer();

        if (!level.isClientSide()) {
            boolean foundBlock = false;
            for(int i=0; i <= positionClicked.getY() + range; i++) {
                BlockPos blockPos = positionClicked.below(i);
                BlockState state = level.getBlockState(blockPos);
                if (isValuableBlock(state)) {
                    outputValueCoordinates(blockPos, player, state.getBlock());
                    foundBlock = true;
                    //Perform other logic
                    level.playSound(null, positionClicked, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.5f, 1f);
                    spawnFoundParticle(level, positionClicked, state);

                    addDataToDataTablet(player, blockPos, state.getBlock());

                    break;
                }
            }
            if(!foundBlock) {
                outputNoValuablesFound(player);
            }
            context.getItemInHand().hurtAndBreak(1, player, context.getHand());

        }
        return InteractionResult.SUCCESS;
    }

    private void addDataToDataTablet(Player player, BlockPos pos, Block foundBlock) {
        int slotIndex = player.getInventory().findSlotMatchingItem(new ItemStack(TestItems.DATA_TABLET.get()));
        if (slotIndex == -1) return;
        ItemStack dataTablet = player.getInventory().getItem(slotIndex);
        dataTablet.set(TestDataComponent.BLOCK_DATA, new BlockData(pos, foundBlock));
    }

    protected void spawnFoundParticle(Level level, BlockPos positionClicked, BlockState state) {
        ServerLevel serverLevel = (ServerLevel) level;
        for (int i=0; i< 20; i++) {
            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), positionClicked.getX() + 0.5, positionClicked.getY() + 1, positionClicked.getZ() + 0.5, 1, Math.cos(i*18) * 0.15, 0.15, Math.sin(i*18) * 0.15, 0.1);
        }
    }

    protected void outputNoValuablesFound(Player player) {
        player.sendSystemMessage(Component.translatable("item.testmod.message.metal_detector.no_valuables").withStyle(style -> style.withColor(ChatFormatting.RED)));
    }

    protected void outputValueCoordinates(BlockPos position, Player player, Block block) {
        Component coordinates = ComponentUtils.wrapInSquareBrackets(Component.translatable("chat.coordinates", position.getX(), position.getY(), position.getZ())).withStyle(ChatFormatting.GREEN);
        Component blockName =  block.getName().withStyle(ChatFormatting.AQUA);
        player.sendSystemMessage(Component.translatable("item.testmod.message.metal_detector.valuable_found", blockName, coordinates));
    }

    protected boolean isValuableBlock(BlockState state) {
        return state.is(TestTags.Blocks.METAL_DETECTABLE);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if (Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable("tooltip.testmod.metal_detector"));
        } else {
            Component shift_key = Component.translatable("key.keyboard.shift").withStyle(ChatFormatting.YELLOW);
            builder.accept(Component.translatable("tooltip.testmod.expansion", shift_key));
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
