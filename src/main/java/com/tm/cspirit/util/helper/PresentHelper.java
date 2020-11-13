package com.tm.cspirit.util.helper;

import com.tm.cspirit.block.BlockPresentWrapped;
import com.tm.cspirit.data.SantaGiftListFile;
import com.tm.cspirit.init.InitEffects;
import com.tm.cspirit.init.InitSounds;
import com.tm.cspirit.present.PresentConstructor;
import com.tm.cspirit.util.Location;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPlaySoundPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PresentHelper {

    public static void giveSantaPresent(ServerPlayerEntity player, int day) {

        World world = player.world;

        PresentConstructor constructor = new PresentConstructor();
        constructor.setDay(TimeHelper.getCurrentDay() - 1);
        constructor.setFromPlayerName("Santa");
        constructor.setToPlayerName(player.getDisplayName().getString());
        constructor.setStyleIndex(0);

        BlockPresentWrapped.spawnPresent(new Location(world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()), constructor, ItemStack.EMPTY);

        //Visuals
        FireworkHelper.spawnFirework(player, (byte)1, true, true, DyeColor.RED, DyeColor.GREEN);
        player.connection.sendPacket(new SPlaySoundPacket(InitSounds.CONGRATS.get().getName(), SoundCategory.AMBIENT, player.getPositionVec(), 1, 1));
        ChatHelper.broadcastMessage(world, TextFormatting.RED + "" + TextFormatting.BOLD + player.getDisplayName().getString() + " has received their daily present!");
    }

    public static ItemStack getSantaGiftStack(PlayerEntity player, int day) {

        Random random = new Random();

        List<SantaGiftListFile.GiftEntry> allGiftEntries = new ArrayList<>(SantaGiftListFile.santaGiftList.values());
        List<SantaGiftListFile.GiftEntry> availableGifts = new ArrayList<>();

        int effectStack = 0;

        EffectInstance effect = player.getActivePotionEffect(InitEffects.CHRISTMAS_SPIRIT.get());

        if (effect != null) {
            effectStack = effect.getAmplifier() + 1;
        }

        int firstRarity = 50;
        int secondRarity = 20 + (effectStack * 2);
        int thirdRarity = 15 + (effectStack * 5);
        int fourthRarity = 10 + (effectStack * 10);
        int fifthRarity = 5 + (effectStack * 20);

        int giftRarityIndex = RandomHelper.getWeightedRandom(firstRarity, secondRarity, thirdRarity, fourthRarity, fifthRarity);

        for (SantaGiftListFile.GiftEntry giftEntry : allGiftEntries) {

            if (day >= giftEntry.minDay && day <= giftEntry.maxDay) {

                if (giftEntry.rarityIndex == giftRarityIndex) {
                    availableGifts.add(giftEntry);
                }
            }
        }

        if (availableGifts.size() == 0) {
            return getSantaGiftStack(player, day);
        }

        SantaGiftListFile.GiftEntry giftEntry = availableGifts.get(random.nextInt(availableGifts.size()));

        ItemStack giftStack = giftEntry.getGiftStack();
        giftStack.setCount(random.nextInt(giftEntry.maxAmount) + 1);

        return giftStack;
    }
}
