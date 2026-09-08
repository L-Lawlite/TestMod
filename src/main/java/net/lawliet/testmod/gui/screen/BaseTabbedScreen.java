package net.lawliet.testmod.gui.screen;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.gui.menu.TabbedContainerMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jspecify.annotations.Nullable;

public class BaseTabbedScreen<T extends BlockEntity, C extends TabbedContainerMenu<T>> extends AbstractContainerScreen<C> {
    public static final Identifier BLANK_BACK = Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/gui/blank.png");
    public static final Identifier BLANK_EXTRA_ROW = Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/gui/blank_extra_row.png");

    @Nullable
    protected final T blockEntity;


    public BaseTabbedScreen(C menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.blockEntity = menu.getBlockEntity();
    }

    @Override
    protected void init() {
        super.init();
        // Add renderable tab screen here
    }

    @Nullable
    public T getBlockEntity() {
        return blockEntity;
    }

    protected void drawIcon() {
    }


    public void updateDisplay() {
    }
}
