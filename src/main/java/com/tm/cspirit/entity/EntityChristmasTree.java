package com.tm.cspirit.entity;

import com.tm.cspirit.entity.data.CSDataSerializers;
import com.tm.cspirit.init.InitEntityTypes;
import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.helper.EffectHelper;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

public class EntityChristmasTree extends Entity {

    private static final DataParameter<NonNullList<ItemStack>> INVENTORY = EntityDataManager.createKey(EntityChristmasTree.class, CSDataSerializers.ITEMSTACK_ARRAY_4);
    private static final DataParameter<Boolean> WHITE = EntityDataManager.createKey(EntityChristmasTree.class, DataSerializers.BOOLEAN);

    public EntityChristmasTree(EntityType<? extends EntityChristmasTree> type, World world) {
        super(type, world);
    }

    public EntityChristmasTree(World world, Vector3d position, float yaw, boolean isWhite) {
        this(InitEntityTypes.CHRISTMAS_TREE.get(), world);
        setLocationAndAngles(position.x, position.y, position.z, yaw, 0);
        dataManager.set(WHITE, isWhite);
    }

    public boolean isWhite() {
        return dataManager.get(WHITE);
    }

    public ItemStack getItemStackFromSlot(EquipmentSlotType slotIn) {

        if (slotIn.getSlotType() == EquipmentSlotType.Group.ARMOR) {
            return dataManager.get(INVENTORY).get(slotIn.getIndex());
        }

        return ItemStack.EMPTY;
    }

    public ItemStack getItemStackFromID (int id) {
        return getItemStackFromSlot(getSlotTypeFromID(id));
    }

    public EquipmentSlotType getSlotTypeFromID(int id) {

        switch (id) {
            case 0: return EquipmentSlotType.FEET;
            case 1: return EquipmentSlotType.LEGS;
            case 2: return EquipmentSlotType.CHEST;
        }

        return EquipmentSlotType.HEAD;
    }

    public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {

        if (slotIn.getSlotType() == EquipmentSlotType.Group.ARMOR) {

            NonNullList<ItemStack> newInv = NonNullList.create();
            newInv.addAll(dataManager.get(INVENTORY));
            newInv.set(slotIn.getIndex(), stack);
            dataManager.set(INVENTORY, newInv);
        }
    }

    public boolean hasStar() {
        return !getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty();
    }

    private boolean addDecoration(ItemStack stack) {

        ItemStack copiedStack = stack.copy();
        copiedStack.setCount(1);

        boolean added = false;

        if (!hasStar()) {
            setItemStackToSlot(EquipmentSlotType.HEAD, copiedStack);
            return true;
        }

        for (int i = 0; i < 4; i++) {

            if (getItemStackFromID(i).isEmpty()) {
                setItemStackToSlot(getSlotTypeFromID(i), copiedStack);
                added = true;
                break;
            }
        }

        return added;
    }

    private ItemStack removeDecoration() {

        for (int i = 2; i >= 0; i--) {

            ItemStack removedStack = getItemStackFromID(i);

            if (!removedStack.isEmpty()) {
                setItemStackToSlot(getSlotTypeFromID(i), ItemStack.EMPTY);
                return removedStack;
            }
        }

        if (hasStar()) {
            ItemStack removedStack = getItemStackFromSlot(EquipmentSlotType.HEAD);
            setItemStackToSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
            return removedStack;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public void tick() {
        super.tick();

        int radius = 10;

        int stack = 1;

        if (hasStar() && !getItemStackFromSlot(EquipmentSlotType.CHEST).isEmpty() && !getItemStackFromSlot(EquipmentSlotType.LEGS).isEmpty() && !getItemStackFromSlot(EquipmentSlotType.FEET).isEmpty()) {
            stack = 2;
        }

        List<PlayerEntity> closePlayers = world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(getPosition().getX() - radius, getPosition().getY() - radius, getPosition().getZ() - radius, getPosition().getX() + radius, getPosition().getY() + radius, getPosition().getZ() + radius));

        for (PlayerEntity player : closePlayers) {
            EffectHelper.giveHolidaySpiritStackEffect(player, stack, 30);
        }
    }

    @Override
    public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) {

        ItemStack stack = player.getHeldItem(hand);

        if (!stack.isEmpty()) {

            boolean added = addDecoration(stack);

            if (added) {
                stack.shrink(1);
            }
        }

        else {

            ItemStack removedStack = removeDecoration();

            if (!removedStack.isEmpty()) {
                ItemHelper.spawnStackAtEntity(world, player, removedStack);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {

        Location location = new Location(world, getPosition());

        if (source.getImmediateSource() instanceof PlayerEntity) {

            PlayerEntity player = (PlayerEntity) source.getImmediateSource();

            if (player.getHeldItemMainhand().getItem() instanceof AxeItem || player.isCreative()) {

                Item item = InitItems.CHRISTMAS_TREE.get();

                if (isWhite()) {
                    item = InitItems.CHRISTMAS_TREE_WHITE.get();
                }

                ItemHelper.spawnStackAtLocation(world, location, new ItemStack(item));

                ItemHelper.spawnStackAtLocation(world, location, getItemStackFromSlot(EquipmentSlotType.HEAD));
                ItemHelper.spawnStackAtLocation(world, location, getItemStackFromSlot(EquipmentSlotType.CHEST));
                ItemHelper.spawnStackAtLocation(world, location, getItemStackFromSlot(EquipmentSlotType.LEGS));
                ItemHelper.spawnStackAtLocation(world, location, getItemStackFromSlot(EquipmentSlotType.FEET));

                if (!world.isRemote) {
                    playSound(SoundEvents.BLOCK_WOOD_BREAK, 1, 1);
                    playSound(SoundEvents.BLOCK_GRASS_BREAK, 1, 1);
                }

                remove();
            }

            return true;
        }

        return false;
    }

    @Override
    protected void registerData() {
        dataManager.register(INVENTORY, NonNullList.withSize(4, ItemStack.EMPTY));
        dataManager.register(WHITE, false);
    }

    @Override
    protected void readAdditional(CompoundNBT nbt) {

        NonNullList<ItemStack> newInv = NonNullList.withSize(4, ItemStack.EMPTY);

        newInv.set(0, ItemStack.read(nbt.getCompound("Head")));
        newInv.set(1, ItemStack.read(nbt.getCompound("Chest")));
        newInv.set(2, ItemStack.read(nbt.getCompound("Legs")));
        newInv.set(3, ItemStack.read(nbt.getCompound("Feet")));

        dataManager.set(INVENTORY, newInv);

        dataManager.set(WHITE, nbt.getBoolean("White"));
    }

    @Override
    protected void writeAdditional(CompoundNBT nbt) {

        CompoundNBT headTag = new CompoundNBT();
        CompoundNBT chestTag = new CompoundNBT();
        CompoundNBT legsTag = new CompoundNBT();
        CompoundNBT feetTag = new CompoundNBT();
        dataManager.get(INVENTORY).get(0).write(headTag);
        dataManager.get(INVENTORY).get(1).write(chestTag);
        dataManager.get(INVENTORY).get(2).write(legsTag);
        dataManager.get(INVENTORY).get(3).write(feetTag);
        nbt.put("Head", headTag);
        nbt.put("Chest", chestTag);
        nbt.put("Legs", legsTag);
        nbt.put("Feet", feetTag);

        nbt.putBoolean("White", dataManager.get(WHITE));
    }

    @Override
    public boolean canCollide(Entity entity) {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
        super.applyEntityCollision(entityIn);
    }

    @Override
    public float getCollisionBorderSize() {
        return 0.0F;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
