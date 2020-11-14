package com.tm.cspirit.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.tm.cspirit.data.DailyPresentDataFile;
import com.tm.cspirit.util.helper.PresentHelper;
import com.tm.cspirit.util.helper.ChatHelper;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TextFormatting;

public class CSCommandBase {

    /**
     * Registers all of the commands.
     */
    public static void register (CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> csCommand = Commands.literal("cspirit");
        csCommand.requires(commandSource -> true)
                .then(gift().requires((player) -> player.hasPermissionLevel(2)))
                .then(removePresentData().requires((player) -> player.hasPermissionLevel(2)));
        dispatcher.register(csCommand);
    }

    private static ArgumentBuilder<CommandSource, ?> gift () {

        return Commands.literal("gift").executes(ctx -> {

            PresentHelper.giveSantaPresent(ctx.getSource().asPlayer(), 1);
            return Command.SINGLE_SUCCESS;
        });
    }

    private static ArgumentBuilder<CommandSource, ?> removePresentData () {

        return Commands.literal("removePresentData").requires((player) -> player.hasPermissionLevel(2)).executes(ctx -> {

            DailyPresentDataFile.clearEntries();
            ChatHelper.broadcastMessage(ctx.getSource().getWorld(), TextFormatting.RED + "" + TextFormatting.BOLD + "All players can now receive gifts for this day!");

            return Command.SINGLE_SUCCESS;
        });
    }
}
