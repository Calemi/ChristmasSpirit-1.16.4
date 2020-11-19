package com.tm.cspirit.item;

import com.tm.cspirit.entity.EntityCandyCaneProjectile;
import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.item.base.ItemBase;
import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemCandyCaneCannon extends ItemBase {

    private static final int MAX_CHARGE_TIME = 20;

    private int chargeTime;

    public ItemCandyCaneCannon() {
        super(new Properties().group(ChristmasSpirit.TAB_MAIN).maxStackSize(1));
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new StringTextComponent(TextFormatting.RED + "Naughty Item"));
    }

    private boolean isReady(ItemStack stack) {
        return stack.getUseDuration() >= MAX_CHARGE_TIME;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {

        if (entity instanceof PlayerEntity) {

            PlayerEntity player = (PlayerEntity) entity;

            if (getUseDuration(stack) - player.getItemInUseCount() == MAX_CHARGE_TIME) {
                player.playSound(SoundEvents.UI_BUTTON_CLICK, 1, 1);
            }
        }

        super.inventoryTick(stack, world, entity, itemSlot, isSelected);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (hand == Hand.MAIN_HAND) {

            ItemStack candyCaneStack = ItemStack.EMPTY;

            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {

                ItemStack stackInSlot = player.inventory.getStackInSlot(i);

                if (stackInSlot.getItem() instanceof ItemCandyCane) {
                    candyCaneStack = stackInSlot;
                    break;
                }
            }

            if (!candyCaneStack.isEmpty()) {
                player.setActiveHand(hand);
            }
        }

        return new ActionResult<>(ActionResultType.FAIL, itemstack);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity entityLiving, int timeLeft) {

        if (entityLiving instanceof PlayerEntity) {

            PlayerEntity player = (PlayerEntity) entityLiving;

            if (getUseDuration(stack) - timeLeft >= MAX_CHARGE_TIME) {

                ItemStack candyCaneStack = ItemStack.EMPTY;

                for (int i = 0; i < player.inventory.getSizeInventory(); i++) {

                    ItemStack stackInSlot = player.inventory.getStackInSlot(i);

                    if (stackInSlot.getItem() instanceof ItemCandyCane) {
                        candyCaneStack = stackInSlot;
                        break;
                    }
                }

                if (!candyCaneStack.isEmpty()) {

                    byte candyType = 0;

                    if (candyCaneStack.getItem() == InitItems.CANDY_CANE_GREEN.get()) {
                        candyType = 1;
                    } else if (candyCaneStack.getItem() == InitItems.CANDY_CANE_BLUE.get()) {
                        candyType = 2;
                    }

                    EntityCandyCaneProjectile entity = new EntityCandyCaneProjectile(world, player, candyType);
                    entity.setIsCritical(true);
                    entity.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0F, 3, 1.0F);
                    world.addEntity(entity);
                    candyCaneStack.shrink(1);

                    player.playSound(SoundEvents.ITEM_TRIDENT_THROW, 1, 1);
                    player.playSound(SoundEvents.ITEM_CROSSBOW_SHOOT, 1, 1);
                }
            }
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }
}
