package com.tm.cspirit.data;

import com.google.gson.reflect.TypeToken;
import com.tm.cspirit.util.helper.FileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class SantaGiftListFile {

    public static Map<String, GiftEntry> santaGiftList = new HashMap<>();

    public static void init() {
        santaGiftList = FileHelper.readFileOrCreate("SantaGiftList", getDefaults(), new TypeToken<Map<String, GiftEntry>>(){});
    }

    private static Map<String, GiftEntry> getDefaults() {
        Map<String, GiftEntry> ret = new HashMap<>();

        addDefault(ret, "WoodLogs", "minecraft:oak_log",                              32, 0, 0, 5);
        addDefault(ret, "Leather", "minecraft:leather",                               16, 0, 0, 15);
        addDefault(ret, "IronIngot", "minecraft:iron_ingot",                          16, 0, 0, 15);
        addDefault(ret, "Peppermint", "cspirit:peppermint",                           8, 0, 0, 5);
        addDefault(ret, "Ginger", "cspirit:ginger",                                   8, 0, 0, 5);
        addDefault(ret, "SugarCookieCircle", "cspirit:sugar_cookie_circle",           8, 0, 3, 15);
        addDefault(ret, "SugarCookieOrnament", "cspirit:sugar_cookie_ornament",       8, 0, 4, 15);
        addDefault(ret, "SugarCookieStar", "cspirit:sugar_cookie_star",               8, 0, 5, 15);
        addDefault(ret, "GingerbreadCircle", "cspirit:gingerbread_cookie_circle",     8, 0, 3, 15);
        addDefault(ret, "GingerbreadOrnament", "cspirit:gingerbread_cookie_ornament", 8, 0, 4, 15);
        addDefault(ret, "GingerbreadStar", "cspirit:gingerbread_cookie_star",         8, 0, 5, 15);

        addDefault(ret, "SugarCookieMan", "cspirit:sugar_cookie_man",                 8, 0, 6, 15);
        addDefault(ret, "SugarCookieSnowman", "cspirit:sugar_cookie_snowman",         8, 0, 6, 15);
        addDefault(ret, "GingerbreadMan", "cspirit:gingerbread_cookie_man",           8, 0, 6, 15);
        addDefault(ret, "GingerbreadSnowman", "cspirit:gingerbread_cookie_snowman",   8, 0, 6, 15);
        addDefault(ret, "CookieCutterOrnament", "cspirit:cookie_cutter_ornament",     1, 1, 6, 15);
        addDefault(ret, "CookieCutterStar", "cspirit:cookie_cutter_star",             1, 1, 7, 15);

        addDefault(ret, "CookieCutterMan", "cspirit:cookie_cutter_man",               1, 2, 8, 15);

        addDefault(ret, "CookieCutterSnowman", "cspirit:cookie_cutter_snowman",       1, 3, 8, 15);
        addDefault(ret, "DiscWishBackground", "cspirit:disc_wishbackground",          1, 3, 0, 15);
        addDefault(ret, "DiscMCChristmas", "cspirit:disc_mcchristmas",                1, 3, 15, 32);

        addDefault(ret, "Diamond", "minecraft:diamond",                               16, 4, 0, 15);
        addDefault(ret, "NetheriteIngot", "minecraft:netherite_ingot",                1, 4, 10, 32);
        addDefault(ret, "Elytra", "minecraft:elytra",                                 1, 4, 20, 32);
        addDefault(ret, "Santa Cookie", "cspirit:sugar_cookie_santa",                 1, 4, 0, 32);

        return ret;
    }

    private static void addDefault(Map<String, GiftEntry> ret, String entryName, String stackStr, int maxAmount, int rarityIndex, int minDay, int maxDay) {
        ret.put(entryName, new GiftEntry(stackStr, maxAmount, rarityIndex, minDay, maxDay));
    }

    public static class GiftEntry {

        public final String stackStr;
        public final int maxAmount;
        public final int rarityIndex;
        public final int minDay;
        public final int maxDay;

        GiftEntry(String stackStr, int maxAmount, int rarityIndex, int minDay, int maxDay) {
            this.stackStr = stackStr;
            this.maxAmount = maxAmount;
            this.rarityIndex = rarityIndex;
            this.minDay = minDay;
            this.maxDay = maxDay;
        }

        public ItemStack getGiftStack() {
            return new ItemStack(Registry.ITEM.getOrDefault(new ResourceLocation(stackStr)));
        }

        public static GiftEntry readFromNBT(CompoundNBT nbt) {
            return new GiftEntry(nbt.getString("stackStr"), nbt.getInt("maxAmount"), nbt.getInt("rarityIndex"), nbt.getInt("minDay"), nbt.getInt("maxDay"));
        }

        public CompoundNBT writeToNBT() {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("stackStr", stackStr);
            nbt.putInt("maxAmount", maxAmount);
            nbt.putInt("rarityIndex", rarityIndex);
            nbt.putInt("minDay", minDay);
            nbt.putInt("maxDay", maxDay);
            return nbt;
        }
    }
}