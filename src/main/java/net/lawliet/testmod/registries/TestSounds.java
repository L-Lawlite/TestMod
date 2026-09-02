package net.lawliet.testmod.registries;

import net.lawliet.testmod.TestMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TestSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, TestMod.MODID);

    public static final Supplier<SoundEvent> VALUABLE_FOUND = registerSound("valuable_found");
    public static final Supplier<SoundEvent> VALUABLE_NOT_FOUND = registerSound("valuable_not_found");

    public static final JunkboxMusicDisc BAR_BRAWL = JunkboxMusicDisc.create("bar_brawl");

    private static Supplier<SoundEvent> registerSound(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(TestMod.MODID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    /** For datagen **/
    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        registerMusicDisc(context, TestSounds.BAR_BRAWL, 162, 15);
    }

    private static void registerMusicDisc(BootstrapContext<JukeboxSong> context, TestSounds.JunkboxMusicDisc musicDisc, int lengthInSeconds, int comparatorOutput) {
        registerMusicDisc(context, musicDisc.key(), musicDisc.getHolderReference(), lengthInSeconds, comparatorOutput);
    }

    private static void registerMusicDisc(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox", key.identifier())), lengthInSeconds, comparatorOutput));
    }

    public record JunkboxMusicDisc(DeferredHolder<SoundEvent, SoundEvent> song, ResourceKey<JukeboxSong> key) {
        public static JunkboxMusicDisc create(String name) {
            return new JunkboxMusicDisc(registerJukeboxSong(name), createSong(name));
        }

        private static DeferredHolder<SoundEvent, SoundEvent> registerJukeboxSong(String name) {
            return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(TestMod.MODID, name)));
        }

        private static ResourceKey<JukeboxSong> createSong(String name) {
            return ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(TestMod.MODID, name));
        }

        public Holder.Reference<SoundEvent> getHolderReference() {
            return (Holder.Reference<SoundEvent>) this.song().getDelegate();
        }

    }
}
