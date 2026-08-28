package net.lawliet.testmod.item;

import net.lawliet.testmod.data.component.BlockData;
import net.lawliet.testmod.registries.TestDataComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class DataTabletItem extends Item {
    public DataTabletItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack handItem = player.getItemInHand(hand);
        if (handItem.has(TestDataComponent.BLOCK_DATA.get())) {
            handItem.remove(TestDataComponent.BLOCK_DATA.get());
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return itemStack.has(TestDataComponent.BLOCK_DATA.get());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if (itemStack.has(TestDataComponent.BLOCK_DATA.get())) {
            BlockData blockData = itemStack.get(TestDataComponent.BLOCK_DATA.get());
            assert blockData != null;
            BlockPos pos = blockData.pos();
            Component blockName = blockData.block().getName().withStyle(ChatFormatting.AQUA);
            Component coordinates = ComponentUtils.wrapInSquareBrackets(Component.translatable("chat.coordinates", pos.getX(), pos.getY(), pos.getZ())).withStyle(ChatFormatting.GREEN);
            builder.accept(Component.translatable("translation.test.args",blockName,coordinates));
        }
    }
}
