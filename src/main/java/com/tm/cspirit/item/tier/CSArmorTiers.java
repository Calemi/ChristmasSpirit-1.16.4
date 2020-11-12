package com.tm.cspirit.item.tier;

import com.tm.cspirit.main.CSReference;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum CSArmorTiers implements IArmorMaterial {

    CHRISTMAS_HAT (CSReference.MOD_ID + ":christmas_hat", 5, new int[]{2, 5, 6, 3}, 15, 0.0F, () -> {return Ingredient.fromItems(Items.LEATHER);}),
    BEANIE_BLACK(CSReference.MOD_ID + ":beanie_black", 5, new int[]{2, 5, 6, 3}, 15, 0.0F, () -> {return Ingredient.fromItems(Items.LEATHER);}),
    BEANIE_RED(CSReference.MOD_ID + ":beanie_red", 5, new int[]{2, 5, 6, 3}, 15, 0.0F, () -> {return Ingredient.fromItems(Items.LEATHER);}),
    BEANIE_GREEN(CSReference.MOD_ID + ":beanie_green", 5, new int[]{2, 5, 6, 3}, 15, 0.0F, () -> {return Ingredient.fromItems(Items.LEATHER);}),
    SWEATER_BLACK(CSReference.MOD_ID + ":sweater_black", 5, new int[]{2, 5, 6, 3}, 15, 0.0F, () -> {return Ingredient.fromItems(Items.LEATHER);}),
    SWEATER_RED(CSReference.MOD_ID + ":sweater_red", 5, new int[]{2, 5, 6, 3}, 15, 0.0F, () -> {return Ingredient.fromItems(Items.LEATHER);}),
    SWEATER_GREEN(CSReference.MOD_ID + ":sweater_green", 5, new int[]{2, 5, 6, 3}, 15, 0.0F, () -> {return Ingredient.fromItems(Items.LEATHER);}),
    WINTER_JEANS(CSReference.MOD_ID + ":winter_jeans", 5, new int[]{2, 5, 6, 3}, 15, 0.0F, () -> {return Ingredient.fromItems(Items.LEATHER);}),
    WINTER_BOOTS(CSReference.MOD_ID + ":winter_boots", 5, new int[]{2, 5, 6, 3}, 15, 0.0F, () -> {return Ingredient.fromItems(Items.LEATHER);});

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{11, 16, 15, 13};
    private final String name;
    private final int maxDamageFactor;
    private final int[] reductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;

    CSArmorTiers(String name, int maxDamageFactor, int[] reductionAmountArray, int enchantability, float toughness, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.reductionAmountArray = reductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.reductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
