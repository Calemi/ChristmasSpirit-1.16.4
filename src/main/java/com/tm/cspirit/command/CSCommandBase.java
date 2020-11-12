package com.tm.cspirit.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.tm.cspirit.gift.Gift;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CSCommandBase {

    /**
     * Registers all of the commands.
     */
    public static void register (CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> cuCommand = Commands.literal("cspirit");
        cuCommand.requires(commandSource -> true).then(gift());
        dispatcher.register(cuCommand);
    }

    private static ArgumentBuilder<CommandSource, ?> gift () {

        return Commands.literal("gift").requires((player) -> player.hasPermissionLevel(2)).executes(ctx -> {

            Gift.giveGift(ctx.getSource().asPlayer(), 1);
            return Command.SINGLE_SUCCESS;
        });
    }
}
