package net.lawliet.testmod.item;

import net.lawliet.testmod.tags.TestTags;
import net.minecraft.world.item.ToolMaterial;

public class TestToolMaterials {
    public static final ToolMaterial AZURITE = new ToolMaterial(
            TestTags.Blocks.INCORRECT_FOR_AZURITE_TOOL,
            1200,
            3f,
            3f,
            22,
            TestTags.Items.AZURITE_REPAIRABLE
    );
}
