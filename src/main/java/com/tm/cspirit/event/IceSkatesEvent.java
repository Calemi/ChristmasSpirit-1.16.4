package com.tm.cspirit.event;

import com.tm.cspirit.init.InitItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class IceSkatesEvent {

    @SubscribeEvent
    public void onSkate(LivingEvent.LivingUpdateEvent event) {

        World world = event.getEntity().getEntityWorld();
        LivingEntity entity = event.getEntityLiving();

        ItemStack bootsStack = entity.getItemStackFromSlot(EquipmentSlotType.FEET);

        if (!entity.isCrouching() && !entity.isPassenger()) {

            if (bootsStack.getItem() == InitItems.ICE_SKATES.get()) {

                BlockPos pos = new BlockPos(entity.getPositionVec().x, entity.getBoundingBox().minY - 0.5D, entity.getPositionVec().z);

                double slipperiness = world.getBlockState(pos).getSlipperiness(world, pos, entity);

                if (slipperiness > 0.7D) {
                    Objects.requireNonNull(entity.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(entity.isSprinting() ? 0.4F : 0.2F);
                    return;
                }
            }
        }

        Objects.requireNonNull(entity.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.1F);
    }
}