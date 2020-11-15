package com.tm.cspirit.event;

import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.util.helper.EffectHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FrozenEvent {

    @SubscribeEvent
    public void onEntityKill(LivingHurtEvent event) {

        if (event.getSource().getTrueSource() instanceof LivingEntity) {

            LivingEntity source = (LivingEntity)event.getSource().getTrueSource();

            boolean hasHelmet = event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(InitItems.FROST_HELMET.get());
            boolean hasChestplate = event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(InitItems.FROST_CHESTPLATE.get());
            boolean hasLeggings = event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(InitItems.FROST_LEGGINGS.get());
            boolean hasBoots = event.getEntityLiving().getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(InitItems.FROST_BOOTS.get());

            if (hasHelmet && hasChestplate && hasLeggings && hasBoots) {

                EffectHelper.giveFrozenEffect(source, 2);
            }
        }
    }
}
