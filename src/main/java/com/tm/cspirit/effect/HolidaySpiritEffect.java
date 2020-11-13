package com.tm.cspirit.effect;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
import java.util.List;

public class HolidaySpiritEffect extends Effect {

    public HolidaySpiritEffect() {
        super(EffectType.BENEFICIAL, 0);
    }

    @Override
    public String getName() {
        return "Holiday Spirit";
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}
