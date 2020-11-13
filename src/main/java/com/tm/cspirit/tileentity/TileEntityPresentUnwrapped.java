package com.tm.cspirit.tileentity;

import com.tm.cspirit.init.InitTileEntityTypes;
import com.tm.cspirit.inventory.ContainerPresentUnwrapped;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileEntityPresentUnwrapped extends TileEntityInventoryBase {

    public TileEntityPresentUnwrapped () {
        super(InitTileEntityTypes.PRESENT_UNWRAPPED.get());
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Unwrapped Present");
    }

    @Override
    public void tick () {

    }

    @Override
    public int getSizeInventory () {
        return 1;
    }

    @Override
    public Container getTileContainer (int windowId, PlayerInventory playerInv) {
        return new ContainerPresentUnwrapped(windowId, playerInv, this);
    }
}
