package com.tm.cspirit.block;

import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFrostedGlassPane extends PaneBlock {

    public BlockFrostedGlassPane() {
        super(Properties.create(Material.GLASS).hardnessAndResistance(0.5F).sound(SoundType.GLASS).notSolid().variableOpacity().notSolid());
    }

    @Override
    public void onReplaced (BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {

        if (state.getBlock() != newState.getBlock()) {
            ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, new ItemStack(asItem()));
            super.onReplaced(state, world, pos, newState, isMoving);
        }
    }
}
