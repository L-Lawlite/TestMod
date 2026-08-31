package net.lawliet.testmod.datagen;

import net.lawliet.testmod.registries.TestSounds;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.item.JukeboxSong;

public class TestJukeBoxSong {

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        registerMusicDisc(context, TestSounds.BAR_BRAWL, 162, 15);
    }

    private static void registerMusicDisc(BootstrapContext<JukeboxSong> context, TestSounds.JunkboxMusicDisc musicDisc, int lengthInSeconds, int comparatorOutput) {
        registerMusicDisc(context, musicDisc.key(), musicDisc.getHolderReference(), lengthInSeconds, comparatorOutput);
    }

    private static void registerMusicDisc(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox", key.identifier())), lengthInSeconds, comparatorOutput));
    }
}
