package com.tm.cspirit.entity;

import com.tm.cspirit.init.InitEntityTypes;
import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityCandyCaneProjectile extends AbstractArrowEntity {

    private static final DataParameter<Byte> CANDY_TYPE = EntityDataManager.createKey(AbstractArrowEntity.class, DataSerializers.BYTE);

    public EntityCandyCaneProjectile(EntityType<? extends AbstractArrowEntity> type, World world) {
        super(type, world);
    }

    public EntityCandyCaneProjectile(World world, PlayerEntity shooter, byte candyCaneType) {
        super(InitEntityTypes.CANDY_CANE_PROJECTILE.get(), shooter, world);
        dataManager.set(CANDY_TYPE, candyCaneType);
        setDamage(2.5F);
        ticksExisted -= (20 * 60);
    }

    public byte getCandyType() {
        return dataManager.get(CANDY_TYPE);
    }

    public ItemStack getCandyCaneStack() {
        ItemStack cane = new ItemStack(InitItems.CANDY_CANE_RED.get());

        if (getCandyType() == 1) cane = new ItemStack(InitItems.CANDY_CANE_GREEN.get());
        else if (getCandyType() == 2) cane = new ItemStack(InitItems.CANDY_CANE_BLUE.get());

        return cane;
    }

    @Override
    protected SoundEvent getHitEntitySound() {
        return SoundEvents.BLOCK_LANTERN_BREAK;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putByte("candy_type", dataManager.get(CANDY_TYPE));
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        dataManager.set(CANDY_TYPE, compound.getByte("candy_type"));
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(CANDY_TYPE, (byte)0);
    }

    @Override
    protected ItemStack getArrowStack() {
        return getCandyCaneStack();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
