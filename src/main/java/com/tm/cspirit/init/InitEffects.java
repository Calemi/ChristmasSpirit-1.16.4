package com.tm.cspirit.init;

import com.tm.cspirit.effect.HolidaySpiritEffect;
import com.tm.cspirit.effect.NaughtyEffect;
import com.tm.cspirit.main.CSReference;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEffects {

    public static final DeferredRegister<Effect> POTION_TYPES = DeferredRegister.create(ForgeRegistries.POTIONS, CSReference.MOD_ID);

    public static final RegistryObject<Effect> HOLIDAY_SPIRIT = POTION_TYPES.register("holiday_spirit", HolidaySpiritEffect::new);
    public static final RegistryObject<Effect> NAUGHTY = POTION_TYPES.register("naughty", NaughtyEffect::new);
}
