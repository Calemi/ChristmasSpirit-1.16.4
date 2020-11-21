package com.tm.cspirit.util.helper;

import com.tm.cspirit.util.Location;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class ItemHelper {

    public static CompoundNBT getNBT (ItemStack is) {

        if (is.getTag() == null) {
            is.setTag(new CompoundNBT());
        }

        return is.getTag();
    }

    public static ItemEntity spawnStackAtLocation(World world, Location location, ItemStack stack) {
        return spawnStack(world, location.x + 0.5F, location.y + 0.5F, location.z + 0.5F, stack);
    }

    public static void spawnStackAtEntity(World world, Entity entity, ItemStack stack) {
        spawnStack(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), stack);
    }

    public static ItemEntity spawnStack(World world, double x, double y, double z, double motionX, double motionY, double motionZ, ItemStack stack) {
        ItemEntity item = new ItemEntity(world, x, y, z, stack);
        item.setMotion(motionX, motionY, motionZ);
        item.setDefaultPickupDelay();
        world.addEntity(item);
        return item;
    }

    public static ItemEntity spawnStack(World world, double x, double y, double z, ItemStack stack) {
        return spawnStack(world, x, y, z, 0, 0.2D, 0, stack);
    }
}
