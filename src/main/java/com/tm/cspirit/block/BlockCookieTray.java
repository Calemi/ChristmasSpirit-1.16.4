package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockInventoryBase;
import com.tm.cspirit.init.InitTileEntityTypes;
import com.tm.cspirit.tileentity.TileEntityCookieTray;
import com.tm.cspirit.tileentity.base.CSItemHandler;
import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Optional;

public class BlockCookieTray extends BlockInventoryBase {

    public static final VoxelShape SHAPE = Optional.of(Block.makeCuboidShape(2, 0, 2, 14, 1, 14)).get();

    public BlockCookieTray() {
        super(Properties.create(Material.IRON).hardnessAndResistance(0.5F).sound(SoundType.METAL).notSolid());
    }

    public void takeNextCookie(World world, PlayerEntity player, CSItemHandler inv) {

        for (int i = 0; i < inv.getSlots(); i++) {

            if (inv.getStackInSlot(i).getCount() > 0) {

                ItemStack stack = inv.getStackInSlot(i).copy();
                stack.setCount(1);

                ItemHelper.spawnStackAtEntity(world, player, stack);
                inv.getStackInSlot(i).shrink(1);
                break;
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {

        if (!player.isCrouching()) {

            Location location = new Location(world, pos);

            if (location.getTileEntity() instanceof TileEntityCookieTray) {
                TileEntityCookieTray cookieTray = (TileEntityCookieTray) location.getTileEntity();
                takeNextCookie(world, player, cookieTray.getInventory());
            }

            return ActionResultType.SUCCESS;
        }

        else return super.onBlockActivated(state, world, pos, player, hand, result);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.COOKIE_TRAY.get().create();
    }

    @Override
    public VoxelShape getShape (BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape (BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }
}
