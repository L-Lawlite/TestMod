package net.lawliet.testmod.networking;

import net.lawliet.testmod.gui.inferface.ITabbedBlock;
import net.lawliet.testmod.networking.packet.StationTabPacket;
import net.lawliet.testmod.networking.packet.TestPacketC2S;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Handle packet from CLIENT to SERVER
 * <p>This is on the SERVER
 **/
public class ClientPayloadHandler {

    public static void handleTestPacket(TestPacketC2S testPacketC2S, IPayloadContext context) {
        ServerLevel serverLevel = (ServerLevel) context.player().level();
        EntityTypes.COW.spawn(serverLevel, context.player().getOnPos().above(), EntitySpawnReason.TRIGGERED);
    }

    public static void handleStationTabPacket(StationTabPacket stationTabPacket, IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();
        BlockPos pos = stationTabPacket.pos();
        ItemStack heldStack = player.containerMenu.getCarried();
        if (!heldStack.isEmpty()) {
            player.containerMenu.setCarried(ItemStack.EMPTY);
        }

        Level level = player.level();
        if (!level.hasChunk(pos.getX(), pos.getZ())) {
            return;
        }

        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof ITabbedBlock block) {
            block.openGui(player, level, pos);
        } else {
            MenuProvider provider = state.getMenuProvider(level, pos);
            if (provider != null) {
                player.openMenu(provider);
            }
        }

        if (!heldStack.isEmpty()) {
            player.containerMenu.setCarried(heldStack);
            player.connection.send(new ClientboundContainerSetSlotPacket(-1, -1, -1, heldStack));
        }
    }
}
