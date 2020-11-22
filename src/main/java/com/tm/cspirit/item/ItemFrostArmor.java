package com.tm.cspirit.item;

import com.tm.cspirit.item.base.IItemTag;
import com.tm.cspirit.item.base.ItemArmorBase;
import com.tm.cspirit.item.tier.CSArmorTiers;
import com.tm.cspirit.main.CSConfig;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemFrostArmor extends ItemArmorBase implements IItemTag {

    public ItemFrostArmor(EquipmentSlotType slot) {
        super(CSArmorTiers.FROST, slot);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new StringTextComponent("Set Bonus: " + TextFormatting.BLUE + "Freezes attackers"));
    }

    @Override
    public String[] getItemTags() {
        return new String[] {CSConfig.misc.naughtyItems.get() ? "naughty" : "disabled"};
    }
}
