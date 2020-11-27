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
        public final ForgeConfigSpec.ConfigValue<Boolean> naughtyItems;
        public final ForgeConfigSpec.ConfigValue<Boolean> reindeerFlying;
        public final ForgeConfigSpec.ConfigValue<Integer> reindeerSpawnWeight;

        public CategoryMisc (ForgeConfigSpec.Builder builder) {

            builder.push("Misc");

            mobArmorRarity = builder.comment("Mob Armor Rarity", NEEDED_FOR_SERVERS, "1 in x chance when a zombie or skeleton spawns to give them clothing and a present.").defineInRange("mobArmorRarity", 50, 0, 256);
            naughtyItems = builder.comment("Naughty Items", NEEDED_FOR_SERVERS, "When false, any Naughty Item in a player's inventory will be deleted.").define("naughtyItems", true);
            reindeerFlying = builder.comment("Reindeer Flying", NEEDED_FOR_SERVERS, "When false, can't fly.").define("reindeerFlying", true);
            reindeerSpawnWeight = builder.comment("Reindeer Spawn Weight", NEEDED_FOR_SERVERS, "The higher the value is, the more frequent it spawns.").defineInRange("reindeerSpawnWeight", 1, 1, 256);

            builder.pop();
        }
    }
}