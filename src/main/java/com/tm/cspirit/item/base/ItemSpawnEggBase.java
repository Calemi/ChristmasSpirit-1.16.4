package com.tm.cspirit.item.base;

import com.tm.cspirit.main.ChristmasSpirit;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ItemSpawnEggBase extends SpawnEggItem {

    protected static final List<ItemSpawnEggBase> UNADDED_EGGS = new ArrayList<>();
    private final Lazy<? extends EntityType<?>> entityTypeSupplier;

    public ItemSpawnEggBase(final RegistryObject<? extends EntityType<?>> entityTypeSupplier) {
        super(null, 0xFFFFFF, 0xFFFFFF, new Item.Properties().group(ChristmasSpirit.TAB_MAIN));
        this.entityTypeSupplier = Lazy.of(entityTypeSupplier);
        UNADDED_EGGS.add(this);
    }

    public static void initSpawnEggs() {
        final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");

        DefaultDispenseItemBehavior dispenseBehavior = new DefaultDispenseItemBehavior() {

            @Override
            protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                type.spawn(source.getWorld(), stack, null, source.getBlockPos(), SpawnReason.DISPENSER, direction != Direction.DOWN, false);
                stack.shrink(1);
                return stack;
            }
        };

        for (final SpawnEggItem spawnEgg : UNADDED_EGGS) {
            EGGS.put(spawnEgg.getType(null), spawnEgg);
            DispenserBlock.registerDispenseBehavior(spawnEgg, dispenseBehavior);
        }

        UNADDED_EGGS.clear();
    }

    @Override
    public EntityType<?> getType(CompoundNBT nbt) {
        return this.entityTypeSupplier.get();
    }
}
