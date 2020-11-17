package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import java.util.Optional;

public class BlockMistletoe extends BlockBase {

    public static final VoxelShape SHAPE = Optional.of(Block.makeCuboidShape(3, 4, 3, 13, 16, 13)).get();

    public BlockMistletoe() {
        super(Block.Properties.create(Material.PLANTS).hardnessAndResistance(0).sound(SoundType.PLANT).notSolid().variableOpacity().doesNotBlockMovement());
    }

    @Override
    public VoxelShape getShape (BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        return SHAPE;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    @Override
    public BlockRenderType getRenderType (BlockState state) {
        return BlockRenderType.MODEL;
    }
}
