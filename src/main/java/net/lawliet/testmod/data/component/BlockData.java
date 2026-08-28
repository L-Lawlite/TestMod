package net.lawliet.testmod.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.block.Block;


public record BlockData(BlockPos pos, Block block) {
    public static final Codec<BlockData> CODEC = RecordCodecBuilder.create(ins -> ins.group(
            BlockPos.CODEC.fieldOf("pos").forGetter(BlockData::pos),
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(BlockData::block)
    ).apply(ins, BlockData::new));
    public static final StreamCodec<ByteBuf, BlockData> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, BlockData::pos,
            ByteBufCodecs.idMapper(BuiltInRegistries.BLOCK) , BlockData::block,
            BlockData::new
    );
}
