package com.tm.cspirit.event;

import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.init.InitEffects;
import com.tm.cspirit.util.helper.EffectHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NaughtyEvent {

    @SubscribeEvent
    public void onEntityKill(LivingDeathEvent event) {

        if (event.getSource().getTrueSource() instanceof PlayerEntity) {

            LivingEntity killedEntity = event.getEntityLiving();
            PlayerEntity killer = (PlayerEntity) event.getSource().getTrueSource();

            boolean killedPlayer = killedEntity instanceof PlayerEntity;
            boolean killedWolf = killedEntity instanceof WolfEntity;
            boolean killedCat = killedEntity instanceof CatEntity;
            boolean killedHorse = killedEntity instanceof HorseEntity;
            boolean killedVillager = killedEntity instanceof VillagerEntity;
            boolean killedBaby = killedEntity instanceof AnimalEntity && killedEntity.isChild();
            boolean killedNamedAnimal = killedEntity instanceof AnimalEntity && killedEntity.hasCustomName();

            if (killedPlayer || killedWolf || killedCat || killedHorse || killedVillager || killedBaby || killedNamedAnimal) {
                EffectHelper.giveNaughtyStackEffect(killer);
            }
        }
    }

    @SubscribeEvent
    public void onPotionCured(PotionEvent.PotionRemoveEvent event) {
        /*if (event.getPotion() == InitEffects.NAUGHTY.get()) {
            event.setCanceled(true);
        }*/
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {

        if (event.getEntityLiving().getEntityWorld().getGameTime() % 20 * 60 == 0) {

            if (event.getEntityLiving() instanceof PlayerEntity) {

                PlayerEntity player = (PlayerEntity) event.getEntityLiving();

                if (NaughtyListFile.isOnNaughtyList(player)) {

                    player.addPotionEffect(new EffectInstance(InitEffects.NAUGHTY.get(), 20 * 60 * 10, 0));
                }
            }
        }
    }
}
