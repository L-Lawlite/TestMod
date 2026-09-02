package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.datagen.painting.TestPainting;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

import java.util.concurrent.CompletableFuture;

public class TestPaintingTagsProvider extends PaintingVariantTagsProvider {
    public TestPaintingTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TestMod.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(PaintingVariantTags.PLACEABLE)
                .add(keyIdentifier(TestPainting.SAW_THEM))
                .add(keyIdentifier(TestPainting.SHRIMP))
                .add(keyIdentifier(TestPainting.WORLD));
    }

    private TagEntry keyIdentifier(ResourceKey<PaintingVariant> key) {
        return TagEntry.optionalElement(key.identifier());
    }
}
