package com.tm.cspirit.item;

import com.tm.cspirit.item.base.IItemTag;
import com.tm.cspirit.main.CSConfig;
import com.tm.cspirit.main.ChristmasSpirit;
import com.tm.cspirit.util.helper.EffectHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ItemFrostmourne extends SwordItem implements IItemTag {

    public ItemFrostmourne() {
        super(ItemTier.DIAMOND, 3, -2.4F, new Item.Properties().group(ChristmasSpirit.TAB_MAIN).maxStackSize(1));
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {

        tooltip.add(new StringTextComponent(TextFormatting.BLUE + "Randomly freezes enemies"));
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        Random random = new Random();

        stack.damageItem(1, attacker, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));

        if (random.nextInt(3) == 0) {
            EffectHelper.giveFrozenEffect(target, 2);
        }

        return true;
    }

    @Override
    public String[] getItemTags() {
        return new String[] {CSConfig.misc.naughtyItems.get() ? "naughty" : "disabled"};
    }
}
