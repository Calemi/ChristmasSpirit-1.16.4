package com.tm.cspirit.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ChristmasSpiritEffect extends Effect {

    public ChristmasSpiritEffect() {
        super(EffectType.BENEFICIAL, 0);
    }

    @Override
    public String getName() {
        return "Christmas Spirit";
    }
}
