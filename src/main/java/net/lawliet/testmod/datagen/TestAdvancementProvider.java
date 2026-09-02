package net.lawliet.testmod.datagen;

import net.lawliet.testmod.datagen.advacnement.sub_provider.TestAdvancements;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TestAdvancementProvider extends AdvancementProvider {
    public TestAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new TestAdvancements()));
    }


}
