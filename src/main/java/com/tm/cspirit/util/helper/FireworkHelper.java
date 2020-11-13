package com.tm.cspirit.util.helper;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

import java.util.List;

public class FireworkHelper {

    public static void spawnFirework(PlayerEntity player, byte flight, boolean doesFlicker, boolean doesTrail, DyeColor color1, DyeColor color2) {

        ItemStack fireworkStack = new ItemStack(Items.FIREWORK_ROCKET);
        CompoundNBT fireworkNBT = fireworkStack.getOrCreateChildTag("Fireworks");

        CompoundNBT explosionNBT = fireworkNBT.getCompound("Explosion");
        ListNBT listnbt = new ListNBT();
        listnbt.add(explosionNBT);

        explosionNBT.putBoolean("Flicker", doesFlicker);
        explosionNBT.putBoolean("Trail", doesTrail);

        List<Integer> colorList = Lists.newArrayList();

        colorList.add(color1.getFireworkColor());
        colorList.add(color2.getFireworkColor());

        explosionNBT.putIntArray("Colors", colorList);
        explosionNBT.putByte("Type", (byte) FireworkRocketItem.Shape.STAR.getIndex());

        fireworkNBT.putByte("Flight", flight);
        fireworkNBT.put("Explosions", listnbt);

        FireworkRocketEntity fireworkEntity = new FireworkRocketEntity(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), fireworkStack);
        player.world.addEntity(fireworkEntity);
    }
}
