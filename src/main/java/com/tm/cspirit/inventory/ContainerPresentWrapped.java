package com.tm.cspirit.inventory;

import com.tm.cspirit.init.InitContainerTypes;
import com.tm.cspirit.inventory.base.ContainerBase;
import com.tm.cspirit.tileentity.TileEntityPresentWrapped;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPresentWrapped extends ContainerBase {

    public ContainerPresentWrapped(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, (TileEntityPresentWrapped) getTileEntity(playerInventory, data));
    }

    public ContainerPresentWrapped(final int windowId, final PlayerInventory playerInventory, final TileEntityPresentWrapped tileEntity) {
        super(InitContainerTypes.PRESENT_WRAPPED.get(), windowId, playerInventory, tileEntity, 8, 41);
        this.addSlot(new SlotItemHandler(tileEntity.getInventory(), 0, 80, 18));
    }
}
