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

public class BlockStocking extends BlockBase {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final VoxelShape SHAPE_N = Optional.of(Block.makeCuboidShape(2, 2, 0, 14, 14, 1)).get();
    public static final VoxelShape SHAPE_E = Optional.of(Block.makeCuboidShape(16, 2, 2, 15, 14, 14)).get();
    public static final VoxelShape SHAPE_S = Optional.of(Block.makeCuboidShape(14, 2, 16, 2, 14, 15)).get();
    public static final VoxelShape SHAPE_W = Optional.of(Block.makeCuboidShape(0, 2, 14, 1, 14, 2)).get();

    public BlockStocking() {
        super(Properties.create(Material.WOOL).hardnessAndResistance(0).sound(SoundType.CLOTH).notSolid().variableOpacity().doesNotBlockMovement());
        setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    public VoxelShape getReefShape(BlockState state) {
        switch (state.get(FACING)) {
            case EAST: return SHAPE_E;
            case SOUTH: return SHAPE_S;
            case WEST: return SHAPE_W;
            default: return SHAPE_N;
        }
    }

    @Override
    public VoxelShape getShape (BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        return getReefShape(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockRenderType getRenderType (BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }
}
