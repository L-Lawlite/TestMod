package net.lawliet.testmod.gui.render;

import com.google.common.collect.Lists;
import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.gui.ScreenElement;
import net.lawliet.testmod.gui.inferface.ITabbedBlock;
import net.lawliet.testmod.gui.menu.TabbedContainerMenu;
import net.lawliet.testmod.gui.screen.BaseTabbedScreen;
import net.lawliet.testmod.networking.packet.StationTabPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;


/**
 * Lot of code taken from <a href="https://github.com/SlimeKnights/TinkersConstruct/blob/1.20.1/src/main/java/slimeknights/tconstruct/tables/client/inventory/widget/TinkerTabsWidget.java">Tinker's Construct</a>
 */
public class TabsRender implements Renderable, GuiEventListener, NarratableEntry {
    private final int leftPos;
    private final int topPos;
    private final int imageWidth;
    private final int imageHeight;

    private static final Identifier BLANK_IDENTIFIER = Identifier.fromNamespaceAndPath(TestMod.MODID, "textures/gui/blank.png");
    private static final int TAB_HEIGHT = 16;
    private static final int TAB_WIDTH = 16;
    private static final ScreenElement BLANK_TAB = ScreenElement.create(BLANK_IDENTIFIER, 0, 0, TAB_WIDTH, TAB_HEIGHT, 8, 8);
    private static final ScreenElement ACTIVE_TAB_LEFT = ScreenElement.create(BLANK_IDENTIFIER, 0, 0, TAB_WIDTH, TAB_HEIGHT, 8, 8);
    private static final ScreenElement ACTIVE_TAB_CENTRE = ScreenElement.create(BLANK_IDENTIFIER, 0, 0, TAB_WIDTH, TAB_HEIGHT, 8, 8);
    private static final ScreenElement ACTIVE_TAB_RIGHT = ScreenElement.create(BLANK_IDENTIFIER, 0, 0, TAB_WIDTH, TAB_HEIGHT, 8, 8);

    private final Tabs tabs;
    private final List<BlockPos> tabData;
    private final BaseTabbedScreen<?, ?> parent;

    public TabsRender(BaseTabbedScreen<?, ?> parent) {
        this.parent = parent;

        var tabs = collectTabs(this.parent.getMinecraft(), this.parent.getMenu());
        this.tabs = new Tabs(parent, Triple.of(BLANK_TAB, BLANK_TAB, BLANK_TAB), Triple.of(ACTIVE_TAB_LEFT, ACTIVE_TAB_CENTRE, ACTIVE_TAB_RIGHT));
        int tabCount = tabs.size();
        this.imageWidth = tabCount * ACTIVE_TAB_CENTRE.width() + (tabCount - 1) * this.tabs.spacing;
        this.imageHeight = ACTIVE_TAB_CENTRE.height();

        this.leftPos = parent.getLeftPos() + 4;
        this.topPos = parent.getTopPos() - this.imageHeight;

        this.tabs.setPosition(leftPos, topPos);
        this.tabs.setSize(TAB_WIDTH, TAB_HEIGHT);

        tabs.stream().map(Pair::getLeft).forEach(this.tabs::addTab);
        tabData = tabs.stream().map(Pair::getRight).toList();

        BlockEntity blockEntity = this.parent.getBlockEntity();
        if  (blockEntity != null) {
            selectTabForPos(blockEntity.getBlockPos());
        }
    }

    private void selectTabForPos(BlockPos pos) {
        for (int i = 0; i < tabData.size(); i++) {
            if (tabData.get(i).equals(pos)) {
                this.tabs.selectedIndex = i;
                return;
            }
        }
    }

    private void onNewTabSelection(BlockPos pos) {
        Level level = this.parent.getMinecraft().level;

        if (level != null) {
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof ITabbedBlock) {
                // send network packet
                ClientPacketDistributor.sendToServer(new StationTabPacket(pos));

                //sound
                this.parent.getMinecraft().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1f));
            }
        }
    }

    public int guiRight() {
        return this.leftPos + this.imageWidth;
    }

    public int guiBottom() {
        return this.topPos + this.imageHeight;
    }

    @SuppressWarnings("unused")
    public Rect2i getArea() {
        return new Rect2i(this.leftPos, this.topPos, this.imageWidth, this.imageHeight);
    }



    private static List<Pair<ItemStack, BlockPos>> collectTabs(Minecraft minecraft, TabbedContainerMenu<?> menu) {
        List<Pair<ItemStack, BlockPos>> tabs = Lists.newArrayList();
        Level level = minecraft.level;

        if (level != null) {
            for (Pair<BlockPos, BlockState> pair: menu.stationBlocks) {
                BlockPos pos = pair.getLeft();
                BlockState state = pair.getRight();
                ItemStack stack = state.getBlock().getCloneItemStack(level, pos, state, false, minecraft.player);
                tabs.add(Pair.of(stack, pos));
            }
        }
        return tabs;
    }


    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        int sel = this.tabs.selectedIndex;
        this.tabs.update(mouseX, mouseY);
        this.tabs.draw(graphics);

        if (sel != this.tabs.selectedIndex) {
            if (0 <= this.tabs.selectedIndex && this.tabs.selectedIndex < this.tabData.size()) {
                onNewTabSelection(this.tabData.get(this.tabs.selectedIndex));
            }
        }

        renderToolTip(graphics, mouseX, mouseY);
    }

    private void renderToolTip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        //highlight tooltip
        Level level = this.parent.getMinecraft().level;
        if (level != null && this.tabs.highlightedIndex > -1) {
            BlockPos pos = this.tabData.get(this.tabs.highlightedIndex);
            BlockEntity te = level.getBlockEntity(pos);
            Component title = te instanceof MenuProvider ? ((MenuProvider) te).getDisplayName() : level.getBlockState(pos).getBlock().getName();
            graphics.setComponentTooltipForNextFrame(parent.getFont(), List.of(title), mouseX, mouseY);
        }

    }

    @Override
    public void setFocused(boolean focused) {
        // Focus Logic if any
    }

    @Override
    public boolean isFocused() {
        return false;
    }

    @Override
    public NarrationPriority narrationPriority() {
        return NarrationPriority.NONE;
    }

    @Override
    public void updateNarration(NarrationElementOutput output) {

    }



    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= (this.leftPos - 1) && mouseX < (this.guiRight() + 1) && mouseY >= (this.topPos - 1) && mouseY < (this.guiBottom() + 1);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (isMouseOver(event.x(), event.y())) {
            this.tabs.handleMouseClicked(event, doubleClick);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        this.tabs.handleMouseReleased(event);
        return true;
    }



}
