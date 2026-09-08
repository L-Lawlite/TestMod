package net.lawliet.testmod.gui.menu;

import com.mojang.logging.LogUtils;
import net.lawliet.testmod.TestMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

/** Many parts taken from <a href="https://github.com/SlimeKnights/Mantle/tree/1.20">Mantle</a>*/
public abstract class BaseContainerMenu<T extends BlockEntity> extends AbstractContainerMenu {
    public static double MAX_DISTANCE = 32D;
    public static int DEFAULT_X_OFFSET = 0;
    public static int DEFAULT_Y_OFFSET = 84;

    private static final Logger LOGGER = LogUtils.getLogger();

    @Nullable
    protected final T blockEntity;

    @Nullable
    protected final Inventory inventory;
    protected int playerInventoryStart = -1;

    protected BaseContainerMenu(@Nullable MenuType<?> menuType, int containerId, @Nullable Inventory inventory,@Nullable T blockEntity) {
        super(menuType, containerId);
        this.blockEntity = blockEntity;
        this.inventory = inventory;
    }

    @Nullable
    public T getBlockEntity() {
        return blockEntity;
    }

    /** Syncs the menu for all players that have opened the menu
     *
     * @param playerOpened {@link ServerPlayer} that opened the container menu
     */
    public void syncOnOpen(ServerPlayer playerOpened) {
        ServerLevel server = playerOpened.level();

        for(Player player: server.players()) {
            if (player == playerOpened) continue;
            if (player.containerMenu instanceof BaseContainerMenu<?> baseMenu) {
                if(this.sameGui(baseMenu)) {
                    this.syncWithOtherContainer(baseMenu, playerOpened);
                    return;
                }
            }
        }

        this.syncNewContainer(playerOpened);
    }

    /**
     *  Called when the container is opened and no other player already has a container for this {@link BlockEntity} open.
     *  Set default state here.
     * @param playerOpened {@link ServerPlayer} that opened the container menu
     */
    protected abstract void syncNewContainer(ServerPlayer playerOpened);

    /**
     *  Called when the container is opened and another player already has a container for this {@link BlockEntity} open.
     *  Add code to sync the same state here.
     * @param otherContainer other container
     * @param playerOpened {@link ServerPlayer} that opened the container menu
     */
    protected abstract void syncWithOtherContainer(BaseContainerMenu<?> otherContainer, ServerPlayer playerOpened);

    /**
     *  Checks if {@link BlockEntity} are same
     * @param otherContainer other container
     */
    public boolean sameGui(BaseContainerMenu<?> otherContainer) {
        if (this.blockEntity == null) {
            return false;
        }
        return this.blockEntity == otherContainer.blockEntity;
    }


    /**
     * Call this to add the player's inventory to the GUI
     */
    protected void addInventorySlots() {
        if (this.inventory != null) {
            this.addInventorySlots(this.inventory);
        }
    }

    protected void addInventorySlots(Inventory inventory) {
        int xOffset = this.getInventoryXOffset();
        int yOffset = this.getInventoryYOffset();

        int start = this.slots.size();
        for(int slotY = 0; slotY < 3; slotY++) {
            for(int slotX = 0; slotX < 9; slotX++) {
                addSlot(new Slot(inventory, slotX + slotY * 9 + 9, xOffset + slotX * 18, yOffset + slotY * 18));
            }
        }

        yOffset += 58;
        for(int slotY = 0; slotY < 9; slotY++) {
            addSlot(new Slot(inventory, slotY, xOffset + slotY * 18, yOffset));
        }

        this.playerInventoryStart = start;
    }

    /**
     * Override this if you want to set y offset for inventory slots.
     */
    protected int getInventoryYOffset() {
        return DEFAULT_Y_OFFSET;
    }

    /**
     * Override this if you want to set x offset for inventory slots.
     */
    protected int getInventoryXOffset() {
        return DEFAULT_X_OFFSET;
    }



    @Override
    protected Slot addSlot(Slot slot) {
        if (this.playerInventoryStart >= 0) {
            throw new IllegalStateException("BaseContainer: Player inventory has to be last slots. Add all slots before adding the player inventory.");
        }
        return super.addSlot(slot);
    }


    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    protected static final int HOTBAR_SLOT_COUNT = 9;
    protected static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    protected static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    protected static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    protected static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    protected static final int VANILLA_FIRST_SLOT_INDEX = 0;
    protected static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    public void setExtraInventorySlotCount(int extraInventorySlotCount) {
        this.ExtraInventorySlotCount = extraInventorySlotCount;
    }

    public int getExtraInventorySlotCount() {
        return ExtraInventorySlotCount;
    }

    private int ExtraInventorySlotCount;
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        if (ExtraInventorySlotCount < 0) {
            throw new IllegalStateException("setExtraInventorySlotCount must be called from the inherited class for quickstack");
        }

        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + ExtraInventorySlotCount, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + ExtraInventorySlotCount) {
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
        if (blockEntity == null) {
            return false;
        }
        return stillValid(ContainerLevelAccess.create(player.level(), blockEntity.getBlockPos()), player, blockEntity.getBlockState().getBlock());
    }

    /**
     * Gets a tile entity from a packet buffer for the client menu.
     * @param buf     Packet buffer instance
     * @param type    Block entity class
     * @param <T>  BlockEntity Type
     * @return Block entity, or null if unable to find
     */
    @Nullable
    public static <T extends BlockEntity> T getBlockEntityFromBuf(@Nullable Level level, @Nullable FriendlyByteBuf buf, Class<T> type) {
        if (buf == null || level == null) {
            return null;
        }

        // Check loaded
        BlockPos blockPos = buf.readBlockPos();
        if (!level.isLoaded(blockPos)) {
            LOGGER.error("Menu attempted to load BlockEntity of type {} from unloaded position {}", type, blockPos);
            return null;
        }

        // check if block entity exist
        BlockEntity be = level.getBlockEntity(blockPos);
        if (be == null) {
            LOGGER.error("Menu failed to find BlockEntity of type {} at {}", type, blockPos);
            return null;
        }

        // check type
        if (!type.isInstance(be)) {
            LOGGER.error("Menu found unexpected BlockEntity class at {}: expected {}, but found {}", blockPos, type, be.getClass());
            return null;
        }
        return type.cast(be);
    }
}
