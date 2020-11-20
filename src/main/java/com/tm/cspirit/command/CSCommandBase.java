package com.tm.cspirit.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.tm.cspirit.data.DailyPresentDataFile;
import com.tm.cspirit.data.SantaGiftListFile;
import com.tm.cspirit.util.helper.PresentHelper;
import com.tm.cspirit.util.helper.ChatHelper;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class CSCommandBase {

    /**
     * Registers all of the commands.
     */
    public static void register (CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> csCommand = Commands.literal("cspirit");
        csCommand.requires(commandSource -> true)
                .then(gift().requires((player) -> player.hasPermissionLevel(2)))
                .then(reloadSantaGifts().requires((player) -> player.hasPermissionLevel(2)))
                .then(removePresentData().requires((player) -> player.hasPermissionLevel(2)));
        dispatcher.register(csCommand);
    }

    private static ArgumentBuilder<CommandSource, ?> gift () {

        return Commands.literal("gift").executes(ctx -> 0).then(Commands.argument("day", IntegerArgumentType.integer(1, 32)).executes(ctx -> {

            PresentHelper.giveSantaPresent(ctx.getSource().asPlayer(), IntegerArgumentType.getInteger(ctx, "day") - 1);
            return Command.SINGLE_SUCCESS;
        }));
    }

    private static ArgumentBuilder<CommandSource, ?> reloadSantaGifts () {

        return Commands.literal("reloadSantaGifts").executes(ctx -> {

            SantaGiftListFile.init();

            List<SantaGiftListFile.GiftEntry> allGiftEntries = new ArrayList<>(SantaGiftListFile.santaGiftList.values());

            for (SantaGiftListFile.GiftEntry entry : allGiftEntries) {

                ItemStack giftStack = new ItemStack(Registry.ITEM.getOrDefault(new ResourceLocation(entry.stackStr)));

                if (giftStack.isEmpty()) {
                    ChatHelper.printModMessage(TextFormatting.RED, "COULD NOT ADD ITEM: " + entry.stackStr, ctx.getSource().asPlayer());
                }
            }

            ChatHelper.printModMessage(TextFormatting.GREEN, "Reload Complete!", ctx.getSource().asPlayer());

            return Command.SINGLE_SUCCESS;
        });
    }

    private static ArgumentBuilder<CommandSource, ?> removePresentData () {

        return Commands.literal("removePresentData").executes(ctx -> {

            DailyPresentDataFile.clearEntries();
            ChatHelper.broadcastMessage(ctx.getSource().getWorld(), TextFormatting.RED + "" + TextFormatting.BOLD + "All players can now receive gifts for this day!");

            return Command.SINGLE_SUCCESS;
        });
    }
}
