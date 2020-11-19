package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockBase;
import com.tm.cspirit.init.InitItems;
import com.tm.cspirit.util.ShapeBundle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockOrnament extends BlockBase {

    public static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(6, 6, 6, 10, 10, 10));
        SHAPE.addShape(Block.makeCuboidShape(7, 7, 7, 9, 13, 9));
    }

    public BlockOrnament() {
        super(Properties.create(Material.IRON).hardnessAndResistance(0.5F).sound(SoundType.LANTERN).notSolid().variableOpacity().doesNotBlockMovement());
    }

    @Override
    public VoxelShape getShape (BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        return SHAPE.getCombinedShape();
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
