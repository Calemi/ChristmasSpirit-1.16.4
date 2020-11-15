package com.tm.cspirit.effect;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
import java.util.List;

public class FrozenEffect extends Effect {

    public FrozenEffect() {
        super(EffectType.HARMFUL, 0);
        addAttributesModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -100F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public String getName() {
        return "Frozen";
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}
