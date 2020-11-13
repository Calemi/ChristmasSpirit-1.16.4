package com.tm.cspirit.block.base;

import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import com.tm.cspirit.util.helper.InventoryHelper;
import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * The base class for Blocks that have Inventories.
 */
public abstract class BlockInventoryBase extends BlockTileEntityBase {

    /**
     * @param properties The specific properties for the Block. (Creative Tab, hardness, material, etc.)
     */
    public BlockInventoryBase(Properties properties) {
        super(properties);
    }

    /**
     * Drops all contents when the Block is broken.
     */
    @Override
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {

        Location location = new Location(world, pos);
        TileEntity tileEntity = location.getTileEntity();

        if (tileEntity instanceof TileEntityInventoryBase) {

            TileEntityInventoryBase inv = (TileEntityInventoryBase) tileEntity;
            InventoryHelper.breakInventory(world, inv.getInventory(), location);
        }

        ItemHelper.spawnStackAtLocation(world, location, new ItemStack(asItem()));

        location.setBlockToAir();

        return true;
    }

    /**
     * Opens the gui of the Block.
     */
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {

        //Prevents client side.
        if (world.isRemote) {
            return ActionResultType.SUCCESS;
        }

        Location location = new Location(world, pos);
        TileEntity tileEntity = location.getTileEntity();

        if (player instanceof ServerPlayerEntity && tileEntity instanceof INamedContainerProvider) {
            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, pos);
        }

        return ActionResultType.SUCCESS;
    }
}
