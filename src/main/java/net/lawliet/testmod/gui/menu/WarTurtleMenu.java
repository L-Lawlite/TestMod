package net.lawliet.testmod.gui.menu;

import net.lawliet.testmod.entity.WarTurtleEntity;
import net.lawliet.testmod.registries.gui.TestMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.UUID;

public class WarTurtleMenu extends AbstractContainerMenu {
    private final Container entityContainer;
    public WarTurtleEntity entity;

    public WarTurtleMenu(int containerId, Inventory inventory, Container entityContainer, final WarTurtleEntity entity, int columns) {
        super(TestMenu.WAR_TURTLE_MENU.get(), containerId);
        this.entityContainer = entityContainer;
        this.entity = entity;
        entityContainer.startOpen(inventory.player);

        addSlot(new Slot(entityContainer, 0, 8, 63));
        addSlot(new Slot(entityContainer, 1, 44, 63));
        // Chest slot tier 1
        addSlot(new Slot(entityContainer, 2, 72, 27) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(Items.CHEST);
            }
            @Override
            public boolean mayPickup(Player player) {
                return !entity.hasTierChest(WarTurtleEntity.HAS_TIER_2_CHEST);
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        // Chest slot tier 2
        addSlot(new Slot(entityContainer, 3, 72, 45) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(Items.CHEST) && entity.hasTierChest(WarTurtleEntity.HAS_TIER_1_CHEST);
            }
            @Override
            public boolean mayPickup(Player player) {
                return !entity.hasTierChest(WarTurtleEntity.HAS_TIER_3_CHEST);
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        // Chest slot tier 3
        addSlot(new Slot(entityContainer, 4, 72, 63) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(Items.CHEST) && entity.hasTierChest(WarTurtleEntity.HAS_TIER_2_CHEST);
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });

        if (columns > 0) {
            for (int i = 0; i < columns; i++) {
                addSlot(new Slot(entityContainer, 5 + i, 98 + i * 18, 27) {
                    @Override
                    public boolean isActive() {
                        return entity.hasTierChest(WarTurtleEntity.HAS_TIER_1_CHEST);
                    }
                });
                addSlot(new Slot(entityContainer, 5 + i + columns, 98 + i * 18, 27 + 18) {
                    @Override
                    public boolean isActive() {
                        return entity.hasTierChest(WarTurtleEntity.HAS_TIER_2_CHEST);
                    }
                });
                addSlot(new Slot(entityContainer, 5 + i + 2 * columns, 98 + i * 18, 27 + 2 * 18) {
                    @Override
                    public boolean isActive() {
                        return entity.hasTierChest(WarTurtleEntity.HAS_TIER_3_CHEST);
                    }
                });
            }
        }

        addPlayerInventory(inventory);
        addHotbar(inventory);
    }

    protected void addHotbar(Inventory inventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    protected void addPlayerInventory(Inventory inventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 102 + i * 18 - 18));
            }
        }
    }

    public static WarTurtleMenu create(int i, Inventory inventory, FriendlyByteBuf extraData) {
        UUID uuid = extraData.readUUID();
        List<WarTurtleEntity> turtles = inventory.player.level().getEntitiesOfClass(WarTurtleEntity.class, inventory.player.getBoundingBox().inflate(16), test -> test.getUUID().equals(uuid));
        WarTurtleEntity entity = turtles.isEmpty() ? null : turtles.getFirst();
        return new WarTurtleMenu(i, inventory, new SimpleContainer(28), entity, 4);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            int i = this.entityContainer.getContainerSize() + 1;
            if (index < i) {
                if (!this.moveItemStackTo(itemstack1, i, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).mayPlace(itemstack1) && !this.getSlot(1).hasItem()) {
                if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).mayPlace(itemstack1)) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (i <= 1 || !this.moveItemStackTo(itemstack1, 2, i, false)) {
                int j = i + 27;
                int k = j + 9;
                if (index >= j && index < k) {
                    if (!this.moveItemStackTo(itemstack1, i, j, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < j) {
                    if (!this.moveItemStackTo(itemstack1, j, k, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, j, j, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return !this.entity.hasInventoryChanged(this.entityContainer)
                && this.entityContainer.stillValid(player)
                && this.entity.isAlive()
                && player.isWithinEntityInteractionRange(this.entity, 4);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.entityContainer.stopOpen(player);
    }
}
