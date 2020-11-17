package com.tm.cspirit.block.base;

import com.tm.cspirit.util.helper.ItemHelper;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

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

    public BlockBase (SoundType sound) {
        super(AbstractBlock.Properties.create(Material.ROCK).sound(sound).hardnessAndResistance(1).harvestTool(ToolType.PICKAXE));
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
