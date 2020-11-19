package com.tm.cspirit.entity.data;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.util.NonNullList;

public class CSDataSerializers {

    public static final IDataSerializer<NonNullList<ItemStack>> ITEMSTACK_ARRAY_4 = new IDataSerializer<NonNullList<ItemStack>>() {

        @Override
        public void write(PacketBuffer buf, NonNullList<ItemStack> value) {

            for (ItemStack stack : value) {
                buf.writeItemStack(stack);
            }
        }

        @Override
        public NonNullList<ItemStack> read(PacketBuffer buf) {

            NonNullList<ItemStack> list = NonNullList.withSize(4, ItemStack.EMPTY);

            for (int i = 0; i < 4; i++) {
                list.set(i, buf.readItemStack());
            }

            return list;
        }

        @Override
        public NonNullList<ItemStack> copyValue(NonNullList<ItemStack> value) {

            NonNullList<ItemStack> list = NonNullList.withSize(4, ItemStack.EMPTY);

            for (int i = 0; i < 4; i++) {
                list.set(i, value.get(i).copy());
            }

            return list;
        }
    };
}
