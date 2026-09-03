package net.lawliet.testmod.datagen.villager;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.villager.Poi;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TestPoiTags extends PoiTypeTagsProvider {
    public TestPoiTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TestMod.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        addPoiTag(PoiTypeTags.ACQUIRABLE_JOB_SITE, List.of(Poi.TEST_PROFESSION_POI)).build();
    }

    private TagBuilder addPoiTag(TagKey<PoiType> poiTypeTag, List<Holder<PoiType>> holders) {
        TagBuilder builder = getOrCreateRawBuilder(poiTypeTag);
        for(Holder<PoiType> holder : holders) {
            builder.add(TagEntry.element(holder.unwrapKey().get().identifier()));
        }
        return builder;
    }
}
