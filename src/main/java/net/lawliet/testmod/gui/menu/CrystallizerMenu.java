package net.lawliet.testmod.gui.menu;

import net.lawliet.testmod.TestMod;
import net.lawliet.testmod.block.entity.CrystallizerBlockEntity;
import net.lawliet.testmod.registries.gui.TestMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;

public class CrystallizerMenu extends AbstractAdvancedContainerMenu {
    public final CrystallizerBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public static final int CRYSTAL_PIXEL_SIZE = 16;
    public static final int ARROW_PIXEL_SIZE = 24;


    public CrystallizerMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new ItemStacksResourceHandler(2), new SimpleContainerData(2));
    }

    public CrystallizerMenu(int containerId, Inventory inv, BlockEntity blockEntity, ItemStacksResourceHandler handler, ContainerData data) {
        super(TestMenu.CRYSTALLIZER_MENU.get(), containerId, inv);
        this.blockEntity = (CrystallizerBlockEntity) blockEntity;
        this.level = inv.player.level();
        this.data = data;

        this.addSlot(new ResourceHandlerSlot(handler, handler::set, CrystallizerBlockEntity.INPUT_SLOT, 54, 34));
        this.addSlot(new ResourceHandlerSlot(handler, handler::set, CrystallizerBlockEntity.OUTPUT_SLOT, 104, 34){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addDataSlots(data);
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

    // THIS YOU HAVE TO DEFINE!
    protected static final int TE_INVENTORY_SLOT_COUNT = 2;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            TestMod.LOGGER.error("Invalid slotIndex:{}", pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, blockEntity.getBlockState().getBlock());
    }
}
