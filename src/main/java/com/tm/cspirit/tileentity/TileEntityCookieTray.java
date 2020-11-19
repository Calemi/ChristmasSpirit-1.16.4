package com.tm.cspirit.tileentity;

import com.tm.cspirit.init.InitTileEntityTypes;
import com.tm.cspirit.inventory.ContainerCookieTray;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileEntityCookieTray extends TileEntityInventoryBase {

    public TileEntityCookieTray() {
        super(InitTileEntityTypes.COOKIE_TRAY.get());
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Cookie Tray");
    }

    @Override
    public int getSizeInventory () {
        return 3;
    }

    @Override
    public Container getTileContainer (int windowId, PlayerInventory playerInv) {
        return new ContainerCookieTray(windowId, playerInv, this);
    }
}
