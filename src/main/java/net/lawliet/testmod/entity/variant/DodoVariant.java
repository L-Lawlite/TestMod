package net.lawliet.testmod.entity.variant;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.lawliet.testmod.registries.custom.CustomRegistry;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.entity.variant.PriorityProvider;
import net.minecraft.world.entity.variant.SpawnCondition;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import com.mojang.serialization.Codec;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record DodoVariant(ClientAsset.ResourceTexture adultAssetInfo, SpawnPrioritySelectors spawnConditions) implements PriorityProvider<SpawnContext, SpawnCondition> {
    public static final Codec<DodoVariant> DIRECT_CODEC = RecordCodecBuilder.create((i) -> i.group(
            ClientAsset.ResourceTexture.DEFAULT_FIELD_CODEC.forGetter(DodoVariant::adultAssetInfo),
            SpawnPrioritySelectors.CODEC.fieldOf("spawn_conditions").forGetter(DodoVariant::spawnConditions)
            ).apply(i, DodoVariant::new));
    public static final Codec<DodoVariant> NETWORK_CODEC = RecordCodecBuilder.create(i -> i.group(ClientAsset.ResourceTexture.DEFAULT_FIELD_CODEC.forGetter(DodoVariant::adultAssetInfo)).apply(i, DodoVariant::new));
    public static final Codec<Holder<DodoVariant>> CODEC = RegistryFixedCodec.create(CustomRegistry.DODO_VARIANT_KEY);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<DodoVariant>> STREAM_CODEC = ByteBufCodecs.holderRegistry(CustomRegistry.DODO_VARIANT_KEY);

    private DodoVariant(ClientAsset.ResourceTexture adultAssetInfo) {
        this(adultAssetInfo, SpawnPrioritySelectors.EMPTY);
    }

    @Override
    @NonNull
    public List<Selector<SpawnContext, SpawnCondition>> selectors() {
        return this.spawnConditions.selectors();
    }
}