package com.tm.cspirit.item.tier;

import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.main.CSReference;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum CSArmorTiers implements IArmorMaterial {

    CHRISTMAS_HAT ("christmas_hat", ArmorMaterial.LEATHER,  100),
    BEANIE_BLACK("beanie_black", ArmorMaterial.LEATHER,100),
    BEANIE_RED("beanie_red", ArmorMaterial.LEATHER,100),
    BEANIE_GREEN("beanie_green", ArmorMaterial.LEATHER, 100),
    SWEATER_BLACK("sweater_black", ArmorMaterial.LEATHER,100),
    SWEATER_RED("sweater_red", ArmorMaterial.LEATHER,150),
    SWEATER_GREEN("sweater_green", ArmorMaterial.LEATHER,150),
    WINTER_JEANS("winter_jeans", ArmorMaterial.LEATHER,150),
    WINTER_BOOTS("winter_boots", ArmorMaterial.LEATHER,150),
    ICE_SKATES("ice_skates", ArmorMaterial.LEATHER, 200),
    FROST("frost_armor", ArmorMaterial.DIAMOND, () -> {return Ingredient.fromItems(InitItems.FROST_INGOT.get());}, 250, 1);

    private final IArmorMaterial base;
    private final String name;
    private final LazyValue<Ingredient> repairMaterial;
    private final int durabilityAddition;
    private final int protectionAddition;

    CSArmorTiers(String name, IArmorMaterial base, Supplier<Ingredient> repairMaterial, int durabilityAddition, int protectionAddition) {
        this.base = base;
        this.name = name;
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.durabilityAddition = durabilityAddition;
        this.protectionAddition = protectionAddition;
    }

    CSArmorTiers(String name, IArmorMaterial base, int durabilityAddition) {
        this(name, base, base::getRepairMaterial, durabilityAddition, 0);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return CSReference.MOD_ID + ":" + name;
    }

    @Override
    public int getDurability(EquipmentSlotType slot) {
        return base.getDurability(slot) + durabilityAddition;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slot) {
        return base.getDamageReductionAmount(slot) + protectionAddition;
    }

    @Override
    public int getEnchantability() {
        return base.getEnchantability();
    }

    @Override
    public SoundEvent getSoundEvent() {
        return base.getSoundEvent();
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.getValue();
    }

    @Override
    public float getToughness() {
        return base.getToughness();
    }

    @Override
    public float getKnockbackResistance() {
        return base.getKnockbackResistance();
    }
}
