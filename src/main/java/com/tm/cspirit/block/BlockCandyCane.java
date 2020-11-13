package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockBase;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import java.util.Optional;

public class BlockCandyCane extends BlockBase {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final VoxelShape SHAPE_NS = Optional.of(Block.makeCuboidShape(4, 0, 7, 12, 13, 9)).get();
    public static final VoxelShape SHAPE_EW = Optional.of(Block.makeCuboidShape(7, 0, 4, 9, 13, 12)).get();

    public BlockCandyCane() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F).sound(SoundType.LANTERN).notSolid());
        setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public VoxelShape getCandyCane(BlockState state) {
        switch (state.get(FACING)) {
            case EAST:
            case WEST: return SHAPE_EW;
            default: return SHAPE_NS;
        }
    }

    @Override
    public VoxelShape getShape (BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        return getCandyCane(state);
    }

    @Override
    public VoxelShape getCollisionShape (BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return getCandyCane(state);
    }

    @Override
    public BlockRenderType getRenderType (BlockState state) {
        return BlockRenderType.MODEL;
    }
}
