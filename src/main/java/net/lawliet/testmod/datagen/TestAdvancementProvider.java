package net.lawliet.testmod.datagen;

import net.lawliet.testmod.datagen.advacnement.sub_provider.TestAdvancements;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.worldgen.BootstrapContext;

import java.util.List;

public class TestAdvancementProvider extends AdvancementProvider {
        public TestAdvancementProvider(BootstrapContext<Advancement> context) {
        super(List.of(TestAdvancements::new));
    }


}
