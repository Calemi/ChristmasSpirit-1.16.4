package com.tm.cspirit.item;

import com.tm.cspirit.block.base.BlockItemBase;
import com.tm.cspirit.item.base.IItemSpiritSupplier;
import com.tm.cspirit.main.ChristmasSpirit;
import com.tm.cspirit.util.helper.EffectHelper;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemCandyCane extends BlockItemBase implements IItemSpiritSupplier {

    public ItemCandyCane(Block block) {
        super(block, new Item.Properties().group(ChristmasSpirit.TAB_BAKING).food(new Food.Builder().hunger(3).saturation(0.4F).setAlwaysEdible().build()));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entityLiving) {

        EffectHelper.giveHolidaySpiritStackEffect((PlayerEntity) entityLiving, 2);

        return super.onItemUseFinish(stack, world, entityLiving);
    }

    @Override
    public int getMaxStacks() {
        return 2;
    }
}
