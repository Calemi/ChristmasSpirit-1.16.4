package com.tm.cspirit.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SantaGiftListFile {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    public static Map<String, GiftEntry> santaGiftList;

    public static void init() {
        santaGiftList = createFile(getDefaults());
    }

    private static Map<String, GiftEntry> createFile(Map<String, GiftEntry> defaultItemsList) {

        String path = FMLPaths.CONFIGDIR.get().toString();
        File jsonConfig = new File(path, "SantaGiftList.json");

        try {

            // Create the config if it doesn't already exist.
            if (!jsonConfig.exists() && jsonConfig.createNewFile()) {

                // Get a default map of blocks. You could just use a blank map, however.
                // Convert the map to JSON format. There is a built in (de)serializer for it already.
                String json = gson.toJson(defaultItemsList, new TypeToken<Map<String, GiftEntry>>(){}.getType());
                FileWriter writer = new FileWriter(jsonConfig);
                // Write to the file you passed
                writer.write(json);
                // Always close when done.
                writer.close();
            }

            // If the file exists (or we just made one exist), convert it from JSON format to a populated Map object
            return gson.fromJson(new FileReader(jsonConfig), new TypeToken<Map<String, GiftEntry>>(){}.getType());
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Map<String, GiftEntry> getDefaults() {
        Map<String, GiftEntry> ret = new HashMap<>();

        addDefault(ret, "Peppermint", "cspirit:peppermint", 8, 0, 0, 5);
        addDefault(ret, "Ginger", "cspirit:ginger", 8, 0, 0, 5);
        addDefault(ret, "SugarCookieCircle", "cspirit:sugar_cookie_circle", 8, 0, 3, 15);
        addDefault(ret, "SugarCookieOrnament", "cspirit:sugar_cookie_ornament", 8, 0, 4, 15);
        addDefault(ret, "IronIngot", "minecraft:iron_ingot", 16, 0, 0, 15);

        addDefault(ret, "CookieCutterOrnament", "cspirit:cookie_cutter_ornament", 1, 1, 6, 15);
        addDefault(ret, "CookieCutterStar", "cspirit:cookie_cutter_star", 1, 1, 7, 15);

        addDefault(ret, "CookieCutterMan", "cspirit:cookie_cutter_man", 1, 2, 8, 15);

        addDefault(ret, "CookieCutterSnowman", "cspirit:cookie_cutter_snowman", 1, 3, 8, 15);

        addDefault(ret, "Diamond", "minecraft:diamond", 16, 4, 0, 15);
        addDefault(ret, "NetheriteIngot", "minecraft:netherite_ingot", 1, 4, 10, 32);

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