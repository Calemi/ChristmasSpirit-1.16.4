package com.tm.cspirit.tileentity;

import com.tm.cspirit.init.InitTileEntityTypes;
import com.tm.cspirit.inventory.ContainerPresentWrapped;
import com.tm.cspirit.present.PresentConstructor;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileEntityPresentWrapped extends TileEntityInventoryBase {

    private PresentConstructor presentConstructor;

    public TileEntityPresentWrapped () {
        super(InitTileEntityTypes.PRESENT_WRAPPED.get());
        presentConstructor = new PresentConstructor();
    }

    public PresentConstructor getConstructor() {
        return presentConstructor;
    }

    public void setConstructor(PresentConstructor presentConstructor) {
        this.presentConstructor = presentConstructor;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Wrapped Present");
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
        return new ContainerPresentWrapped(windowId, playerInv, this);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        presentConstructor = PresentConstructor.fromNBT(nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        presentConstructor.toNBT(nbt);
        return super.write(nbt);
    }
}
