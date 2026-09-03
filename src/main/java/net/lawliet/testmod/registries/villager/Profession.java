package net.lawliet.testmod.registries.villager;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.lawliet.testmod.TestMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public class Profession {
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, TestMod.MODID);

    public static final DeferredHolder<VillagerProfession, VillagerProfession> TEST_PROFESSION = createVillagerProfession("test_profession", Poi.TEST_PROFESSION_POI, SoundEvents.AMETHYST_BLOCK_CHIME,
            List.of(
                    TestTradeSets.TEST_PROFESSION_LEVEL_1,
                    TestTradeSets.TEST_PROFESSION_LEVEL_2
            )
    );

    public static void register(IEventBus eventBus) {
        VILLAGER_PROFESSIONS.register(eventBus);
    }

    public static DeferredHolder<VillagerProfession, VillagerProfession> createVillagerProfession(
            String name,
            Holder<PoiType> jobSite,
            ImmutableSet<Item> requestedItem,
            ImmutableSet<Block> secondaryPoi,
            @Nullable SoundEvent workSound,
            List<ResourceKey<TradeSet>> trades
            ) {
        Int2ObjectMap<ResourceKey<TradeSet>> tradeSets = new Int2ObjectOpenHashMap<>();
        int i = 0;
        for (ResourceKey<TradeSet> tradeSet : trades) {
            tradeSets.put(++i, tradeSet);
        }
        return VILLAGER_PROFESSIONS.register(name, () -> new VillagerProfession(
                Component.translatable("entity.minecraft.villager.%s.%s".formatted(TestMod.MODID,name)),
                holder -> holder.value() == jobSite.value(),
                holder -> holder.value() == jobSite.value(),
                requestedItem,
                secondaryPoi,
                workSound,
                tradeSets
                ));
    }

    public static DeferredHolder<VillagerProfession, VillagerProfession> createVillagerProfession(
            String name,
            Holder<PoiType> jobSite,
            @Nullable SoundEvent workSound,
            List<ResourceKey<TradeSet>> trades
    ) {
        return createVillagerProfession(name, jobSite, ImmutableSet.of(), ImmutableSet.of(), workSound, trades);
    }


}
