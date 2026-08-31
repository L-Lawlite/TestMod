package net.lawliet.testmod.datagen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.registries.TestSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

import java.util.Arrays;

public class TestSoundsProvider extends SoundDefinitionsProvider {
    public TestSoundsProvider(PackOutput output) {
        super(output, TestMod.MODID);
    }

    @Override
    public void registerSounds() {
        addSound(TestSounds.VALUABLE_FOUND.get(), "valuable_found");
        addSound(TestSounds.VALUABLE_NOT_FOUND.get(), "valuable_not_found");
    }

    private void addSound(SoundEvent soundEvent, Identifier... identifiers) {
        add(soundEvent, definition()
                .subtitle(String.format("sounds.%s.%s",TestMod.MODID, soundEvent.location().getPath()))
                .with(Arrays.stream(identifiers).map(SoundDefinitionsProvider::sound).toArray(SoundDefinition.Sound[]::new))
        );
    }

    private void addSound(SoundEvent soundEvent, String... names) {
        addSound(soundEvent, Arrays.stream(names).map((name) -> Identifier.fromNamespaceAndPath(TestMod.MODID, name)).toArray(Identifier[]::new));
    }
}
