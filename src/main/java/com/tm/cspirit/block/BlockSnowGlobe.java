package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockBase;
import com.tm.cspirit.util.ShapeBundle;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class BlockSnowGlobe extends BlockBase {

    public static final ShapeBundle SHAPE = new ShapeBundle();

    static {
        SHAPE.addShape(Block.makeCuboidShape(3, 0, 3, 13, 1, 13));
        SHAPE.addShape(Block.makeCuboidShape(3.5D, 1, 3.5D, 12.5D, 10, 12.5D));
    }

    public BlockSnowGlobe() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(1).harvestLevel(1).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).notSolid().variableOpacity());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

        if (!world.isRemote) ((ServerWorld)world).func_241113_a_(0, 20 * 60 * 5, true, false);

        else {
            for (int i = 0; i < 10; i++) {
                world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.SNOW_BLOCK.getDefaultState()), pos.getX() + 0.5D, pos.getY() + 0.2D, pos.getZ() + 0.5D, 0, 0, 0);
            }
        }

        return ActionResultType.SUCCESS;
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
