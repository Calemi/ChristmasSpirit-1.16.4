package com.tm.cspirit.util.helper;

import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.init.InitEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.TextFormatting;

public class EffectHelper {

    public static void giveHolidaySpiritStackEffect(PlayerEntity player, int maxStackSize) {

        if (player.getActivePotionEffect(InitEffects.NAUGHTY.get()) == null) {
            stackEffect(player, InitEffects.HOLIDAY_SPIRIT.get(), maxStackSize);
        }
    }

    public static void giveNaughtyStackEffect(PlayerEntity player) {
        player.removePotionEffect(InitEffects.HOLIDAY_SPIRIT.get());
        stackEffect(player, InitEffects.NAUGHTY.get(), 5);

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

    public static void stackEffect(PlayerEntity player, Effect effect, int maxStackSize) {

        int spiritStack = 0;

        if (player.getActivePotionEffect(effect) != null) {
            spiritStack = player.getActivePotionEffect(effect).getAmplifier() + 1;
        }

        if (spiritStack < maxStackSize) {
            player.addPotionEffect(new EffectInstance(effect, 20 * 60, spiritStack));
        }

        else player.addPotionEffect(new EffectInstance(effect, 20 * 60, maxStackSize - 1));
    }
}
