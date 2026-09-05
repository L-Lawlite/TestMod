package net.lawliet.testmod.keybinding;

import com.mojang.blaze3d.platform.InputConstants;
import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.networking.packet.TestPacketC2S;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = TestMod.MODID, value =  Dist.CLIENT)
public class TestKeyMapping {
    public static final KeyMappingRegister TEST = KeyMappingRegister.create(new KeyMapping(getKeyTranslate("test"), InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, KeyMapping.Category.MISC));


    private static String getKeyTranslate(String name) {
        return "key.%s.%s".formatted(TestMod.MODID, name);
    }

    public record KeyMappingRegister(KeyMapping keyMapping, Lazy<KeyMapping> keyPress) {

        public static KeyMappingRegister create(KeyMapping keyMapping) {
            return new KeyMappingRegister(keyMapping, Lazy.of(() -> keyMapping));
        }
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        LocalPlayer player = Minecraft.getInstance().player;
        assert player != null;
        while(TEST.keyPress().get().consumeClick()) {
            player.sendSystemMessage(Component.literal("Test Key pressed"));
            ClientPacketDistributor.sendToServer(new TestPacketC2S("test", 1));
        }
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(TEST.keyPress().get());
    }
}
