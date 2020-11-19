package com.tm.cspirit.inventory;

import com.tm.cspirit.init.InitContainerTypes;
import com.tm.cspirit.inventory.base.ContainerBase;
import com.tm.cspirit.inventory.base.SlotLimitedStack;
import com.tm.cspirit.tileentity.TileEntityCookieTray;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class ContainerCookieTray extends ContainerBase {

    public ContainerCookieTray(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, (TileEntityCookieTray) getTileEntity(playerInventory, data));
    }

    public ContainerCookieTray(final int windowId, final PlayerInventory playerInventory, final TileEntityCookieTray tileEntity) {
        super(InitContainerTypes.COOKIE_TRAY.get(), windowId, playerInventory, tileEntity, 8, 50);
        this.addSlot(new SlotLimitedStack(tileEntity.getInventory(), 0, 62, 18, 3));
        this.addSlot(new SlotLimitedStack(tileEntity.getInventory(), 1, 80, 18, 3));
        this.addSlot(new SlotLimitedStack(tileEntity.getInventory(), 2, 98, 18, 3));
    }
}
