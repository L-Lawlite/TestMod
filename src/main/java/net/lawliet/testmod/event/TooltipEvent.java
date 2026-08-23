package net.lawliet.testmod.event;

import net.lawliet.testmod.registries.TestBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.tooltip.TooltipLocation;
import net.neoforged.neoforge.event.RegisterTooltipAppendersEvent;

@EventBusSubscriber
public class TooltipEvent {

    @SubscribeEvent
    public static void AddTooltip(RegisterTooltipAppendersEvent event) {
        event.registerAppender(TooltipLocation.POST_CUSTOM, (stack, _, _, _, _, builder) -> {
            if (stack.is(TestBlocks.MAGIC_BLOCK.asItem())) {
                builder.accept(Component.translatable("tooltip.testmod.magic_block").withStyle(s -> s.withColor(ChatFormatting.DARK_PURPLE)));
            }
        });
    }
}
