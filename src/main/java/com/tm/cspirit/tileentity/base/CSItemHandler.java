package com.tm.cspirit.tileentity.base;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class CSItemHandler extends ItemStackHandler {

    public CSItemHandler(int size, ItemStack... stacks) {
        super(size);

        for (int index = 0; index < stacks.length; index++) {
            this.stacks.set(index, stacks[index]);
        }
    }

    @Override
    public boolean isItemValid (int slot, @Nonnull ItemStack stack) {
        return true;
    }

    public void clear() {

        for (int index = 0; index < this.getSlots(); index++) {
            this.stacks.set(index, ItemStack.EMPTY);
            this.onContentsChanged(index);
        }
    }

    public boolean isEmpty() {
        return stacks.stream().allMatch(ItemStack::isEmpty);
    }

    public ItemStack decrStackSize (int index, int count) {
        return ItemStackHelper.getAndSplit(stacks, index, count);
    }

    public ItemStack removeStackFromSlot (int index) {
        return ItemStackHelper.getAndRemove(stacks, index);
    }

    public NonNullList<ItemStack> toNonNullList() {
        NonNullList<ItemStack> items = NonNullList.create();
        items.addAll(stacks);
        return items;
    }

    public void setNonNullList(NonNullList<ItemStack> items) {

        if (items.size() == 0) {
            return;
        }

        if (items.size() != this.getSlots()) {
            throw new IndexOutOfBoundsException("NonNullList must be same size as ItemStackHandler!");
        }

        for (int index = 0; index < items.size(); index++) {
            stacks.set(index, items.get(index));
        }
    }

    @Override
    public String toString() {
        return stacks.toString();
    }
}
