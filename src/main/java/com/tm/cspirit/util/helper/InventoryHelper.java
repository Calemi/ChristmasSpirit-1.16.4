package com.tm.cspirit.util.helper;

import com.tm.cspirit.tileentity.base.CSItemHandler;
import com.tm.cspirit.util.Location;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class InventoryHelper {

    public static void breakInventory (World world, CSItemHandler inventory, Location location) {

        for (int slotId = 0; slotId < inventory.getSlots(); slotId++) {

            ItemStack stackInSlot = inventory.getStackInSlot(slotId);

            if (!stackInSlot.isEmpty()) {

                ItemEntity dropEntity = ItemHelper.spawnStackAtLocation(world, location, stackInSlot);

                if (stackInSlot.hasTag()) {
                    dropEntity.getItem().setTag(stackInSlot.getTag());
                }
            }
        }
    }
}
