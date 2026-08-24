package net.lawliet.testmod.tags;

import net.lawliet.testmod.TestMod;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TestTags {
    public static class Blocks {
        public static final TagKey<Block> METAL_DETECTABLE = createTag("metal_detectable");
        public static final TagKey<Block> AZURITE_ORES = createTag("azurite_ores");


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(TestMod.MODID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> AZURITE_ORES = createTag("azurite_ores");



        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(TestMod.MODID, name));
        }
    }
}
