package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockBase;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class BlockChimney extends BlockBase {

    public static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(0, 0, 0, 2, 16, 16));
        SHAPE.addShape(Block.makeCuboidShape(14, 0, 0, 16, 16, 16));
        SHAPE.addShape(Block.makeCuboidShape(2, 0, 0, 14, 16, 2));
        SHAPE.addShape(Block.makeCuboidShape(2, 0, 14, 14, 16, 16));
    }

    public BlockChimney() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(1).harvestLevel(1).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).notSolid().variableOpacity());
    }

    @Override
    public VoxelShape getShape (BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        return SHAPE.getCombinedShape();
    }

    @Override
    public VoxelShape getCollisionShape (BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE.getCombinedShape();
    }

    @Override
    public BlockRenderType getRenderType (BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }
}
