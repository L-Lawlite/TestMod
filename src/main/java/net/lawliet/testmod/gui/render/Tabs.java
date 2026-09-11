package net.lawliet.testmod.gui.render;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.InputConstants;
import net.lawliet.testmod.gui.ScreenElement;
import net.lawliet.testmod.gui.screen.BaseTabbedScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;

/**
 * Some code taken from <a href="https://github.com/SlimeKnights/Mantle/blob/1.20/src/main/java/slimeknights/mantle/client/screen/TabsWidget.java">Mantle</a>
 */
public class Tabs {

    private static final Identifier[] UNSELECTED_TABS = new Identifier[]{
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_unselected_1"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_unselected_2"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_unselected_3"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_unselected_4"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_unselected_5"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_unselected_6"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_unselected_7")
    };
    private static final Identifier[] SELECTED_TABS = new Identifier[]{
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_selected_1"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_selected_2"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_selected_3"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_selected_4"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_selected_5"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_selected_6"),
            Identifier.withDefaultNamespace("container/creative_inventory/tab_top_selected_7")
    };

    private final Triple<ScreenElement, ScreenElement, ScreenElement> activeTab;
    private final Triple<ScreenElement, ScreenElement, ScreenElement> inactiveTabs;

    // Offset of tab row
    public int yOffset = 4;
    // space between tabs
    public int spacing = 2;

    public int selectedIndex;
    public int highlightedIndex;
    protected List<ItemStack> icons = Lists.newArrayList();

    public int xPos;
    public int yPos;
    public int height;
    public int width;

    private final BaseTabbedScreen<?, ?> parent;
    private boolean clicked = false;
    private boolean leftMouseDown = false;

    public Tabs(BaseTabbedScreen<?, ?> parent, Triple<ScreenElement, ScreenElement, ScreenElement> inactiveTabs, Triple<ScreenElement, ScreenElement, ScreenElement> activeTabs) {
        this.parent = parent;

        this.inactiveTabs = inactiveTabs;
        this.activeTab = activeTabs;
        this.selectedIndex = 0;
    }

    public void addTab(ItemStack icon) {
        icons.add(icon);
    }

    public void clearIcons() {
        this.selectedIndex = 0;
        icons.clear();
    }

    public void handleMouseClicked(MouseButtonEvent mouseEvent, boolean doubleClick) {
        if (mouseEvent.button() == InputConstants.MOUSE_BUTTON_LEFT) {
            this.leftMouseDown = true;
        }
    }

    public void handleMouseReleased(MouseButtonEvent mouseEvent) {
        this.leftMouseDown = false;
    }

    public void update(int mouseX, int mouseY) {
        mouseX -= this.xPos;
        mouseY -= this.yPos;
        this.highlightedIndex = -1;

        if (mouseY >= 0 && mouseY <= this.inactiveTabs.getMiddle().height()) {
            int x = 0;
            for(int i = 0; i < this.icons.size(); i++) {
                if (mouseX >= x && mouseX < x + this.inactiveTabs.getMiddle().width()) {
                    this.highlightedIndex = i;
                    break;
                }

                x+= (this.inactiveTabs.getMiddle().width() + this.spacing);
            }
        }

        if (this.clicked) {
            if (!this.leftMouseDown) {
                this.clicked = false;
            }
            return;
        } else if (this.leftMouseDown) {
            this.clicked = true;
        } else return;

        if (this.highlightedIndex > -1) {
            this.selectedIndex = this.highlightedIndex;
        }
    }

    public void draw(GuiGraphicsExtractor graphics) {
        int y = this.yPos + this.yOffset;
        for (int i = 0; i < this.icons.size(); i++) {
            int x = this.xPos + i * this.inactiveTabs.getLeft().width();

            if(i > 0) {
                x += i * this.spacing;
            }

            Triple<ScreenElement, ScreenElement, ScreenElement> toDraw = i == selectedIndex ? activeTab : inactiveTabs;
            ScreenElement actualTab;
            if (i == 0 && x == this.parent.getLeftPos()) {
                actualTab = toDraw.getLeft();
            } else if (x == this.parent.getLeftPos() + this.parent.width) {
                actualTab = toDraw.getRight();
            } else  {
                actualTab = toDraw.getMiddle();
            }

            actualTab.draw(graphics, x, y);
            ItemStack icon = icons.get(i);
            if (icon != null) {
                int iconWidth = x + (actualTab.width() - 16) / 2;
                int iconHeight = y + (actualTab.height() - 16) / 2;
                graphics.item(icon, iconWidth, iconHeight);
            }

        }
    }

    public void setPosition(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }


}
