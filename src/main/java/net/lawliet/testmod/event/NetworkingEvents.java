package net.lawliet.testmod.event;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.networking.ClientPayloadHandler;
import net.lawliet.testmod.networking.packet.TestPacketC2S;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = TestMod.MODID)
public class NetworkingEvents {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1")
                .executesOn(HandlerThread.MAIN);
        registrar.playToServer(TestPacketC2S.TYPE, TestPacketC2S.STREAM_CODEC, ClientPayloadHandler::handleTestPacket);
    }
}
