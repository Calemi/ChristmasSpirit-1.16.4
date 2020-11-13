package com.tm.cspirit.inventory;

import com.tm.cspirit.tileentity.base.CSItemHandler;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class ContainerBase extends Container {

    protected final PlayerInventory playerInventory;
    public final TileEntityInventoryBase tileEntity;
    protected int size;

    protected boolean isItemContainer;

    protected ContainerBase (ContainerType<?> type, int windowId, PlayerInventory playerInventory, TileEntityInventoryBase tileEntity, int x, int y) {
        super(type, windowId);

        this.playerInventory = playerInventory;
        this.tileEntity = tileEntity;

        addPlayerInv(x, y);
        addPlayerHotbar(x, y + 58);
    }

    /**
     * Gets the connected TileEntity.
     * Throws IllegalStateException if not found.
     */
    protected static TileEntityInventoryBase getTileEntity (final PlayerInventory playerInventory, final PacketBuffer data) {

        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");

        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());

        if (tileAtPos instanceof TileEntityInventoryBase) {
            return (TileEntityInventoryBase) tileAtPos;
        }

        throw new IllegalStateException("Tile entity is not correct!" + tileAtPos);
    }

    /**
     * Gets the new inventory's slot amount.
     */
    private int getTileEntitySlotAmount () {
        return isItemContainer ? size : tileEntity.getSizeInventory();
    }

    /**
     * Used to add the Player's inventory.
     */
    protected void addPlayerInv (int x, int y) {
        addStorageInv(playerInventory, 9, x, y, 3);
    }

    /**
     * Used to add the Player's hotbar.
     */
    protected void addPlayerHotbar (int x, int y) {
        addStorageInv(playerInventory, 0, x, y, 1);
    }

    /**
     * Used to by addPlayerInv & addPlayerHotbar to add the appropriate slots.
     */
    private void addStorageInv (IInventory inv, int idOffset, int x, int y, int height) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inv, j + i * 9 + idOffset, x + (j * 18), y + (i * 18)));
            }
        }
    }

    /**
     * Used to add the storage slots of a Tile Entity.
     */
    protected void addTileEntityStorageInv (CSItemHandler inv, int idOffset, int x, int y, int height) {

        int id = idOffset;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new SlotItemHandler(inv, id, x + (j * 18), y + (i * 18)));
                id++;
            }
        }
    }

    /**
     * Handles shift-clicking items from slot to slot.
     */
    @Override
    public ItemStack transferStackInSlot (PlayerEntity playerIn, int index) {

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        //Checks if the slot exists and it has an Item in it.
        if (slot != null && slot.getHasStack()) {

            ItemStack itemStack1 = slot.getStack();
            itemstack = itemStack1.copy();

            //Checks if the new inventory has slots.
            if (getTileEntitySlotAmount() > 0) {

                //Transfers: Player's inventory -> New inventory.
                if (index <= 35) {

                    if (mergeIfPossible(slot, itemStack1, itemstack, 36, 36 + getTileEntitySlotAmount())) {

                        if (mergeInvHotbarIfPossible(slot, itemStack1, itemstack, index)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }

                //Transfers: New inventory -> Player's inventory.
                else {

                    if (mergeIfPossible(slot, itemStack1, itemstack, 0, 35)) {
                        return ItemStack.EMPTY;
                    }

                    slot.onSlotChange(itemStack1, itemstack);
                }
            }

            else {

                if (mergeInvHotbarIfPossible(slot, itemStack1, itemstack, index)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemStack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            }

            else slot.onSlotChanged();

            if (itemStack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerInventory.player, itemStack1);
        }

        return itemstack;
    }

    /**
     * Checks if the given merge is possible.
     */
    private boolean mergeIfPossible (Slot slot, ItemStack is, ItemStack is2, int id, int maxId) {

        if (!this.mergeItemStack(is, id, maxId, false)) {
            return true;
        }

        slot.onSlotChange(is, is2);
        return false;
    }

    /**
     * Handles Transfers: Player Inventory <-> Hotbar.
     */
    private boolean mergeInvHotbarIfPossible (Slot slot, ItemStack is, ItemStack is2, int id) {

        //Transfers: Player Inventory -> Hotbar.
        if (id < 27) {

            if (mergeIfPossible(slot, is, is2, 27, 35)) {
                return true;
            }
        }

        //Transfers: Hotbar -> Player Inventory.
        else {

            if (mergeIfPossible(slot, is, is2, 0, 26)) {
                return true;
            }
        }

        slot.onSlotChange(is, is2);
        return false;
    }

    @Override
    public boolean canInteractWith (PlayerEntity playerIn) {
        return true;
    }
}

