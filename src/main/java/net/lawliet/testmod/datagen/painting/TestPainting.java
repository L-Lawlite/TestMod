package net.lawliet.testmod.datagen.painting;

import net.lawliet.testmod.TestMod;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

import java.util.Optional;

public class TestPainting {

    public static final ResourceKey<PaintingVariant> SAW_THEM = create("saw_them");
    public static final ResourceKey<PaintingVariant> SHRIMP = create("shrimp");
    public static final ResourceKey<PaintingVariant> WORLD = create("world");
    public static final ResourceKey<PaintingVariant> WANDERER = create("wanderer");

    /** For datagen **/
    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, SAW_THEM, 2, 2, true);
        register(context, SHRIMP, 2, 1, true);
        register(context, WORLD, 2, 2, true);
        register(context, WANDERER, 1, 2, true);
    }

    protected static void register(final BootstrapContext<PaintingVariant> context, final ResourceKey<PaintingVariant> key, final int width, final int height, final boolean hasAuthor) {
        context.register(key, new PaintingVariant(width, height, key.identifier(),
                Optional.of(Component.translatable(key.identifier().toLanguageKey("painting", "title")).withStyle(ChatFormatting.YELLOW)),
                hasAuthor ? Optional.of(Component.translatable(key.identifier().toLanguageKey("painting", "author")).withStyle(ChatFormatting.GRAY)) : Optional.empty()
        ));
    }

    protected static ResourceKey<PaintingVariant> create(final String id) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, Identifier.fromNamespaceAndPath(TestMod.MODID, id));
    }
}
