package com.tm.cspirit.event;

import com.tm.cspirit.init.InitItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IceSkatesEvent {

    @SubscribeEvent
    public void onSkate(LivingEvent.LivingUpdateEvent event) {

        World world = event.getEntity().getEntityWorld();
        LivingEntity entity = event.getEntityLiving();

        ItemStack bootsStack = entity.getItemStackFromSlot(EquipmentSlotType.FEET);

        if (!entity.isCrouching()) {

            if (bootsStack.getItem() == InitItems.ICE_SKATES.get()) {

                BlockPos pos = new BlockPos(entity.getPositionVec().x, entity.getBoundingBox().minY - 0.5D, entity.getPositionVec().z);

                double slipperiness = world.getBlockState(pos).getSlipperiness(world, pos, entity);

                if (slipperiness > 0.7D) {

                    float movementMultiplier = 2;

                    if (entity.isSprinting()) {
                        movementMultiplier = 4;
                    }

                    Vector3d movement = new Vector3d(entity.getMotion().x * movementMultiplier, entity.getMotion().y, entity.getMotion().z * movementMultiplier);
                    entity.move(MoverType.SELF, movement);
                }
            }
        }
    }
}