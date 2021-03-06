package com.tm.cspirit.item;

import com.tm.cspirit.entity.EntitySleigh;
import com.tm.cspirit.item.base.ItemBase;
import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class ItemSleigh extends ItemBase {

    public ItemSleigh() {
     super(new Item.Properties().group(ChristmasSpirit.TAB_MAIN).maxStackSize(1));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        if (context.getPlayer() != null) {
            EntitySleigh sleigh = new EntitySleigh(context.getWorld(), context.getHitVec().x, context.getHitVec().y, context.getHitVec().z);
            sleigh.rotationYaw = context.getPlayer().rotationYaw;
            context.getWorld().addEntity(sleigh);
            context.getItem().shrink(1);

            return ActionResultType.SUCCESS;
        }

        return super.onItemUse(context);
    }
}
