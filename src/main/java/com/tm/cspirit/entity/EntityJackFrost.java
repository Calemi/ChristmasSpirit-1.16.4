package com.tm.cspirit.entity;

import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.init.InitItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.UUID;

public class EntityJackFrost extends CreatureEntity implements IAngerable {

    private int attackTimer;

    private static final RangedInteger randomTime = TickRangeConverter.convertRange(20, 39);
    private int angerTime;
    private UUID angerTarget;

    public EntityJackFrost(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
        setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(InitItems.FROSTMOURNE.get()));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 40D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::func_233680_b_));
        this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, false));
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }

        if (!this.world.isRemote) {
            this.func_241359_a_((ServerWorld)this.world, true);
        }

        int radius = 20;

        List<PlayerEntity> closePlayers = world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(getPosition().getX() - radius, getPosition().getY() - radius, getPosition().getZ() - radius, getPosition().getX() + radius, getPosition().getY() + radius, getPosition().getZ() + radius));

        for (PlayerEntity player : closePlayers) {

            if (!NaughtyListFile.isOnNaughtyList(player)) {
                setAngerTarget(player.getUniqueID());
                break;
            }
        }
    }

    @Override
    public int getAngerTime() {
        return angerTime;
    }

    @Override
    public void setAngerTime(int time) {
        angerTime = time;
    }

    @Override
    public UUID getAngerTarget() {
        return angerTarget;
    }

    @Override
    public void setAngerTarget(UUID target) {
        angerTarget = target;
    }

    @Override
    public void func_230258_H__() {
        this.setAngerTime(randomTime.getRandomWithinRange(this.rand));
    }

    @Override
    public boolean func_233680_b_(LivingEntity p_233680_1_) {

        if (!EntityPredicates.CAN_HOSTILE_AI_TARGET.test(p_233680_1_)) {
            return false;
        }

        else {
            return p_233680_1_.getType() == EntityType.PLAYER && this.func_241357_a_(p_233680_1_.world) || p_233680_1_.getUniqueID().equals(this.getAngerTarget());
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PILLAGER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_PILLAGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PILLAGER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 1);
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 40;
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }
}
