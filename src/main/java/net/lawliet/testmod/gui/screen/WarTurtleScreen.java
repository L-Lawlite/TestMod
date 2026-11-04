package net.lawliet.testmod.gui.screen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.entity.WarTurtleEntity;
import net.lawliet.testmod.gui.menu.WarTurtleMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class WarTurtleScreen extends AbstractContainerScreen<WarTurtleMenu> {
    public static final Identifier GUI_TEXTURE_T0 = Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/entity/war_turtle/gui/war_turtle_gui_tier0.png");
    public static final Identifier GUI_TEXTURE_T1 = Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/entity/war_turtle/gui/war_turtle_gui_tier1.png");
    public static final Identifier GUI_TEXTURE_T2 = Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/entity/war_turtle/gui/war_turtle_gui_tier2.png");
    public static final Identifier GUI_TEXTURE_T3 = Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/entity/war_turtle/gui/war_turtle_gui_tier3.png");

    private final WarTurtleEntity warTurtle;

    public WarTurtleScreen(WarTurtleMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.warTurtle = menu.entity;
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = 72;
        titleLabelY = 12;

        inventoryLabelX = 10000;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if (warTurtle.hasTierChest(WarTurtleEntity.HAS_TIER_3_CHEST)) {
            renderBackground(graphics, x, y, GUI_TEXTURE_T3);
        } else if (warTurtle.hasTierChest(WarTurtleEntity.HAS_TIER_2_CHEST)) {
            renderBackground(graphics, x, y, GUI_TEXTURE_T2);
        } else if (warTurtle.hasTierChest(WarTurtleEntity.HAS_TIER_1_CHEST)) {
            renderBackground(graphics, x, y, GUI_TEXTURE_T1);
        } else {
            renderBackground(graphics, x, y, GUI_TEXTURE_T0);
        }

        InventoryScreen.extractEntityInInventoryFollowsMouse(graphics, x + 8, y + 9, x + 60, y + 58, 20, 0.05f, mouseX, mouseY, this.warTurtle);

    }

    private void renderBackground(GuiGraphicsExtractor graphics, int x, int y, Identifier identifier) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, identifier, x, y, 0, 0, imageWidth, imageHeight, 256, 256);

    }
}
