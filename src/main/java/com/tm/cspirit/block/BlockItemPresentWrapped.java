package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockItemBase;
import com.tm.cspirit.main.ChristmasSpirit;
import com.tm.cspirit.present.PresentConstructor;
import com.tm.cspirit.tileentity.TileEntityPresentWrapped;
import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.helper.ItemHelper;
import com.tm.cspirit.util.helper.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class BlockItemPresentWrapped extends BlockItemBase {

    public BlockItemPresentWrapped(Block block) {
        super(block, ChristmasSpirit.TAB_MAIN);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {

        PresentConstructor constructor = PresentConstructor.fromStack(stack);

        if (!constructor.getToPlayerName().isEmpty()) {
            tooltip.add(new StringTextComponent("From: " + TextFormatting.GOLD + constructor.getFromPlayerName()));
            tooltip.add(new StringTextComponent("To: " + TextFormatting.GOLD + constructor.getToPlayerName()));
            tooltip.add(new StringTextComponent("Open on the " + TextFormatting.GOLD + TimeHelper.getFormattedDay(constructor.getActualDay())));
        }
    }

    @Override
    protected boolean placeBlock(BlockItemUseContext context, BlockState state) {

        boolean b = super.placeBlock(context, state);

        Location location = new Location(context.getWorld(), context.getPos());

        PresentConstructor constructor = PresentConstructor.fromStack(context.getItem());
        constructor.toBlock(location);

        CompoundNBT nbt = ItemHelper.getNBT(context.getItem());
        CompoundNBT compoundnbt = nbt.getList("Items", 10).getCompound(0);

        if (location.getTileEntity() != null && location.getTileEntity() instanceof TileEntityPresentWrapped) {
            TileEntityPresentWrapped tileEntity = (TileEntityPresentWrapped) location.getTileEntity();
            tileEntity.getInventory().setStackInSlot(0, ItemStack.read(compoundnbt));
        }

        return b;
    }
}
