package com.tm.cspirit.init;

import com.tm.cspirit.entity.EntityJackFrost;
import com.tm.cspirit.main.CSReference;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, CSReference.MOD_ID);

    public static final RegistryObject<EntityType<EntityJackFrost>> JACK_FROST = ENTITY_TYPES.register("jack_frost",
            () -> EntityType.Builder.<EntityJackFrost>create(EntityJackFrost::new, EntityClassification.MONSTER)
                    .size(0.8F, 1.8F)
                    .build(new ResourceLocation(CSReference.MOD_ID, "jack_frost").toString()));
}
