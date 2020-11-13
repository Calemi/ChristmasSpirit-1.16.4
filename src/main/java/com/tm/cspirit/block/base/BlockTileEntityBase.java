package com.tm.cspirit.block.base;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;

/**
 * The base class for Blocks that have Containers
 */
public abstract class BlockTileEntityBase extends ContainerBlock {

    /**
     * @param properties The specific properties for the Block. (Creative Tab, hardness, material, etc.)
     */
    public BlockTileEntityBase(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity (BlockState state) {
        return true;
    }

    public BlockRenderType getRenderType (BlockState state) {
        return BlockRenderType.MODEL;
    }
}
