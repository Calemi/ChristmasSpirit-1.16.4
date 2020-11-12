package com.tm.cspirit.gift;

import com.tm.cspirit.init.InitSounds;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlaySoundPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Objects;

public class Gift {

    public static void giveGift(ServerPlayerEntity player, int day) {
        player.connection.sendPacket(new SPlaySoundPacket(InitSounds.CONGRATS.get().getName(), SoundCategory.AMBIENT, player.getPositionVec(), 1, 1));
        Objects.requireNonNull(player.world.getServer()).getPlayerList().func_232641_a_(new StringTextComponent(TextFormatting.RED + "" + TextFormatting.BOLD + player.getDisplayName().getString() + " has received their daily gift!"), ChatType.SYSTEM, Util.DUMMY_UUID);
    }
}
