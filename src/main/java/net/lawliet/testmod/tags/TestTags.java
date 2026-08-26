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
        public static final TagKey<Block> NEED_AZURITE_TOOLS = createTag("need_azurite_tools");
        public static final TagKey<Block> INCORRECT_FOR_AZURITE_TOOL = createTag("incorrect_for_azurite_tool");


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(TestMod.MODID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> AZURITE_ORES = createTag("azurite_ores");
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> AZURITE_TOOL_MATERIAL = createTag("azurite_tool_material");
        public static final TagKey<Item> AZURITE_REPAIRABLE = createTag("azurite_repairable");


        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(TestMod.MODID, name));
        }
    }
}
