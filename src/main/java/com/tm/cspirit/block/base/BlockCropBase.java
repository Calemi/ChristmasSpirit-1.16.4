package com.tm.cspirit.block.base;

import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;

public class BlockCropBase extends CropsBlock {

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 4, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 4, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 6, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 6, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 8, 16),
            Block.makeCuboidShape(0, 0, 0, 16, 8, 16)};

    private final Supplier<Item> cropItem;

    public BlockCropBase(Supplier<Item> cropItem) {
        super(Block.Properties.from(Blocks.WHEAT));
        this.cropItem = cropItem;
    }

    public BlockCropBase() {
        super(Block.Properties.from(Blocks.WHEAT));
        this.cropItem = null;
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return Item.getItemFromBlock(this);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(getAgeProperty())];
    }

    @Override
    public void onReplaced (BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {

        if (state.getBlock() != newState.getBlock()) {

            Random random = new Random();

            int seedAmount = 1 + (state.get(AGE) == 7 ? random.nextInt(2) : 0);
            int cropAmount = state.get(AGE) == 7 ? random.nextInt(2) + 1 : 0;

            if (cropItem == null) {
                seedAmount = 1 + (state.get(AGE) == 7 ? random.nextInt(2) + 1 : 0);
            }

            ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, new ItemStack(getSeedsItem(), seedAmount));
            if (cropItem != null) ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, new ItemStack(cropItem.get(), cropAmount));

            super.onReplaced(state, world, pos, newState, isMoving);
        }
    }
}
