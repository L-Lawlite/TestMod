package net.lawliet.testmod.block.entity;

import net.lawliet.testmod.gui.menu.PedestalMenu;
import net.lawliet.testmod.registries.TestBlockEntities;
import net.lawliet.testmod.registries.TestBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import org.jspecify.annotations.Nullable;

public class PedestalBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(1) {
        @Override
        protected int getCapacity(int index, ItemResource resource) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            PedestalBlockEntity.this.setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(),  getBlockState(), getBlockState(), Block.UPDATE_NEIGHBORS | Block.UPDATE_CLIENTS);
            }
        }
    };

    public PedestalBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(TestBlockEntities.PEDESTAL_BLOCK_ENTITY.get(), worldPosition, blockState);
    }

    public void clearContents() {
        for(int index = 0; index < inventory.size(); ++index) {
            inventory.set(index, ItemResource.EMPTY, 0);
        }
    }


    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.size());
        for (int i = 0; i < inventory.size(); i++) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, i);
            inv.setItem(i, new ItemStack(itemAccess.getResource().getItem(), itemAccess.getAmount()));
        }
        Containers.dropContents(level, worldPosition, inv);
    }

    public boolean isEmpty() {
        for (int index = 0; index < inventory.size(); ++index) {
            if (!inventory.getResource(index).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void insertItem(ItemStack stack) {
        inventory.set(0, ItemResource.of(stack), 1);
        stack.shrink(1);
    }

    public ItemStack getItem() {
        return inventory.getResource(0).toStack();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putChild("inventory", inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        input.child("inventory").ifPresent(inventory::deserialize);
    }

    //START: Block entity sync methods
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    //END: Block entity sync methods

    @Override
    public Component getDisplayName() {
        return Component.translatable(TestBlocks.PEDESTAL.getId().toLanguageKey());
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new PedestalMenu(containerId, inventory, this, this.inventory);
    }
}
