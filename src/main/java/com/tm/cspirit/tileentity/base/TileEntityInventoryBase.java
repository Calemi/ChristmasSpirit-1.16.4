package com.tm.cspirit.tileentity.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public abstract class TileEntityInventoryBase extends TileEntityBase implements INamedContainerProvider {

    private final CSItemHandler inventory;

    public TileEntityInventoryBase (TileEntityType<?> tileEntityType) {
        super(tileEntityType);

        this.inventory = new CSItemHandler(getSizeInventory());
    }

    public abstract int getSizeInventory();
    public abstract Container getTileContainer(int id, PlayerInventory playerInv);

    public CSItemHandler getInventory() {
        return this.inventory;
    }

    @Override
    public <T> LazyOptional<T> getCapability (Capability<T> cap, Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.inventory));
    }

    @Nullable
    @Override
    public Container createMenu (int id, PlayerInventory playerInv, PlayerEntity player) {
        return getTileContainer(id, playerInv);
    }

    @Override
    public void read (BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);

        NonNullList<ItemStack> inv = NonNullList.withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, inv);
        this.inventory.setNonNullList(inv);
    }

    @Override
    public CompoundNBT write (CompoundNBT nbt) {

        ItemStackHelper.saveAllItems(nbt, this.inventory.toNonNullList());
        return super.write(nbt);
    }
}
