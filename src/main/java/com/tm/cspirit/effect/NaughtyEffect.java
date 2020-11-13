package com.tm.cspirit.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class NaughtyEffect extends Effect {

    public NaughtyEffect() {
        super(EffectType.HARMFUL, 0);
    }

    @Override
    public String getName() {
        return "Naughty";
    }
}
