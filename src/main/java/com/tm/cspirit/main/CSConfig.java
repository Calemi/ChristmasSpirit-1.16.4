package com.tm.cspirit.main;

import net.minecraftforge.common.ForgeConfigSpec;

public class CSConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final CategoryWorldGen worldGen = new CategoryWorldGen(BUILDER);
    public static final CategoryMisc misc = new CategoryMisc(BUILDER);

    public static final ForgeConfigSpec spec = BUILDER.build();

    private static final String NEEDED_FOR_SERVERS = "(Only needed on servers)";

    public static class CategoryWorldGen {

        public final ForgeConfigSpec.ConfigValue<Boolean> freezeWorld;
        public final ForgeConfigSpec.ConfigValue<Boolean> freezeOceans;

        public CategoryWorldGen (ForgeConfigSpec.Builder builder) {

            builder.push("WorldGen");

            freezeWorld = builder.comment("Freeze World", NEEDED_FOR_SERVERS, "Causes all biomes to snow. (excluding oceans)").define("freezeWorld", true);
            freezeOceans = builder.comment("Freeze Oceans", NEEDED_FOR_SERVERS, "Causes all oceans to freeze as well.", "Only works if freezeWorld set to true").define("freezeOceans", false);

            builder.pop();
        }
    }

    public static class CategoryMisc {

        public final ForgeConfigSpec.ConfigValue<Integer> mobArmorRarity;

        public CategoryMisc (ForgeConfigSpec.Builder builder) {

            builder.push("Misc");

            mobArmorRarity = builder.comment("Mob Armor Rarity", NEEDED_FOR_SERVERS, "Determines how rare of a chance for Zombies & Skeletons to wear warm cloths", "The higher the value, the more rare").defineInRange("mobArmorRarity", 10, 0, 256);

            builder.pop();
        }
    }
}