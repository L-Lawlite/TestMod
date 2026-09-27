package net.lawliet.testmod.datagen.tags;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class TestEntityTypesTagProvider extends EntityTypeTagsProvider {
    public TestEntityTypesTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TestMod.MODID);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected void addTags(HolderLookup.Provider provider) {
        tag(EntityTypeTags.BOAT).add(TestEntityTypes.DRIFTWOOD_BOAT.getKey());
    }
}
