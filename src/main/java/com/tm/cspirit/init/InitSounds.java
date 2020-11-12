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

    public static final RegistryObject<SoundEvent> WISHBACKGROUND = SOUNDS.register("disc.wishbackground", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "disc.wishbackground")));
    public static final RegistryObject<SoundEvent> MCCHRISTMAS = SOUNDS.register("disc.mcchristmas", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "disc.mcchristmas")));
    public static final RegistryObject<SoundEvent> JARED = SOUNDS.register("disc.jared", () -> new SoundEvent(new ResourceLocation(CSReference.MOD_ID, "disc.jared")));
}
