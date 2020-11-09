package com.tm.cspirit.block.base;

import com.tm.cspirit.util.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * The base class for Blocks.
 */
public class BlockBase extends Block {

    /**
     * @param properties The specific properties for the Block. (Creative Tab, hardness, material, etc.)
     */
    public BlockBase (Properties properties) {
        super(properties);
    }

    public void addDrops(BlockState state, World world, BlockPos pos, List<ItemStack> list) {
        list.add(new ItemStack(asItem()));
    }

    @Override
    public void onReplaced (BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {

        if (state.getBlock() != newState.getBlock()) {

            if (isMoving && PistonBlock.canPush(state, world, pos, Direction.NORTH, false, Direction.NORTH)) {
                return;
            }

            List<ItemStack> drops = new ArrayList<>();
            addDrops(state, world, pos, drops);

            for (ItemStack stack : drops) {
                ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, stack);
            }

            super.onReplaced(state, world, pos, newState, isMoving);
        }
    }
}
