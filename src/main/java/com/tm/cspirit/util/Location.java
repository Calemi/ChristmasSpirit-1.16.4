package com.tm.cspirit.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.extensions.IForgeBlockState;

import java.util.List;

public class Location {

    public final World world;
    public int x, y, z;
    private BlockPos blockPos;

    public Location(World world, BlockPos pos) {
        this(world, pos.getX(), pos.getY(), pos.getZ());
    }

    public Location(World world, int x, int y, int z) {

        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;

        blockPos = new BlockPos(x, y, z);
    }

    public Location(TileEntity tileEntity) {
        this(tileEntity.getWorld(), tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ());
    }

    public Location(Entity entity) {
        this(entity.world, entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ());
    }

    public Location(Location location, Direction dir) {
        this(location, dir, 1);
    }

    public Location(Location location, Direction dir, int distance) {

        this.world = location.world;
        this.x = location.x + (dir.getXOffset() * distance);
        this.y = location.y + (dir.getYOffset() * distance);
        this.z = location.z + (dir.getZOffset() * distance);

        blockPos = new BlockPos(x, y, z);
    }

    public Location translate (Direction dir, int distance) {

        this.x += (dir.getXOffset() * distance);
        this.y += (dir.getYOffset() * distance);
        this.z += (dir.getZOffset() * distance);
        blockPos = new BlockPos(x, y, z);

        return this;
    }

    public Location translate (Location location) {

        this.x += location.x;
        this.y += location.y;
        this.z += location.z;
        blockPos = new BlockPos(x, y, z);
        return this;
    }

    public Location copy () {
        return new Location(this.world, this.x, this.y, this.z);
    }

    public BlockPos getBlockPos () {
        return blockPos;
    }

    public IForgeBlockState getForgeBlockState () {

        if (getBlockPos() == null) {
            return null;
        }

        return world.getBlockState(getBlockPos());
    }

    public BlockState getBlockState () {

        if (getForgeBlockState() == null) {
            return null;
        }

        return getForgeBlockState().getBlockState();
    }

    public Block getBlock () {

        if (getBlockState() == null) {
            return null;
        }

        return getBlockState().getBlock();
    }

    public List<ItemStack> getDrops (PlayerEntity player, ItemStack heldStack) {
        return Block.getDrops(getBlockState(), (ServerWorld) world, getBlockPos(), null, player, heldStack);
    }

    public int getLightValue () {
        return world.getLight(getBlockPos());
    }

    public TileEntity getTileEntity () {
        return world.getTileEntity(getBlockPos());
    }

    public double getDistance (Location location) {

        int dx = x - location.x;
        int dy = y - location.y;
        int dz = z - location.z;

        return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
    }

    public void setBlock (Block block) {
        world.setBlockState(getBlockPos(), block.getDefaultState());
    }

    public void setBlock (BlockState state) {
        world.setBlockState(getBlockPos(), state.getBlock().getDefaultState());
        world.setBlockState(getBlockPos(), state);
    }

    public void setBlock (BlockState state, PlayerEntity placer) {
        world.setBlockState(getBlockPos(), state, 2);
        state.getBlock().onBlockPlacedBy(world, getBlockPos(), state, placer, new ItemStack(state.getBlock()));
    }

    public void setBlockToAir () {
        setBlock(Blocks.AIR);
    }

    public boolean isZero () {
        return x == 0 && y == 0 && z == 0;
    }

    public boolean isAirBlock () {
        return getBlock() == Blocks.AIR;
    }

    public boolean doesBlockHaveCollision () {
        return getBlock().getCollisionShape(getBlockState(), world, getBlockPos(), ISelectionContext.dummy()) != VoxelShapes.empty();
    }

    public static Location fromNBT(World world, CompoundNBT nbt) {

        int x = nbt.getInt("locX");
        int y = nbt.getInt("locY");
        int z = nbt.getInt("locZ");

        Location loc = new Location(world, x, y, z);

        if (!loc.isZero()) {
            return loc;
        }

        return null;
    }

    public void toNBT(CompoundNBT nbt) {
        nbt.putInt("locX", z);
        nbt.putInt("locY", y);
        nbt.putInt("locZ", z);
    }

    @Override
    public boolean equals (Object obj) {

        if (obj instanceof Location) {
            Location newLoc = (Location) obj;
            return world == newLoc.world && x == newLoc.x && y == newLoc.y && z == newLoc.z;
        }

        return super.equals(obj);
    }

    @Override
    public String toString () {
        return "[" + x + ", " + y + ", " + z + "]";
    }
}
