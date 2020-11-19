package com.tm.cspirit.block;

import com.tm.cspirit.util.helper.EffectHelper;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.List;

public class BlockFruitCake extends CakeBlock {

    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.makeCuboidShape(1.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.makeCuboidShape(3.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.makeCuboidShape(5.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.makeCuboidShape(9.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.makeCuboidShape(11.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D),
            Block.makeCuboidShape(13.0D, 0.0D, 4.0D, 15.0D, 8.0D, 12.0D)};

    public BlockFruitCake() {
        super(AbstractBlock.Properties.create(Material.CAKE).hardnessAndResistance(0.5F).sound(SoundType.CLOTH));
    }

    @Override
    public void addInformation(ItemStack stack, IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new StringTextComponent("Max Effect Stacking: " + TextFormatting.GOLD + 3));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if (worldIn.isRemote) {

            ItemStack itemstack = player.getHeldItem(handIn);

            if (this.eatSlice(worldIn, pos, state, player).isSuccessOrConsume()) {
                return ActionResultType.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return ActionResultType.CONSUME;
            }
        }

        return this.eatSlice(worldIn, pos, state, player);
    }

    private ActionResultType eatSlice(IWorld world, BlockPos pos, BlockState state, PlayerEntity player) {

        if (!player.canEat(false)) {
            return ActionResultType.PASS;
        }

        else {
            player.addStat(Stats.EAT_CAKE_SLICE);
            player.getFoodStats().addStats(4, 0.5F);
            EffectHelper.giveHolidaySpiritStackEffect(player, 3);
            int i = state.get(BITES);
            if (i < 6) {
                world.setBlockState(pos, state.with(BITES, i + 1), 3);
            } else {
                world.removeBlock(pos, false);
            }

            return ActionResultType.SUCCESS;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.get(BITES)];
    }
}
