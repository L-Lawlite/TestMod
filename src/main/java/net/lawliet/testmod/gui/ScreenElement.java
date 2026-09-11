package net.lawliet.testmod.gui;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public record ScreenElement(Identifier texture, int x, int y, int width, int height, int imageWidth, int imageHeight){

    public static ScreenElement create(Identifier texture, int x, int y, int width, int height, int imageWidth, int imageHeight){
        return new ScreenElement(texture, x, y, width, height, imageWidth, imageHeight);
    }

    public static ScreenElement create(Identifier texture, int x, int y, int width, int height){
        return create(texture, x, y, width, height, 256, 256);
    }

    public ScreenElement move(int x, int y, int width, int height) {
        return new ScreenElement(texture, x, y, width, height, imageWidth, imageHeight);
    }

    @SuppressWarnings("unused")
    public ScreenElement shift(int dx, int dy) {
        return move(x + dx, y + dy, width, height);
    }

    public void draw(GuiGraphicsExtractor graphics, int xPos, int yPos){
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture,  xPos, yPos, x, y,  width, height, imageWidth, imageHeight);
    }
}
