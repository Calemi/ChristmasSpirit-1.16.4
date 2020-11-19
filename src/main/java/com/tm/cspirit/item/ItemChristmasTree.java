package com.tm.cspirit.item;

import com.tm.cspirit.entity.EntityChristmasTree;
import com.tm.cspirit.item.base.ItemBase;
import com.tm.cspirit.main.ChristmasSpirit;
import com.tm.cspirit.util.helper.SoundHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class ItemChristmasTree extends ItemBase {

    private final boolean isWhite;

    public ItemChristmasTree(boolean isWhite) {
        super(new Properties().group(ChristmasSpirit.TAB_DECORATION).maxStackSize(1));
        this.isWhite = isWhite;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        if (context.getPlayer() != null) {

            EntityChristmasTree tree = new EntityChristmasTree(context.getWorld(), context.getHitVec(), context.getPlacementYaw() + 180, isWhite);
            context.getWorld().addEntity(tree);

            context.getItem().shrink(1);

            if (!context.getWorld().isRemote) {
                context.getPlayer().playSound(SoundEvents.BLOCK_WOOD_PLACE, 1, 1);
                context.getPlayer().playSound(SoundEvents.BLOCK_GRASS_PLACE, 1, 1);
            }

            return ActionResultType.SUCCESS;
        }

        return super.onItemUse(context);
    }
}
