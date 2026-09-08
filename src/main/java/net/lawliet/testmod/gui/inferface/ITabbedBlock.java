package net.lawliet.testmod.gui.inferface;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Blocks implementing this are part of the tabbed GUI system
 */
public interface ITabbedBlock {

    /**
     * Called when the block is activated to open the UI. Override to return false for blocks with no inventory
     * @param player Player instance
     * @param level  World instance
     * @param pos    Block position
     * @return true if the GUI opened, false if not
     */
    boolean openGui(Player player, Level level, BlockPos pos);
}
