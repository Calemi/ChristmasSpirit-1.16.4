package com.tm.cspirit.init;

import com.tm.cspirit.main.CSReference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CSReference.MOD_ID);

    public static final RegistryObject<SoundEvent> CONGRATS = SOUNDS.register("congrats", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "congrats")));

    public static final RegistryObject<SoundEvent> PRESENT_WRAP = SOUNDS.register("present.wrap", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "present.wrap")));
    public static final RegistryObject<SoundEvent> PRESENT_UNWRAP = SOUNDS.register("present.unwrap", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "present.unwrap")));

    public static final RegistryObject<SoundEvent> CAN_OPEN = SOUNDS.register("can.open", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "can.open")));
    public static final RegistryObject<SoundEvent> WANNA_SPRITE_CRANBERRY = SOUNDS.register("wanna.sprite.cranberry", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "wanna.sprite.cranberry")));
    public static final RegistryObject<SoundEvent> THE_ANSWER_IS_CLEAR = SOUNDS.register("the.answer.is.clear", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "the.answer.is.clear")));

    public static final RegistryObject<SoundEvent> WISHBACKGROUND = SOUNDS.register("disc.wishbackground", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "disc.wishbackground")));
    public static final RegistryObject<SoundEvent> MCCHRISTMAS = SOUNDS.register("disc.mcchristmas", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "disc.mcchristmas")));
    public static final RegistryObject<SoundEvent> JARED = SOUNDS.register("disc.jared", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "disc.jared")));
}
