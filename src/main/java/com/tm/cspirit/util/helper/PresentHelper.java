package com.tm.cspirit.util.helper;

import com.tm.cspirit.block.BlockPresentWrapped;
import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.data.SantaGiftListFile;
import com.tm.cspirit.init.InitEffects;
import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.init.InitSounds;
import com.tm.cspirit.present.PresentConstructor;
import com.tm.cspirit.util.Location;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PresentHelper {

    public static void giveSantaPresent(ServerPlayerEntity player, int day) {

        World world = player.world;
        BlockPresentWrapped.spawnPresent(new Location(world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()), getSantaPresent(player.getName().getString(), day), ItemStack.EMPTY);

        //Visuals
        FireworkHelper.spawnFirework(player, (byte)1, true, true, DyeColor.RED, DyeColor.GREEN);
        SoundHelper.sendSoundToClient(player, InitSounds.CONGRATS.get());
        ChatHelper.broadcastMessage(world, TextFormatting.GREEN + "" + TextFormatting.BOLD + player.getDisplayName().getString() + " has received their daily present!");
    }

    public static PresentConstructor getSantaPresent(String toPlayerName, int day) {
        PresentConstructor constructor = new PresentConstructor();
        constructor.setDay(day);
        constructor.setFromPlayerName("Santa");
        constructor.setToPlayerName(toPlayerName);
        constructor.setStyleIndex(0);
        return constructor;
    }

    public static ItemStack getSantaGiftedStack(PlayerEntity player, int day) {

        Random random = new Random();

        if (NaughtyListFile.isOnNaughtyList(player)) {

            int naughtyEffectStack = 0;

            EffectInstance naughtyEffect = player.getActivePotionEffect(InitEffects.NAUGHTY.get());

            if (naughtyEffect != null) {
                naughtyEffectStack = naughtyEffect.getAmplifier() + 1;
            }

            return new ItemStack(InitItems.LUMP_OF_COAL.get(), random.nextInt(naughtyEffectStack * 2 + 1) + 1);
        }

        List<SantaGiftListFile.GiftEntry> allGiftEntries = new ArrayList<>(SantaGiftListFile.santaGiftList.values());
        List<SantaGiftListFile.GiftEntry> availableGifts = new ArrayList<>();

        int spiritEffectStack = 0;

        EffectInstance spiritEffect = player.getActivePotionEffect(InitEffects.HOLIDAY_SPIRIT.get());

        if (spiritEffect != null) {
            spiritEffectStack = spiritEffect.getAmplifier() + 1;
        }

        int firstRarity = 50;
        int secondRarity = 20 + (spiritEffectStack * 2);
        int thirdRarity = 15 + (spiritEffectStack * 5);
        int fourthRarity = 10 + (spiritEffectStack * 10);
        int fifthRarity = 5 + (spiritEffectStack * 20);

        int giftRarityIndex = RandomHelper.getWeightedRandom(firstRarity, secondRarity, thirdRarity, fourthRarity, fifthRarity);

        for (SantaGiftListFile.GiftEntry giftEntry : allGiftEntries) {

            if (day >= giftEntry.minDay && day <= giftEntry.maxDay) {

                if (giftEntry.rarityIndex == giftRarityIndex) {
                    availableGifts.add(giftEntry);
                }
            }
        }

        if (availableGifts.size() == 0) {
            return getSantaGiftedStack(player, day);
        }

        SantaGiftListFile.GiftEntry giftEntry = availableGifts.get(random.nextInt(availableGifts.size()));

        ItemStack giftStack = giftEntry.getGiftStack();
        giftStack.setCount(random.nextInt(giftEntry.maxAmount) + 1);

        return giftStack;
    }
}
