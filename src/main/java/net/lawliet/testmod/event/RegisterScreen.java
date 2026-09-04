package net.lawliet.testmod.event;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.gui.screen.PedestalScreen;
import net.lawliet.testmod.registries.gui.TestMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = TestMod.MODID, value = Dist.CLIENT)
public class RegisterScreen {
    @SubscribeEvent
    public static void registerScreen(RegisterMenuScreensEvent event) {
        event.register(TestMenu.PEDESTAL_MENU.get(), PedestalScreen::new);
    }
}
