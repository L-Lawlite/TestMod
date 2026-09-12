package net.lawliet.testmod.gui.screen;

import net.lawliet.testmod.gui.ScreenElement;
import net.lawliet.testmod.gui.menu.TabbedContainerMenu;
import net.lawliet.testmod.gui.render.TabsRender;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jspecify.annotations.Nullable;

public class BaseTabbedScreen<T extends BlockEntity, C extends TabbedContainerMenu<T>> extends AbstractContainerScreen<C> {

    @Nullable
    protected final T blockEntity;
    protected TabsRender tabScreen;

    public BaseTabbedScreen(C menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.blockEntity = menu.getBlockEntity();
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
        this.tabScreen = addRenderableWidget(new TabsRender(this));

    }



    @Nullable
    public T getBlockEntity() {
        return blockEntity;
    }

    protected void drawIcon(GuiGraphicsExtractor graphics, Slot slot, ScreenElement element) {
        element.draw(graphics, slot.x + this.leftPos, slot.y + this.topPos);
    }

    @SuppressWarnings("unused")
    protected void drawIconEmpty(GuiGraphicsExtractor graphics, Slot slot, ScreenElement element) {
        if (slot.hasItem()) {
            return;
        }
        this.drawIcon(graphics, slot, element);
    }


    public void updateDisplay() {
    }

    @Override
    protected boolean hasClickedOutside(double mouseX, double mouseY, int xo, int yo) {
        return super.hasClickedOutside(mouseX, mouseY, xo, yo) && !tabScreen.isMouseOver(xo, yo);
    }

}
