package net.lawliet.testmod.gui.screen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.gui.menu.CrystallizerMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class CrystallizerScreen extends AbstractContainerScreen<CrystallizerMenu> {
    private static final Identifier GUI_TEXTURE = TestMod.createIdentifier("textures/gui/crystallizer/crystallizer_gui.png");
    private static final Identifier ARROW_TEXTURE = TestMod.createIdentifier("textures/gui/crystallizer/arrow_progress.png");
    private static final Identifier CRYSTAL_TEXTURE = Identifier.withDefaultNamespace("textures/block/amethyst_cluster.png");


    public CrystallizerScreen(CrystallizerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        if (menu.isCrafting()) {
            renderProgressArrow(graphics, x, y);
            renderProgressCrystal(graphics, x, y);
        }
    }

    private void renderProgressArrow(GuiGraphicsExtractor graphics, int x, int y) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 73, y + 35, 0, 0, menu.getScaleArrowProgress(), 16, 24, 16);
    }

    private void renderProgressCrystal(GuiGraphicsExtractor graphics, int x, int y) {
        int crystalProgress = menu.getScaleCrystalProgress();
        graphics.blit(RenderPipelines.GUI_TEXTURED, CRYSTAL_TEXTURE, x + 104, y + 13 + CrystallizerMenu.CRYSTAL_PIXEL_SIZE - crystalProgress, 0, CrystallizerMenu.CRYSTAL_PIXEL_SIZE - crystalProgress, 16, crystalProgress, 16, 16);
    }
}
