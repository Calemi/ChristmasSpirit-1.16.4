package com.tm.cspirit.init;

import com.tm.cspirit.inventory.ContainerCookieTray;
import com.tm.cspirit.inventory.ContainerPresentUnwrapped;
import com.tm.cspirit.inventory.ContainerPresentWrapped;
import com.tm.cspirit.main.CSReference;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, CSReference.MOD_ID);

    public static final RegistryObject<ContainerType<ContainerPresentUnwrapped>> PRESENT_UNWRAPPED = CONTAINER_TYPES.register(
            "present_unwrapped", () -> IForgeContainerType.create(ContainerPresentUnwrapped::new));

    public static final RegistryObject<ContainerType<ContainerPresentWrapped>> PRESENT_WRAPPED = CONTAINER_TYPES.register(
            "present_wrapped", () -> IForgeContainerType.create(ContainerPresentWrapped::new));

    public static final RegistryObject<ContainerType<ContainerCookieTray>> COOKIE_TRAY = CONTAINER_TYPES.register(
            "cookie_tray", () -> IForgeContainerType.create(ContainerCookieTray::new));
}
