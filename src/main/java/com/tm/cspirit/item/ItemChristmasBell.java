package com.tm.cspirit.item;

import com.tm.cspirit.item.base.ItemBase;
import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemChristmasBell extends ItemBase {

    public ItemChristmasBell() {
        super(new Item.Properties().group(ChristmasSpirit.TAB_MAIN).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {



        return super.onItemRightClick(world, player, hand);
    }
}
