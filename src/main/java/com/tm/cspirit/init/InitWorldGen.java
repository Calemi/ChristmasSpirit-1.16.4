package com.tm.cspirit.init;

import com.google.common.collect.ImmutableSet;
import com.tm.cspirit.main.CSConfig;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.reflect.FieldUtils;

public class InitWorldGen {

    public static final ConfiguredFeature<?, ?> WINTER_GARDEN =  Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(InitItems.WINTER_GARDEN.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Features.States.GRASS_BLOCK.getBlock())).func_227317_b_().build()).withPlacement(Features.Placements.PATCH_PLACEMENT).chance(16);

    public static void init() {
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,"cspirit:winter_garden", WINTER_GARDEN);
        if (CSConfig.worldGen.freezeWorld.get()) {
            DeferredWorkQueue.runLater(() -> freezeBiomes("field_242423_j", "field_242526_h", "field_235052_p_"));
            DeferredWorkQueue.runLater(() -> freezeBiomes("climate", "grassColorModifier", "effects"));
        }
    }

    private static void freezeBiomes(String climateVar, String grassColorModifierVar, String effectsVar) {

        for (Biome biome : ForgeRegistries.BIOMES) {

            if (!CSConfig.worldGen.freezeOceans.get()) {

                if (biome.getRegistryName() != null && biome.getRegistryName().toString().contains("ocean")) {
                    continue;
                }
            }

            Biome.Climate climate = new Biome.Climate(Biome.RainType.SNOW, -1, Biome.TemperatureModifier.NONE, 1);
            BiomeAmbience.GrassColorModifier colorModifier = BiomeAmbience.GrassColorModifier.NONE;
            BiomeAmbience ambience = biome.getAmbience();

            try {
                FieldUtils.writeField(biome, climateVar, climate, true);
                FieldUtils.writeField(ambience, grassColorModifierVar, colorModifier, true);
                FieldUtils.writeField(biome, effectsVar, ambience, true);
            }

            catch (IllegalAccessException ignored) {}
        }
    }
}
