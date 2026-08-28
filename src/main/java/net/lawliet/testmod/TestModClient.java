package net.lawliet.testmod;

import net.lawliet.testmod.registries.TestItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = TestMod.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = TestMod.MODID, value = Dist.CLIENT)
public class TestModClient {
    public TestModClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        TestMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        TestMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    static void onComputeFOVModifierEvent(ComputeFovModifierEvent event) {
        Player player = event.getPlayer();
        if(player.isUsingItem() && player.getUseItem().getItem() == TestItems.TEST_BOW.get()) {
            float fovModifier = 1f;
            int tickUsingItem = player.getTicksUsingItem();
            float deltaTicks = (float)tickUsingItem/20f;
            if(deltaTicks > 1f) {
                deltaTicks = 1f;
            } else deltaTicks += deltaTicks;
            fovModifier *= 1f - deltaTicks * 0.15f;
            event.setNewFovModifier(fovModifier);
        }
    }
}
