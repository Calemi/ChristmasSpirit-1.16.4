package com.tm.cspirit.inventory;

import com.tm.cspirit.init.InitContainerTypes;
import com.tm.cspirit.tileentity.TileEntityPresentUnwrapped;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPresentUnwrapped extends ContainerBase {

    public ContainerPresentUnwrapped (final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, (TileEntityPresentUnwrapped) getTileEntity(playerInventory, data));
    }

    public ContainerPresentUnwrapped (final int windowId, final PlayerInventory playerInventory, final TileEntityPresentUnwrapped tileEntity) {
        super(InitContainerTypes.PRESENT_UNWRAPPED.get(), windowId, playerInventory, tileEntity, 8, 112);
        this.addSlot(new SlotItemHandler(tileEntity.getInventory(), 0, 80, 18));
    }
}
