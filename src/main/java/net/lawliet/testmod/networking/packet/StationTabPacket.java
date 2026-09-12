package net.lawliet.testmod.networking.packet;

import net.lawliet.testmod.TestMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record StationTabPacket(BlockPos pos) implements CustomPacketPayload {
    public static final Type<StationTabPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TestMod.MODID, "station_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, StationTabPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, StationTabPacket::pos,
            StationTabPacket::new
    );

    @SuppressWarnings("unused")
    public StationTabPacket(FriendlyByteBuf buf) {
        this(buf.readBlockPos());
    }

                            @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
