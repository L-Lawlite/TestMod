package net.lawliet.testmod.gui.menu;

import net.lawliet.testmod.entity.WarTurtleEntity;
import net.lawliet.testmod.registries.gui.TestMenu;
import net.lawliet.testmod.tags.TestTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ArmorSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class WarTurtleMenu extends AbstractContainerMenu {
    private final Container entityContainer;
    public WarTurtleEntity entity;

    private static final int ARMOR_SLOT = 1;
    private static final int MAX_ROWS = 3;
    private static final int SLOTS_PER_ROW = 4;
    private static final Identifier ARMOR_SLOT_SPRITE = Identifier.withDefaultNamespace("container/slot/horse_armor");

    public static WarTurtleMenu create(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
            Level level = inventory.player.level();
            Entity entity = level.getEntity(extraData.getInt(0));
            WarTurtleEntity warTurtleEntity = entity != null && entity instanceof WarTurtleEntity warTurtle? warTurtle : null;
            return new WarTurtleMenu(containerId, inventory, new SimpleContainer(5 + MAX_ROWS * SLOTS_PER_ROW), warTurtleEntity, SLOTS_PER_ROW);
    }

    public WarTurtleMenu(int containerId, Inventory inventory, Container entityContainer, final WarTurtleEntity entity, int columns) {
        super(TestMenu.WAR_TURTLE_MENU.get(), containerId);
        this.entityContainer = entityContainer;
        this.entity = entity;
        entityContainer.startOpen(inventory.player);

        addSlot(new ArmorSlot(entityContainer, entity, EquipmentSlot.BODY, 0, 8, 63, ARMOR_SLOT_SPRITE) {
            @Override
            public boolean isActive() {
                return entity.canUseSlot(EquipmentSlot.BODY) && entity.is(TestTags.EntityTypes.CAN_WEAR_TURTLE_ARMOR);
            }
        });
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
            }
            for (int i = 0; i < columns; i++) {
                addSlot(new Slot(entityContainer, 5 + i + columns, 98 + i * 18, 27 + 18) {
                    @Override
                    public boolean isActive() {
                        return entity.hasTierChest(WarTurtleEntity.HAS_TIER_2_CHEST);
                    }
                });
            }
            for (int i = 0; i < columns; i++) {
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

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();
            int playerContainerStart = this.entityContainer.getContainerSize() + 1;
            int extraInventoryEnd = this.extraInventoryRows() * 4 + 5;
            if (index < playerContainerStart) {
                if (!this.moveItemStackTo(stack, playerContainerStart, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).mayPlace(stack) && !this.getSlot(1).hasItem()) {
                if (!this.moveItemStackTo(stack, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).mayPlace(stack) && !this.getSlot(0).hasItem()) {
                if (!this.moveItemStackTo(stack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (playerContainerStart <= 1 || !this.moveItemStackTo(stack, 2, extraInventoryEnd, false)) {
                int playerContainerEnd = playerContainerStart + 27;
                int playerHotBarEnd = playerContainerEnd + 9;
                if (index >= playerContainerEnd && index < playerHotBarEnd) {
                    if (!this.moveItemStackTo(stack, playerContainerStart, playerContainerEnd, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < playerContainerEnd) {
                    if (!this.moveItemStackTo(stack, playerContainerEnd, playerHotBarEnd, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(stack, playerContainerEnd, playerContainerEnd, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return clicked;
    }

    private int extraInventoryRows() {
        if (this.entity.hasTierChest(WarTurtleEntity.HAS_TIER_3_CHEST)) {
            return 3 ;
        } else if (this.entity.hasTierChest(WarTurtleEntity.HAS_TIER_2_CHEST)) {
            return 2;
        } else if (this.entity.hasTierChest(WarTurtleEntity.HAS_TIER_1_CHEST)) {
            return 1;
        }
        return 0;
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
