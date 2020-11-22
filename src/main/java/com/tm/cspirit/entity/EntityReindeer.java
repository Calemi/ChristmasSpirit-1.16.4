package com.tm.cspirit.entity;

import com.tm.cspirit.init.InitEntityTypes;
import com.tm.cspirit.main.CSConfig;
import com.tm.cspirit.main.ChristmasSpirit;
import com.tm.cspirit.packet.PacketReindeerJump;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityReindeer extends HorseEntity implements IFlyingAnimal {

    public static final DataParameter<Boolean> JUMP_KEY = EntityDataManager.createKey(EntityReindeer.class, DataSerializers.BOOLEAN);

    public EntityReindeer(EntityType<? extends HorseEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityReindeer(World world, double x, double y, double z) {
        this(InitEntityTypes.REINDEER.get(), world);
        this.setPosition(x, y, z);
        this.setMotion(Vector3d.ZERO);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.2D)
                .createMutableAttribute(Attributes.HORSE_JUMP_STRENGTH, 1);
    }

    @Override
    public void tick() {
        
        if (CSConfig.misc.reindeerFlying.get()) {

            addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 20, 0, true, false));
            
            if (world.isRemote && getControllingPassenger() != null && getControllingPassenger() instanceof PlayerEntity) {

                ClientPlayerEntity player = (ClientPlayerEntity) getControllingPassenger();

                if (isHorseSaddled()) {

                    if (player.movementInput.jump && !dataManager.get(JUMP_KEY)) {
                        ChristmasSpirit.network.sendToServer(new PacketReindeerJump(true));
                        dataManager.set(JUMP_KEY, true);
                    }

                    else if (dataManager.get(JUMP_KEY)) {
                        ChristmasSpirit.network.sendToServer(new PacketReindeerJump(false));
                        dataManager.set(JUMP_KEY, false);
                    }
                }
            }

            if (dataManager.get(JUMP_KEY)) {
                addVelocity(0, 0.2F, 0);
            }
        }

        super.tick();
    }

    @Override
    public void travel(Vector3d travelVector) {

        if (CSConfig.misc.reindeerFlying.get()) {

            if (!onGround) {
                setAIMoveSpeed((float) getAttributeValue(Attributes.MOVEMENT_SPEED) * 5);
            } 
            
            else setAIMoveSpeed((float) getAttributeValue(Attributes.MOVEMENT_SPEED));
        }
        
        super.travel(travelVector);
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {

        if (!CSConfig.misc.reindeerFlying.get()) {

            if (distance > 1.0F) {
                playSound(SoundEvents.ENTITY_HORSE_LAND, 0.4F, 1.0F);
            }

            return false;
        }

        else return super.onLivingFall(distance, damageMultiplier);
    }

    @Override
    public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity mate) {
        AbstractHorseEntity reindeer = InitEntityTypes.REINDEER.get().create(world);
        setOffspringAttributes(mate, reindeer);
        return reindeer;
    }

    @Override
    public boolean canJump() {

        if (!CSConfig.misc.reindeerFlying.get()) {
            return super.canJump();
        }

        return false;
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(JUMP_KEY, false);
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
        dataManager.set(JUMP_KEY, nbt.getBoolean("Jump"));
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);
        nbt.putBoolean("Jump", dataManager.get(JUMP_KEY));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

