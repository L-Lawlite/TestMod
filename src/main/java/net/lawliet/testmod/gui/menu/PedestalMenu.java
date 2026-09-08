package net.lawliet.testmod.gui.menu;

import net.lawliet.testmod.block.entity.PedestalBlockEntity;
import net.lawliet.testmod.registries.gui.TestMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;

public class PedestalMenu extends BaseContainerMenu<PedestalBlockEntity> {
    public PedestalMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new ItemStacksResourceHandler(1));
    }

    public PedestalMenu(int containerId, Inventory inv, BlockEntity blockEntity, ItemStacksResourceHandler handler) {
        super(TestMenu.PEDESTAL_MENU.get(), containerId, inv, (PedestalBlockEntity) blockEntity);

        addInventorySlots();

        setExtraInventorySlotCount(1);

        addSlot(new ResourceHandlerSlot(handler, handler::set, 0, 80, 35) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
    }
}
