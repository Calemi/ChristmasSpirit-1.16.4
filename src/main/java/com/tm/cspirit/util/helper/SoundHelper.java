package com.tm.cspirit.util.helper;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlaySoundPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class SoundHelper {

    public static void sendSoundToClient(ServerPlayerEntity player, SoundEvent sound) {
        player.connection.sendPacket(new SPlaySoundPacket(sound.getName(), SoundCategory.AMBIENT, player.getPositionVec(), 1, 1));
    }
}
