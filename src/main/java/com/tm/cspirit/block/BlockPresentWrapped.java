package com.tm.cspirit.block;

import com.tm.cspirit.init.InitSounds;
import com.tm.cspirit.init.InitTileEntityTypes;
import com.tm.cspirit.present.PresentConstructor;
import com.tm.cspirit.tileentity.TileEntityPresentWrapped;
import com.tm.cspirit.util.Location;
import com.tm.cspirit.util.helper.ItemHelper;
import com.tm.cspirit.util.helper.PresentHelper;
import com.tm.cspirit.util.helper.SoundHelper;
import com.tm.cspirit.util.helper.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Optional;

public class BlockPresentWrapped extends BlockPresentUnwrapped {

    public static final VoxelShape SHAPE = Optional.of(Block.makeCuboidShape(1, 0, 1, 15, 13, 15)).get();

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return InitTileEntityTypes.PRESENT_WRAPPED.get().create();
    }

    public static void spawnPresent(Location location, PresentConstructor constructor, ItemStack giftStack) {

        ItemStack stack = new ItemStack(constructor.getStyle().getBlock().asItem());

        constructor.toStack(stack);

        CompoundNBT nbt = ItemHelper.getNBT(stack);
        NonNullList<ItemStack> giftList = NonNullList.create();
        giftList.add(0, giftStack);
        ItemStackHelper.saveAllItems(nbt, giftList);

        ItemHelper.spawnStackAtLocation(location.world, location, stack);
    }

    @Override
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {

        Location location = new Location(world, pos);
        TileEntity tileEntity = location.getTileEntity();

        if (!world.isRemote) {

            if (tileEntity instanceof TileEntityPresentWrapped) {

                TileEntityPresentWrapped present = (TileEntityPresentWrapped) tileEntity;

                boolean isAnybody = present.getConstructor().getToPlayerName().equalsIgnoreCase("anybody");
                boolean isToPlayer = player.getDisplayName().getString().equalsIgnoreCase(present.getConstructor().getToPlayerName());
                boolean isFromPlayer = player.getDisplayName().getString().equalsIgnoreCase(present.getConstructor().getFromPlayerName());

                if (isAnybody|| isToPlayer || isFromPlayer || player.isCreative()) {
                    spawnPresent(location, present.getConstructor(), present.getInventory().getStackInSlot(0));
                    location.setBlockToAir();
                }

                else present.getUnitName(player).printMessage(TextFormatting.RED, "This present belongs to " + present.getConstructor().getToPlayerName() + "! You can't pick it up!");
            }
        }

        return true;
    }
    @Override
    public ActionResultType onBlockActivated (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {

        Location location = new Location(world, pos);
        TileEntity tileEntity = location.getTileEntity();

        if (!world.isRemote) {

            if (tileEntity instanceof TileEntityPresentWrapped) {

                TileEntityPresentWrapped present = (TileEntityPresentWrapped) tileEntity;

                if (player.isCrouching()) {
                    present.getUnitName(player).printMessage(TextFormatting.WHITE, "From: " + present.getConstructor().getFromPlayerName());
                    present.getUnitName(player).printMessage(TextFormatting.WHITE, "To: " + present.getConstructor().getToPlayerName());
                    present.getUnitName(player).printMessage(TextFormatting.WHITE, "Open on the " + TimeHelper.getFormattedDay(present.getConstructor().getActualDay()));
                }

                else if (player.getDisplayName().getString().equalsIgnoreCase(present.getConstructor().getToPlayerName()) || present.getConstructor().getToPlayerName().equalsIgnoreCase("anybody")) {

                    if (TimeHelper.getCurrentDay() >= present.getConstructor().getActualDay()) {

                        ItemStack stack = present.getInventory().getStackInSlot(0);

                        if (present.getConstructor().getFromPlayerName().equalsIgnoreCase("santa")) {
                            stack = PresentHelper.getSantaGiftedStack(player, present.getConstructor().getDay());
                        }

                        ItemHelper.spawnStack(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, stack);
                        location.setBlockToAir();

                        player.playSound(InitSounds.PRESENT_UNWRAP.get(), 1, 1);
                        SoundHelper.sendSoundToClient((ServerPlayerEntity) player, InitSounds.PRESENT_UNWRAP.get());
                    }

                    else {
                        present.getUnitName(player).printMessage(TextFormatting.RED, "You can't open this present yet!");
                        present.getUnitName(player).printMessage(TextFormatting.RED, "You must wait until the " + TimeHelper.getFormattedDay(present.getConstructor().getActualDay()) + "!");
                    }
                }

                else {
                    present.getUnitName(player).printMessage(TextFormatting.RED, "This present belongs to " + present.getConstructor().getToPlayerName() + "! You can't open it!");
                }
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getShape (BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape (BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
