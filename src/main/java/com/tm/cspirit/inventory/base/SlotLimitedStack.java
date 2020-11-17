package com.tm.cspirit.inventory.base;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

public class SlotLimitedStack extends SlotItemHandler {

    private final int index;
    private final int maxStackSize;

    public SlotLimitedStack(IItemHandler itemHandler, int index, int xPosition, int yPosition, int maxStackSize) {
        super(itemHandler, index, xPosition, yPosition);
        this.index = index;
        this.maxStackSize = maxStackSize;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {

        ItemStack maxAdd = stack.copy();
        int maxInput = maxStackSize;
        maxAdd.setCount(maxInput);

        IItemHandler handler = this.getItemHandler();
        ItemStack currentStack = handler.getStackInSlot(index);

        if (handler instanceof IItemHandlerModifiable) {

            IItemHandlerModifiable handlerModifiable = (IItemHandlerModifiable) handler;

            handlerModifiable.setStackInSlot(index, ItemStack.EMPTY);

            ItemStack remainder = handlerModifiable.insertItem(index, maxAdd, true);

            handlerModifiable.setStackInSlot(index, currentStack);

            return maxInput - remainder.getCount();
        }

        else {
            ItemStack remainder = handler.insertItem(index, maxAdd, true);

            int current = currentStack.getCount();
            int added = maxInput - remainder.getCount();
            return current + added;
        }
    }
}
