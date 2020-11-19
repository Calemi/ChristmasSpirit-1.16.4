package com.tm.cspirit.init;

import com.google.common.collect.Sets;
import com.tm.cspirit.main.CSReference;
import com.tm.cspirit.tileentity.TileEntityCookieTray;
import com.tm.cspirit.tileentity.TileEntityPresentUnwrapped;
import com.tm.cspirit.tileentity.TileEntityPresentWrapped;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, CSReference.MOD_ID);

    public static final RegistryObject<TileEntityType<TileEntityPresentUnwrapped>> PRESENT_UNWRAPPED = TILE_ENTITY_TYPES.register(
            "present_unwrapped", () -> new TileEntityType<>(TileEntityPresentUnwrapped::new, Sets.newHashSet(InitItems.PRESENT_UNWRAPPED.get()), null));

    public static final RegistryObject<TileEntityType<TileEntityPresentWrapped>> PRESENT_WRAPPED = TILE_ENTITY_TYPES.register(
            "present_wrapped", () -> new TileEntityType<>(TileEntityPresentWrapped::new, Sets.newHashSet(InitItems.PRESENT_WRAPPED_RED.get(), InitItems.PRESENT_WRAPPED_GREEN.get(), InitItems.PRESENT_WRAPPED_BLUE.get()), null));

    public static final RegistryObject<TileEntityType<TileEntityCookieTray>> COOKIE_TRAY = TILE_ENTITY_TYPES.register(
            "cookie_tray", () -> new TileEntityType<>(TileEntityCookieTray::new, Sets.newHashSet(InitItems.COOKIE_TRAY.get()), null));
}
