package net.lawliet.testmod.gui.menu;

import net.lawliet.testmod.block.entity.CrystallizerBlockEntity;
import net.lawliet.testmod.registries.gui.TestMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;

public class CrystallizerMenu extends BaseContainerMenu<CrystallizerBlockEntity> {
    private final ContainerData data;

    public static final int CRYSTAL_PIXEL_SIZE = 16;
    public static final int ARROW_PIXEL_SIZE = 24;

    private static final int EXTRA_SLOTS = 2;

    public CrystallizerMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ItemStacksResourceHandler handler, ContainerData data) {
        super(TestMenu.CRYSTALLIZER_MENU.get(), containerId, inventory,(CrystallizerBlockEntity) blockEntity);
        this.setExtraInventorySlotCount(EXTRA_SLOTS);
        this.data = data;

        addInventorySlots(inventory);

        this.addSlot(new ResourceHandlerSlot(handler, handler::set, CrystallizerBlockEntity.INPUT_SLOT, 54, 34));
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, CrystallizerBlockEntity.OUTPUT_SLOT, 104, 34) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addDataSlots(data);
    }


    public CrystallizerMenu(int containerId, Inventory inventory, RegistryFriendlyByteBuf extraData) {
        this(containerId, inventory, getBlockEntityFromBuf(inventory.player.level(), extraData, CrystallizerBlockEntity.class), new ItemStacksResourceHandler(EXTRA_SLOTS), new SimpleContainerData(EXTRA_SLOTS));
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaleArrowProgress() {
        return getScaleProgress(ARROW_PIXEL_SIZE);
    }

    public int getScaleCrystalProgress() {
        return getScaleProgress(CRYSTAL_PIXEL_SIZE);
    }

    private int getScaleProgress(int pixelSize) {
        int progress = data.get(0);
        int maxProgress = data.get(1);
        return maxProgress != 0 && progress != 0 ? progress * pixelSize / maxProgress : 0;
    }
}
