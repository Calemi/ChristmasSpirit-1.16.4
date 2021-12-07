package com.tm.cspirit.util.helper;

import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.init.InitEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TextFormatting;

public class EffectHelper {

    public static void giveFrozenEffect(LivingEntity entity, int seconds) {

        entity.addPotionEffect(new EffectInstance(InitEffects.FROZEN.get(), 20 * seconds));

        if (!entity.world.isRemote) {
            if (entity instanceof PlayerEntity) SoundHelper.sendSoundToClient((ServerPlayerEntity) entity, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE);
        }

        entity.playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, 0.7F, 1);
    }

    public static void giveHolidaySpiritStackEffect(PlayerEntity player, int maxStackSize) {
        giveHolidaySpiritStackEffect(player, maxStackSize, 60 * 5);
    }

    public static void giveHolidaySpiritStackEffect(PlayerEntity player, int maxStackSize, int seconds) {

        if (player.getActivePotionEffect(InitEffects.NAUGHTY.get()) == null && !NaughtyListFile.isOnNaughtyList(player)) {
            stackEffect(player, InitEffects.HOLIDAY_SPIRIT.get(), seconds, maxStackSize);
        }
    }

    public static void giveNaughtyStackEffect(PlayerEntity player) {
        player.removePotionEffect(InitEffects.HOLIDAY_SPIRIT.get());
        stackEffect(player, InitEffects.NAUGHTY.get(), 30 * 60, 5);

        if (!NaughtyListFile.isOnNaughtyList(player)) {

            int naughtyStacks = player.getActivePotionEffect(InitEffects.NAUGHTY.get()).getAmplifier();

            if (naughtyStacks == 2) {
                ChatHelper.printModMessage(TextFormatting.RED,"Be careful! You've been pretty naughty!", player);
                ChatHelper.printModMessage(TextFormatting.RED,"Do a few more naughty deeds, and you'll be marked on the Naughty List!", player);
            }

            if (naughtyStacks >= 4) {

                NaughtyListFile.addToNaughtyList(player);

                ChatHelper.printModMessage(TextFormatting.RED,"You've been very naughty! You are now on the Naughty List!", player);
                ChatHelper.printModMessage(TextFormatting.RED,"Eat a Santa Cookie to be removed.", player);
            }
        }
    }

    public static void stackEffect(PlayerEntity player, Effect effect, int seconds, int maxStackSize) {

        int spiritStack = 0;

        if (player.getActivePotionEffect(effect) != null) {
            spiritStack = player.getActivePotionEffect(effect).getAmplifier() + 1;
        }

        if (spiritStack < maxStackSize) {
            player.addPotionEffect(new EffectInstance(effect, 20 * seconds, spiritStack));
        }

        else player.addPotionEffect(new EffectInstance(effect, 20 * seconds, maxStackSize - 1));
    }
}
