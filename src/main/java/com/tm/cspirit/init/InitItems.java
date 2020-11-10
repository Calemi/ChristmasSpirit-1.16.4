package com.tm.cspirit.init;

import com.tm.cspirit.block.base.BlockItemBase;
import com.tm.cspirit.item.base.ItemDiscBase;
import com.tm.cspirit.main.CSReference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class InitItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CSReference.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CSReference.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //------ITEMS------\\

    //CROPS

    //INGREDIENTS

    //FOOD

    //WEARABLES

    //DISCS
    public static final RegistryObject<Item> DISC_WISHBACKGROUND = ITEMS.register("disc_wishbackground", () -> new ItemDiscBase(InitSounds.WISHBACKGROUND));
    public static final RegistryObject<Item> DISC_MCCHRISTMAS = ITEMS.register("disc_mcchristmas", () -> new ItemDiscBase(InitSounds.MCCHRISTMAS));
    public static final RegistryObject<Item> DISC_JARED = ITEMS.register("disc_jared", () -> new ItemDiscBase(InitSounds.JARED));

    //OTHER


    //------NAUGHTY ITEMS------\\


    //------BLOCKS------\\

    //CROPS

    //DECORATIONS

    public static RegistryObject<Item> regItem(String name, final Supplier<? extends Item> sup) {
        return ITEMS.register(name, sup);
    }

    public static RegistryObject<Block> regBlock(String name, ItemGroup tab, final Supplier<? extends Block> sup) {
        RegistryObject<Block> registryBlock = BLOCKS.register(name, sup);
        RegistryObject<Item> registryItem = ITEMS.register(name, () -> new BlockItemBase(registryBlock.get(), tab));
        return registryBlock;
    }
}
