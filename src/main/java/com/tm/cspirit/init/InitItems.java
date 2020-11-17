package com.tm.cspirit.init;

import com.tm.cspirit.block.*;
import com.tm.cspirit.block.base.*;
import com.tm.cspirit.item.*;
import com.tm.cspirit.item.base.ItemArmorBase;
import com.tm.cspirit.item.base.ItemBase;
import com.tm.cspirit.item.base.ItemFoodBase;
import com.tm.cspirit.item.tier.CSArmorTiers;
import com.tm.cspirit.main.CSReference;
import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.inventory.EquipmentSlotType;
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

    //------ITEMS------\\

    //INGREDIENTS
    public static final RegistryObject<Item> FLOUR =                       regItem("flour", () -> new ItemBase(ChristmasSpirit.TAB_BAKING));
    public static final RegistryObject<Item> ICING =                       regItem("icing",() -> new ItemBase(ChristmasSpirit.TAB_BAKING));

    public static final RegistryObject<Item> FOOD_DYE_RED =                regItem("food_dye_red", () -> new ItemBase(ChristmasSpirit.TAB_BAKING));
    public static final RegistryObject<Item> FOOD_DYE_GREEN =              regItem("food_dye_green", () -> new ItemBase(ChristmasSpirit.TAB_BAKING));
    public static final RegistryObject<Item> FOOD_DYE_BLUE =               regItem("food_dye_blue", () -> new ItemBase(ChristmasSpirit.TAB_BAKING));

    public static final RegistryObject<Item> PEPPERMINT_LEAF =             regItem("peppermint_leaf", () -> new ItemBase(ChristmasSpirit.TAB_BAKING));
    public static final RegistryObject<Item> PEPPERMINT_CANDY_MIX =        regItem("peppermint_candy_mix", () -> new ItemBase(ChristmasSpirit.TAB_BAKING));

    public static final RegistryObject<Item> SUGAR_COOKIE_DOUGH =          regItem("sugar_cookie_dough", () -> new ItemBase(ChristmasSpirit.TAB_BAKING));
    public static final RegistryObject<Item> SUGAR_COOKIE_SHEET =          regItem("sugar_cookie_sheet", () -> new ItemBase(ChristmasSpirit.TAB_BAKING));
    public static final RegistryObject<Item> GINGER_GROUND =               regItem("ginger_ground", () -> new ItemBase(ChristmasSpirit.TAB_BAKING));
    public static final RegistryObject<Item> GINGERBREAD_DOUGH =           regItem("gingerbread_dough", () -> new ItemBase(ChristmasSpirit.TAB_BAKING));
    public static final RegistryObject<Item> GINGERBREAD_SHEET =           regItem("gingerbread_sheet", () -> new ItemBase(ChristmasSpirit.TAB_BAKING));

    public static final RegistryObject<Item> COOKIE_CUTTER_CIRCLE =        regItem("cookie_cutter_circle", ItemCookieCutter::new);
    public static final RegistryObject<Item> COOKIE_CUTTER_ORNAMENT =      regItem("cookie_cutter_ornament", ItemCookieCutter::new);
    public static final RegistryObject<Item> COOKIE_CUTTER_STAR =          regItem("cookie_cutter_star", ItemCookieCutter::new);
    public static final RegistryObject<Item> COOKIE_CUTTER_MAN =           regItem("cookie_cutter_man", ItemCookieCutter::new);
    public static final RegistryObject<Item> COOKIE_CUTTER_SNOWMAN =       regItem("cookie_cutter_snowman", ItemCookieCutter::new);

    public static final RegistryObject<Item> CHRISTMAS_LIGHT_WHITE =       regItem("christmas_light_white", ItemBase::new);
    public static final RegistryObject<Item> CHRISTMAS_LIGHT_RED =         regItem("christmas_light_red", ItemBase::new);
    public static final RegistryObject<Item> CHRISTMAS_LIGHT_GREEN =       regItem("christmas_light_green", ItemBase::new);
    public static final RegistryObject<Item> CHRISTMAS_LIGHT_BLUE =        regItem("christmas_light_blue", ItemBase::new);

    //FOOD
    public static final RegistryObject<Item> CHOCOLATE_BAR =               regItem("chocolate_bar", () -> new ItemFoodBase(2, 2, 1, false));
    public static final RegistryObject<Item> PEPPERMINT_BARK =             regItem("peppermint_bark", () -> new ItemFoodBase(4, 4, 2, false));

    public static final RegistryObject<Item> PEPPERMINT_CANDY_RED =        regItem("peppermint_candy_red", () -> new ItemFoodBase(2, 2, 2, false));
    public static final RegistryObject<Item> PEPPERMINT_CANDY_GREEN =      regItem("peppermint_candy_green", () -> new ItemFoodBase(2, 2, 2, false));
    public static final RegistryObject<Item> PEPPERMINT_CANDY_BLUE =       regItem("peppermint_candy_blue", () -> new ItemFoodBase(2, 2, 2, false));

    public static final RegistryObject<Block> CANDY_CANE_POST_RED =       regBlock("candy_cane_red", BlockCandyCanePost::new);
    public static final RegistryObject<Block> CANDY_CANE_POST_GREEN =     regBlock("candy_cane_green", BlockCandyCanePost::new);
    public static final RegistryObject<Block> CANDY_CANE_POST_BLUE =      regBlock("candy_cane_blue", BlockCandyCanePost::new);

    public static final RegistryObject<Item> CANDY_CANE_RED =              regItem("candy_cane_red", () -> new ItemCandyCane(CANDY_CANE_POST_RED.get()));
    public static final RegistryObject<Item> CANDY_CANE_GREEN =            regItem("candy_cane_green", () -> new ItemCandyCane(CANDY_CANE_POST_GREEN.get()));
    public static final RegistryObject<Item> CANDY_CANE_BLUE =             regItem("candy_cane_blue", () -> new ItemCandyCane(CANDY_CANE_POST_BLUE.get()));

    public static final RegistryObject<Item> SUGAR_COOKIE_SANTA =          regItem("sugar_cookie_santa", ItemSantaCookie::new);

    public static final RegistryObject<Item> SUGAR_COOKIE_CIRCLE =         regItem("sugar_cookie_circle", () -> new ItemFoodBase(5, 5, 2, false));
    public static final RegistryObject<Item> SUGAR_COOKIE_ORNAMENT =       regItem("sugar_cookie_ornament", () -> new ItemFoodBase(6, 6, 2, false));
    public static final RegistryObject<Item> SUGAR_COOKIE_STAR =           regItem("sugar_cookie_star", () -> new ItemFoodBase(7, 7, 2, false));
    public static final RegistryObject<Item> SUGAR_COOKIE_MAN =            regItem("sugar_cookie_man", () -> new ItemFoodBase(8, 8, 3, false));
    public static final RegistryObject<Item> SUGAR_COOKIE_SNOWMAN =        regItem("sugar_cookie_snowman", () -> new ItemFoodBase(9, 9, 3, false));

    public static final RegistryObject<Item> GINGERBREAD_COOKIE_CIRCLE =   regItem("gingerbread_cookie_circle", () -> new ItemFoodBase(6, 6, 2, false));
    public static final RegistryObject<Item> GINGERBREAD_COOKIE_ORNAMENT = regItem("gingerbread_cookie_ornament", () -> new ItemFoodBase(7, 7, 2, false));
    public static final RegistryObject<Item> GINGERBREAD_COOKIE_STAR =     regItem("gingerbread_cookie_star", () -> new ItemFoodBase(8, 8, 2, false));
    public static final RegistryObject<Item> GINGERBREAD_COOKIE_MAN =      regItem("gingerbread_cookie_man", () -> new ItemFoodBase(9, 9, 3, false));
    public static final RegistryObject<Item> GINGERBREAD_COOKIE_SNOWMAN =  regItem("gingerbread_cookie_snowman", () -> new ItemFoodBase(10, 10, 3, false));

    public static final RegistryObject<Item> MUG_MILK =                    regItem("mug_milk", () -> new ItemFoodBase(3, 3, 2, true));
    public static final RegistryObject<Item> MUG_HOT_CHOCOLATE =           regItem("mug_hot_chocolate", () -> new ItemFoodBase(6, 6, 2, true));
    public static final RegistryObject<Item> MUG_EGGNOG =                  regItem("mug_eggnog", () -> new ItemFoodBase(6, 6, 2, true));

    public static final RegistryObject<Item> SODA_COLA =                   regItem("soda_cola", () -> new ItemSoda(5, 5));
    public static final RegistryObject<Item> SODA_GINGER_ALE =             regItem("soda_ginger_ale", () -> new ItemSoda(6, 6));
    public static final RegistryObject<Item> SODA_SPRITE_CRANBERRY =       regItem("soda_sprite_cranberry", () -> new ItemSoda(8, 8));

    //WEARABLES
    public static final RegistryObject<Item> CHRISTMAS_HAT =               regItem("christmas_hat", () -> new ItemArmorBase(CSArmorTiers.CHRISTMAS_HAT, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> BEANIE_BLACK =                regItem("beanie_black", () -> new ItemArmorBase(CSArmorTiers.BEANIE_BLACK, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> BEANIE_RED =                  regItem("beanie_red", () -> new ItemArmorBase(CSArmorTiers.BEANIE_RED, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> BEANIE_GREEN =                regItem("beanie_green", () -> new ItemArmorBase(CSArmorTiers.BEANIE_GREEN, EquipmentSlotType.HEAD));

    public static final RegistryObject<Item> SWEATER_BLACK =               regItem("sweater_black", () -> new ItemArmorBase(CSArmorTiers.SWEATER_BLACK, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> SWEATER_RED =                 regItem("sweater_red", () -> new ItemArmorBase(CSArmorTiers.SWEATER_RED, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> SWEATER_GREEN =               regItem("sweater_green", () -> new ItemArmorBase(CSArmorTiers.SWEATER_GREEN, EquipmentSlotType.CHEST));

    public static final RegistryObject<Item> WINTER_JEANS =                regItem("winter_jeans", () -> new ItemArmorBase(CSArmorTiers.WINTER_JEANS, EquipmentSlotType.LEGS));

    public static final RegistryObject<Item> WINTER_BOOTS =                regItem("winter_boots", () -> new ItemArmorBase(CSArmorTiers.WINTER_BOOTS, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> ICE_SKATES =                  regItem("ice_skates", () -> new ItemArmorBase(CSArmorTiers.ICE_SKATES, EquipmentSlotType.FEET));

    //DISCS
    public static final RegistryObject<Item> DISC_WISHBACKGROUND =         regItem("disc_wishbackground", () -> new ItemDisc(InitSounds.WISHBACKGROUND, ChristmasSpirit.TAB_MAIN));
    public static final RegistryObject<Item> DISC_MCCHRISTMAS =            regItem("disc_mcchristmas", () -> new ItemDisc(InitSounds.MCCHRISTMAS, ChristmasSpirit.TAB_MAIN));
    public static final RegistryObject<Item> DISC_JARED =                  regItem("disc_jared", () -> new ItemDisc(InitSounds.JARED));

    //OTHER



    //------NAUGHTY ITEMS------\\
    public static final RegistryObject<Item> LUMP_OF_COAL =                regItem("lump_of_coal", () -> new ItemBase().setTag("naughty"));

    public static final RegistryObject<Item> FROST_INGOT =                 regItem("frost_ingot", () -> new ItemBase().setTag("naughty"));
    public static final RegistryObject<Item> FROST_HELMET =                regItem("frost_helmet", () -> new ItemFrostArmor(EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> FROST_CHESTPLATE =            regItem("frost_chestplate", () -> new ItemFrostArmor(EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> FROST_LEGGINGS =              regItem("frost_leggings", () -> new ItemFrostArmor(EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> FROST_BOOTS =                 regItem("frost_boots", () -> new ItemFrostArmor(EquipmentSlotType.FEET));

    public static final RegistryObject<Item> FROSTMOURNE =                 regItem("frostmourne", ItemFrostmourne::new);

    //------BLOCKS------\\

    public static final RegistryObject<Block> FRUITCAKE =              regBlockAndItem("fruitcake", ChristmasSpirit.TAB_BAKING, BlockFruitCake::new);

    public static final RegistryObject<Block> PRESENT_UNWRAPPED =      regBlockAndItem("present_unwrapped", ChristmasSpirit.TAB_MAIN, BlockPresentUnwrapped::new);

    public static final RegistryObject<Block> PRESENT_WRAPPED_RED =           regBlock("present_wrapped_red", BlockPresentWrapped::new);
    public static final RegistryObject<Item> PRESENT_WRAPPED_RED_ITEM =        regItem("present_wrapped_red", () -> new BlockItemPresentWrapped(PRESENT_WRAPPED_RED.get()));
    public static final RegistryObject<Block> PRESENT_WRAPPED_GREEN =         regBlock("present_wrapped_green", BlockPresentWrapped::new);
    public static final RegistryObject<Item> PRESENT_WRAPPED_GREEN_ITEM =      regItem("present_wrapped_green", () -> new BlockItemPresentWrapped(PRESENT_WRAPPED_GREEN.get()));
    public static final RegistryObject<Block> PRESENT_WRAPPED_BLUE =          regBlock("present_wrapped_blue", BlockPresentWrapped::new);
    public static final RegistryObject<Item> PRESENT_WRAPPED_BLUE_ITEM =       regItem("present_wrapped_blue", () -> new BlockItemPresentWrapped(PRESENT_WRAPPED_BLUE.get()));

    public static final RegistryObject<Block> STOCKING_RED =           regBlockAndItem("stocking_red", ChristmasSpirit.TAB_DECORATION, BlockStocking::new);
    public static final RegistryObject<Block> STOCKING_GREEN =         regBlockAndItem("stocking_green", ChristmasSpirit.TAB_DECORATION, BlockStocking::new);
    public static final RegistryObject<Block> STOCKING_BLUE =          regBlockAndItem("stocking_blue", ChristmasSpirit.TAB_DECORATION, BlockStocking::new);

    //CROPS
    public static final RegistryObject<Block> GINGER =                 regBlockAndItem("ginger", ChristmasSpirit.TAB_BAKING, BlockCropBase::new);
    public static final RegistryObject<Block> PEPPERMINT =             regBlockAndItem("peppermint", ChristmasSpirit.TAB_BAKING, () -> new BlockCropBase(PEPPERMINT_LEAF));

    //DECORATIONS
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_MULTICOLOR =            regBlockAndItem("christmas_lights_multicolor", ChristmasSpirit.TAB_DECORATION, BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_MULTICOLOR_FLICKERING = regBlockAndItem("christmas_lights_multicolor_flickering", ChristmasSpirit.TAB_DECORATION, BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_WHITE =                 regBlockAndItem("christmas_lights_white", ChristmasSpirit.TAB_DECORATION, BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_WHITE_FLICKERING =      regBlockAndItem("christmas_lights_white_flickering", ChristmasSpirit.TAB_DECORATION, BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_RED =                   regBlockAndItem("christmas_lights_red", ChristmasSpirit.TAB_DECORATION, BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_GREEN =                 regBlockAndItem("christmas_lights_green", ChristmasSpirit.TAB_DECORATION, BlockChristmasLights::new);
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS_BLUE =                  regBlockAndItem("christmas_lights_blue", ChristmasSpirit.TAB_DECORATION, BlockChristmasLights::new);

    public static final RegistryObject<Block> CANDY_CANE_BLOCK_RED =   regBlockAndItem("candy_cane_block_red", ChristmasSpirit.TAB_DECORATION, () -> new BlockBase(SoundType.NETHERRACK));
    public static final RegistryObject<Block> CANDY_CANE_BLOCK_GREEN = regBlockAndItem("candy_cane_block_green", ChristmasSpirit.TAB_DECORATION, () -> new BlockBase(SoundType.NETHERRACK));
    public static final RegistryObject<Block> CANDY_CANE_BLOCK_BLUE =  regBlockAndItem("candy_cane_block_blue", ChristmasSpirit.TAB_DECORATION, () -> new BlockBase(SoundType.NETHERRACK));

    public static final RegistryObject<Block> CANDY_CANE_BRICK_RED =   regBlockAndItem("candy_cane_brick_red", ChristmasSpirit.TAB_DECORATION, () -> new BlockBase(SoundType.NETHER_BRICK));
    public static final RegistryObject<Block> CANDY_CANE_BRICK_GREEN = regBlockAndItem("candy_cane_brick_green", ChristmasSpirit.TAB_DECORATION, () -> new BlockBase(SoundType.NETHER_BRICK));
    public static final RegistryObject<Block> CANDY_CANE_BRICK_BLUE =  regBlockAndItem("candy_cane_brick_blue", ChristmasSpirit.TAB_DECORATION, () -> new BlockBase(SoundType.NETHER_BRICK));

    public static final RegistryObject<Block> COOKIE_TRAY =            regBlockAndItem("cookie_tray", ChristmasSpirit.TAB_DECORATION, BlockCookieTray::new);
    public static final RegistryObject<Block> SNOW_GLOBE =             regBlockAndItem("snow_globe", ChristmasSpirit.TAB_DECORATION, BlockSnowGlobe::new);
    public static final RegistryObject<Block> REEF =                   regBlockAndItem("reef", ChristmasSpirit.TAB_DECORATION, BlockReef::new);
    public static final RegistryObject<Block> GARLAND =                regBlockAndItem("garland", ChristmasSpirit.TAB_DECORATION, BlockGarland::new);
    public static final RegistryObject<Block> GINGERBREAD_HOUSE =      regBlockAndItem("gingerbread_house", ChristmasSpirit.TAB_DECORATION, BlockGingerbreadHouse::new);
    public static final RegistryObject<Block> MISTLETOE =              regBlockAndItem("mistletoe", ChristmasSpirit.TAB_DECORATION, BlockMistletoe::new);
    public static final RegistryObject<Block> CHIMNEY =                regBlockAndItem("chimney", ChristmasSpirit.TAB_DECORATION, BlockChimney::new);
    public static final RegistryObject<Block> ICICLES =                regBlockAndItem("icicles", ChristmasSpirit.TAB_DECORATION, BlockIcicles::new);
    public static final RegistryObject<Block> FROSTED_GLASS =          regBlockAndItem("frosted_glass", ChristmasSpirit.TAB_DECORATION, BlockFrostedGlass::new);
    public static final RegistryObject<Block> FROSTED_GLASS_PANE =     regBlockAndItem("frosted_glass_pane", ChristmasSpirit.TAB_DECORATION, BlockFrostedGlassPane::new);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static RegistryObject<Item> regItem(String name, final Supplier<? extends Item> sup) {
        return ITEMS.register(name, sup);
    }

    public static RegistryObject<Block> regBlock(String name, final Supplier<? extends Block> sup) {
        return BLOCKS.register(name, sup);
    }

    public static RegistryObject<Block> regBlockAndItem(String name, ItemGroup tab, final Supplier<? extends Block> sup) {
        RegistryObject<Block> registryBlock = BLOCKS.register(name, sup);
        RegistryObject<Item> registryItem = ITEMS.register(name, () -> new BlockItemBase(registryBlock.get(), tab));
        return registryBlock;
    }
}
