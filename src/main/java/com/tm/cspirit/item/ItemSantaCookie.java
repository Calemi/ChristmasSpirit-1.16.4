package com.tm.cspirit.item;

import com.tm.cspirit.data.NaughtyListFile;
import com.tm.cspirit.init.InitEffects;
import com.tm.cspirit.item.base.ItemFoodBase;
import com.tm.cspirit.main.ChristmasSpirit;
import com.tm.cspirit.util.helper.ChatHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSantaCookie extends ItemFoodBase {

    private static final Food SANTA_COOKIE = new Food.Builder()
            .hunger(40)
            .saturation(40)
            .setAlwaysEdible()
            .effect(() -> new EffectInstance(InitEffects.HOLIDAY_SPIRIT.get(), 400, 4), 1).build();

    public ItemSantaCookie() {
        super(new Item.Properties().group(ChristmasSpirit.TAB_BAKING).food(SANTA_COOKIE).rarity(Rarity.RARE), 5, false);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entityLiving) {

        if (entityLiving instanceof PlayerEntity) {

            PlayerEntity player = (PlayerEntity) entityLiving;

            player.removePotionEffect(InitEffects.NAUGHTY.get());

            player.addPotionEffect(new EffectInstance(InitEffects.HOLIDAY_SPIRIT.get(), 20 * 60, 4));
            player.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 20 * 60, 2));
            player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 20 * 60, 2));

            if (NaughtyListFile.isOnNaughtyList(player)) {

                NaughtyListFile.removeFromNaughtyList(player);
                ChatHelper.printModMessage(TextFormatting.GREEN,"You've been removed from the naughty list!", player);
            }
        }

        return this.isFood() ? entityLiving.onFoodEaten(world, stack) : stack;
    }
}
