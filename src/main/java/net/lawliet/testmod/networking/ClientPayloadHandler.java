package net.lawliet.testmod.networking;

import net.lawliet.testmod.networking.packet.TestPacketC2S;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Handle packet from CLIENT to SERVER
 * This is on the SERVER
 **/
public class ClientPayloadHandler {

    public static void handleTestPacket(TestPacketC2S testPacketC2S, IPayloadContext context) {
        ServerLevel serverLevel = (ServerLevel) context.player().level();
        EntityTypes.COW.spawn(serverLevel, context.player().getOnPos().above(), EntitySpawnReason.TRIGGERED);
    }
}
