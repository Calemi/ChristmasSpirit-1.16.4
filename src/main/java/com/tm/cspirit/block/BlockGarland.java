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

public class BlockGarland extends BlockBase {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final VoxelShape SHAPE_N = Optional.of(Block.makeCuboidShape(0, 5, 0, 16, 10, 2)).get();
    public static final VoxelShape SHAPE_E = Optional.of(Block.makeCuboidShape(16, 5, 0, 14, 10, 16)).get();
    public static final VoxelShape SHAPE_S = Optional.of(Block.makeCuboidShape(16, 5, 16, 0, 10, 14)).get();
    public static final VoxelShape SHAPE_W = Optional.of(Block.makeCuboidShape(0, 5, 16, 2, 10, 0)).get();

    public BlockGarland() {
        super(Block.Properties.create(Material.PLANTS).hardnessAndResistance(0).sound(SoundType.PLANT).notSolid().variableOpacity().doesNotBlockMovement());
        setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    public VoxelShape getGarlandShape(BlockState state) {
        switch (state.get(FACING)) {
            case EAST: return SHAPE_E;
            case SOUTH: return SHAPE_S;
            case WEST: return SHAPE_W;
            default: return SHAPE_N;
        }
    }

    @Override
    public VoxelShape getShape (BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        return getGarlandShape(state);
    }

    @Override
    public VoxelShape getCollisionShape (BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return getGarlandShape(state);
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
