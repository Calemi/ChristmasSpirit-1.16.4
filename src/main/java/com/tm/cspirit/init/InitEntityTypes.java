package com.tm.cspirit.init;

import com.tm.cspirit.entity.*;
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

    public static final RegistryObject<EntityType<EntityReindeer>> REINDEER = ENTITY_TYPES.register("reindeer",
            () -> EntityType.Builder.<EntityReindeer>create(EntityReindeer::new, EntityClassification.CREATURE)
                    .size(0.8F, 1.8F)
                    .build(new ResourceLocation(CSReference.MOD_ID, "jack_frost").toString()));

    public static final RegistryObject<EntityType<EntityCandyCaneProjectile>> CANDY_CANE_PROJECTILE = ENTITY_TYPES.register("candy_cane_projectile",
            () -> EntityType.Builder.<EntityCandyCaneProjectile>create(EntityCandyCaneProjectile::new, EntityClassification.MISC)
                    .size(0.5F, 0.5F)
                    .build(new ResourceLocation(CSReference.MOD_ID, "candy_cane_projectile").toString()));

    public static final RegistryObject<EntityType<EntitySleigh>> SLEIGH = ENTITY_TYPES.register("sleigh",
            () -> EntityType.Builder.<EntitySleigh>create(EntitySleigh::new, EntityClassification.MISC)
                    .size(1.95F, 1.95F)
                    .build(new ResourceLocation(CSReference.MOD_ID, "sleigh").toString()));

    public static final RegistryObject<EntityType<EntityChristmasTree>> CHRISTMAS_TREE = ENTITY_TYPES.register("christmas_tree",
            () -> EntityType.Builder.<EntityChristmasTree>create(EntityChristmasTree::new, EntityClassification.MISC)
                    .size(1.5F, 3F)
                    .build(new ResourceLocation(CSReference.MOD_ID, "christmas_tree").toString()));
}
