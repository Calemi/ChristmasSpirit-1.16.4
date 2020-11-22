package com.tm.cspirit.event;

import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.init.InitEffects;
import com.tm.cspirit.item.base.IItemTag;
import com.tm.cspirit.util.helper.EffectHelper;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class NaughtyEvent {

    @SubscribeEvent
    public void onEntityKill(LivingDeathEvent event) {

        if (!event.getEntityLiving().world.isRemote) {

            if (event.getSource().getTrueSource() instanceof PlayerEntity) {

                LivingEntity killedEntity = event.getEntityLiving();
                PlayerEntity killer = (PlayerEntity) event.getSource().getTrueSource();

                boolean killedPlayer = killedEntity instanceof PlayerEntity;
                boolean killedWolf = killedEntity instanceof WolfEntity;
                boolean killedFox = killedEntity instanceof FoxEntity;
                boolean killedCat = killedEntity instanceof CatEntity;
                boolean killedHorse = killedEntity instanceof HorseEntity;
                boolean killedVillager = killedEntity instanceof VillagerEntity;
                boolean killedBaby = killedEntity instanceof AnimalEntity && killedEntity.isChild();
                boolean killedNamedAnimal = killedEntity instanceof AnimalEntity && killedEntity.hasCustomName();

                if (killedPlayer && NaughtyListFile.isOnNaughtyList((PlayerEntity) killedEntity)) {
                    killedPlayer = false;
                }

                if (killedPlayer || killedWolf || killedFox || killedCat || killedHorse || killedVillager || killedBaby || killedNamedAnimal) {
                    EffectHelper.giveNaughtyStackEffect(killer);
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {

        if (!event.getEntityLiving().world.isRemote) {

            if (event.getEntityLiving().getEntityWorld().getGameTime() % 20 * 60 == 0) {

                if (event.getEntityLiving() instanceof PlayerEntity) {

                    PlayerEntity player = (PlayerEntity) event.getEntityLiving();

                    if (NaughtyListFile.isOnNaughtyList(player)) {
                        player.addPotionEffect(new EffectInstance(InitEffects.NAUGHTY.get(), 20 * 60 * 10, 0));
                    }

                    else {

                        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {

                            ItemStack stackInSlot = player.inventory.getStackInSlot(i);

                            if (ItemHelper.hasTag(stackInSlot.getItem(), "naughty")) {

                                Random random = new Random();
                                ItemHelper.spawnStack(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), random.nextDouble() - 0.5D, 0.5D, random.nextDouble() - 0.5D, stackInSlot);
                                player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
                            }
                        }
                    }
                }
            }
        }
    }
}
