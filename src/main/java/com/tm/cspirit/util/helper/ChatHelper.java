package com.tm.cspirit.util.helper;

import com.tm.cspirit.main.CSReference;
import com.tm.cspirit.util.UnitChatMessage;
import net.minecraft.entity.Entity;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.Objects;

public class ChatHelper {

    /**
     * Used to print the main mod's messages.
     * @param format The color or style of the message.
     * @param message The message.
     * @param players The Players that will receive the message.
     */
    public static void printModMessage (TextFormatting format, String message, Entity... players) {
        UnitChatMessage unitMessage = new UnitChatMessage(CSReference.MOD_NAME, players);
        unitMessage.printMessage(format, message);
    }

    /**
     * Used to send a message to everyone.
     * @param message The message.
     */
    public static void broadcastMessage (World world, String message) {
        Objects.requireNonNull(world.getServer()).getPlayerList().func_232641_a_(new StringTextComponent(message), ChatType.SYSTEM, Util.DUMMY_UUID);
    }
}
