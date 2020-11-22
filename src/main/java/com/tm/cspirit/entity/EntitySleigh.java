package com.tm.cspirit.entity;

import com.tm.cspirit.init.InitEntityTypes;
import com.tm.cspirit.init.InitItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

public class EntitySleigh extends BoatEntity {

    private static final int MAX_PASSENGERS = 4;

    public EntitySleigh(EntityType<? extends BoatEntity> type, World world) {
        super(type, world);
        stepHeight = 1.0F;
    }

    public EntitySleigh(World world, double x, double y, double z) {
        this(InitEntityTypes.SLEIGH.get(), world);
        this.setPosition(x, y, z);
        this.setMotion(Vector3d.ZERO);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    @Override
    public void tick() {

        if (this.canPassengerSteer()) {
            this.move(MoverType.SELF, this.getMotion().mul(4, 1, 4));
        }

        else {
            this.setMotion(Vector3d.ZERO);
        }

        super.tick();

        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow(0.2F, -0.01F, 0.2F), EntityPredicates.pushableBy(this));

        if (!list.isEmpty()) {

            boolean flag = !this.world.isRemote && !(this.getControllingPassenger() instanceof PlayerEntity);

            for (Entity entity : list) {

                if (!entity.isPassenger(this)) {

                    if (flag && this.getPassengers().size() < MAX_PASSENGERS && !entity.isPassenger() && entity.getWidth() < this.getWidth() && entity instanceof LivingEntity && !(entity instanceof WaterMobEntity) && !(entity instanceof PlayerEntity)) {
                        entity.startRiding(this);
                    }

                    else this.applyEntityCollision(entity);
                }
            }
        }
    }

    public void updatePassenger(Entity passenger) {

        if (this.isPassenger(passenger)) {

            double passengerOffsetX = 0.0F;
            double passengerOffsetZ = 0.0F;
            float f1 = (float)((this.removed ? (double)0.01F : this.getMountedYOffset()) + passenger.getYOffset());

            if (getPassengers().indexOf(passenger) == 0) passengerOffsetX = -0.39D;
            if (getPassengers().size() > 1 && getPassengers().indexOf(passenger) == 1) passengerOffsetX = 0.39D;

            if (getPassengers().size() > 2 && getPassengers().indexOf(passenger) == 2) {
                passengerOffsetX = -0.39D;
                f1 += 0.4D;
                passengerOffsetZ = -1.0D;
            }
            if (getPassengers().size() > 3 && getPassengers().indexOf(passenger) == 3) {
                passengerOffsetX = 0.39D;
                f1 += 0.4D;
                passengerOffsetZ = -1.0D;
            }

            Vector3d vector3d = (new Vector3d(-0.5D + passengerOffsetZ, 0D, passengerOffsetX)).rotateYaw(-this.rotationYaw * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
            passenger.setPosition(this.getPosX() + vector3d.x, this.getPosY() + (double)f1, this.getPosZ() + vector3d.z);

            passenger.rotationYaw += (rotationYaw - prevRotationYaw);
            passenger.setRotationYawHead(passenger.getRotationYawHead() + (rotationYaw - prevRotationYaw));

            this.applyYawToEntity(passenger);
        }
    }

    @Override
    public double getMountedYOffset() {
        return 0.85D;
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {

        if (!this.isPassenger()) {

            if (onGroundIn) {
                this.fallDistance = 0.0F;
            }

            else if (!this.world.getFluidState(this.getPosition().down()).isTagged(FluidTags.WATER) && y < 0.0D) {
                this.fallDistance = (float)((double)this.fallDistance - y);
            }
        }
    }

    @Override
    public Item getItemBoat() {
        return InitItems.SLEIGH.get();
    }

    @Override
    protected boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < MAX_PASSENGERS && !this.areEyesInFluid(FluidTags.WATER);
    }

    @Override
    public float getBoatGlide() {
        return 0.8F;
    }

    @Override
    public boolean canSwim() {
        return false;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
