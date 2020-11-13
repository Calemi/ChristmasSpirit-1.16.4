package com.tm.cspirit.present;

import com.tm.cspirit.tileentity.TileEntityPresentUnwrapped;
import com.tm.cspirit.tileentity.TileEntityPresentWrapped;
import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;

public class PresentConstructor {

    private String fromPlayerName;
    private String toPlayerName;
    private int day;
    private int styleIndex;

    public PresentConstructor() {
        fromPlayerName = "";
        toPlayerName = "";
        day = 0;
        styleIndex = 0;
    }

    public String getFromPlayerName() {
        return this.fromPlayerName;
    }

    public String getToPlayerName() {
        return this.toPlayerName;
    }

    public int getDay() {
        return this.day;
    }

    public int getActualDay() {
        return this.day + 1;
    }

    public int getStyleIndex() {
        return this.styleIndex;
    }

    public PresentStyle getStyle() {
        return PresentStyle.fromIndex(styleIndex);
    }

    public void setFromPlayerName(String fromPlayerName) {
        this.fromPlayerName = fromPlayerName;
    }

    public void setToPlayerName(String toPlayerName) {
        this.toPlayerName = toPlayerName;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setStyleIndex(int styleIndex) {
        this.styleIndex = styleIndex;
    }

    public void toStack(ItemStack stack) {
        CompoundNBT nbt = ItemHelper.getNBT(stack);
        toNBT(nbt);
    }

    public static PresentConstructor fromStack(ItemStack stack) {
        CompoundNBT nbt = ItemHelper.getNBT(stack);
        return fromNBT(nbt);
    }

    public void toBlock(Location location) {

        ItemStack giftStack = ItemStack.EMPTY;

        if (location.getTileEntity() != null && location.getTileEntity() instanceof TileEntityPresentUnwrapped) {
            giftStack = ((TileEntityPresentUnwrapped)location.getTileEntity()).getInventory().getStackInSlot(0);
        }

        location.setBlock(getStyle().getBlock());

        if (location.getTileEntity() != null && location.getTileEntity() instanceof TileEntityPresentWrapped) {
            TileEntityPresentWrapped tileEntity = (TileEntityPresentWrapped) location.getTileEntity();
            tileEntity.setConstructor(this);
            tileEntity.getInventory().setStackInSlot(0, giftStack);
        }
    }

    public static PresentConstructor fromBytes(PacketBuffer buf) {
        PresentConstructor constructor = new PresentConstructor();
        constructor.setFromPlayerName(buf.readString(64).trim());
        constructor.setToPlayerName(buf.readString(64).trim());
        constructor.setDay(buf.readInt());
        constructor.setStyleIndex(buf.readInt());
        return constructor;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeString(fromPlayerName, fromPlayerName.length());
        buf.writeString(toPlayerName, toPlayerName.length());
        buf.writeInt(day);
        buf.writeInt(styleIndex);
    }

    public static PresentConstructor fromNBT(CompoundNBT nbt) {
        PresentConstructor constructor = new PresentConstructor();
        constructor.setFromPlayerName(nbt.getString("Present-FromPlayerName"));
        constructor.setToPlayerName(nbt.getString("Present-ToPlayerName"));
        constructor.setDay(nbt.getInt("Present-Day"));
        constructor.setStyleIndex(nbt.getInt("Present-Style"));
        return constructor;
    }

    public void toNBT(CompoundNBT nbt) {
        nbt.putString("Present-FromPlayerName", fromPlayerName);
        nbt.putString("Present-ToPlayerName", toPlayerName);
        nbt.putInt("Present-Day", day);
        nbt.putInt("Present-Style", styleIndex);
    }
}
