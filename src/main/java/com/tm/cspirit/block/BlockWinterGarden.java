package com.tm.cspirit.block;

import com.tm.cspirit.block.base.BlockBase;
import com.tm.cspirit.init.InitItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockWinterGarden extends BlockBase {
    public BlockWinterGarden() {
        super(Block.Properties.from(Blocks.WHEAT));
    }


    @Override
    public void addDrops(BlockState state, World world, BlockPos pos, List<ItemStack> list) {
        Random random = world.getRandom();
        if (random.nextBoolean()) {
            list.add(new ItemStack(InitItems.GINGER.get().asItem(), Math.max(1, random.nextInt(4))));
        } else {
            list.add(new ItemStack(InitItems.PEPPERMINT.get().asItem(), Math.max(1, random.nextInt(4))));
        }
    }
}
