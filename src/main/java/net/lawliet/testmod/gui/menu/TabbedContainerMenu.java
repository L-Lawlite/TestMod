package net.lawliet.testmod.gui.menu;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ibm.icu.impl.Pair;
import net.lawliet.testmod.gui.inferface.ITabbedBlock;
import net.lawliet.testmod.gui.screen.BaseTabbedScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import java.util.*;

/** Lot of code taken from <a href="https://github.com/SlimeKnights/TinkersConstruct/blob/1.20.1/src/main/java/slimeknights/tconstruct/tables/menu/TabbedContainerMenu.java">Tinker's Constuct</a>
 *
 * @param <T> Any entity that extends {@link BlockEntity}
 */
public class TabbedContainerMenu<T extends BlockEntity> extends BaseContainerMenu<T>{
    private static final BlockComparator COMPARATOR = new BlockComparator();
    public final List<Pair<BlockPos, BlockState>> stationBlocks;


    private final int DEFAULT_SEARCH_SIZE = 6;

    /**
     * Must provide {@link #setExtraInventorySlotCount(int)}
     */
    protected TabbedContainerMenu(@Nullable MenuType<?> menuType, int containerId, @Nullable Inventory inventory, @Nullable T blockEntity) {
        super(menuType, containerId, inventory, blockEntity);

        this.stationBlocks = Lists.newLinkedList();

        if (blockEntity != null && blockEntity.hasLevel()) {
            this.detectStationParts(blockEntity.getLevel(), blockEntity.getBlockPos());
        }
    }

    /**
     *  Detects the given station parts nearby the given position
     * @param level {@link Level}
     * @param blockPos start of search position {@link BlockPos} for the block entity {@link T}
     */
    public void detectStationParts(Level level, BlockPos blockPos) {

        //BFS
        Set<BlockPos> visited = Sets.newHashSet();
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(blockPos);

        while (!queue.isEmpty()) {
            BlockPos pos = queue.poll();
            if (visited.contains(pos)) {
                continue;
            }
            BlockState state = level.getBlockState(pos);
            if (!(state.getBlock() instanceof ITabbedBlock)) {
                // not valid for us
                continue;
            }

            for (Direction direction : Direction.values()) {
                BlockPos neighborPos = pos.relative(direction);
                if (!visited.contains(neighborPos)) {
                    queue.add(neighborPos);
                }
            }

            visited.add(pos);
            this.stationBlocks.add(Pair.of(pos, state));
            if (stationBlocks.size() > this.getSearchSize()) {
                break;
            }

            this.stationBlocks.sort(COMPARATOR);
        }
    }

    /**
     * Override it if you want to change the search size
     * @return {@link #DEFAULT_SEARCH_SIZE} by default
     */
    protected int getSearchSize() {
        return DEFAULT_SEARCH_SIZE;
    }

    /**
     * Sends update to client current screen
     */
    public void updateScreen() {
        if (this.blockEntity != null && this.blockEntity.hasLevel()) {
            Level level = this.blockEntity.getLevel();
            assert level != null;
            if (level.isClientSide()) {
                Screen screen = Minecraft.getInstance().gui.screen();
                if (screen instanceof BaseTabbedScreen<?, ?> baseTabbedScreen) {
                    baseTabbedScreen.updateDisplay();
                }
            }
        }
    }

    /**
     * Logic for comparing {@link BlockPos} and {@link BlockState}
     */
    private static class BlockComparator implements Comparator<Pair<BlockPos, BlockState>> {

        /**
         * Priority Order Distance, Y position, X position, Z position
         */
        @Override
        public int compare(Pair<BlockPos, BlockState> o1, Pair<BlockPos, BlockState> o2) {
            BlockPos pos1 = o1.first;
            BlockPos pos2 = o2.first;
            int sum1 = pos1.getX() + pos1.getY() + pos1.getZ();
            int sum2 = pos2.getX() + pos2.getY() + pos2.getZ();
            if (sum1 != sum2) {
                return Integer.compare(sum1, sum2);
            }
            if (pos1.getY() != pos2.getY()) {
                return Integer.compare(pos1.getY(), pos2.getY());
            }
            if (pos1.getX() != pos2.getX()) {
                return Integer.compare(pos1.getX(), pos2.getX());
            }
            return Integer.compare(pos1.getZ(), pos2.getZ());
        }
    }
}
