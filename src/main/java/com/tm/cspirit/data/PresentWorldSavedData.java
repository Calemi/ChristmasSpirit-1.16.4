package com.tm.cspirit.data;

import com.tm.cspirit.main.CSReference;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class PresentWorldSavedData extends WorldSavedData implements Supplier {

    private static final String DATA_NAME = CSReference.MOD_ID + "_PresentData";

    public Map<UUID, Integer> playerGiftData = new HashMap<>();

    public PresentWorldSavedData() {
        super(DATA_NAME);
    }

    public PresentWorldSavedData(String s) {
        super(s);
    }

    @Override
    public void read(CompoundNBT nbt) {

        playerGiftData.clear();

        ListNBT list = nbt.getList("list", Constants.NBT.TAG_COMPOUND);

        for (int i =0; i<list.size(); i++) {

            CompoundNBT element = list.getCompound(i);

            UUID playerID = element.getUniqueId("playerID");
            int playerDay = element.getInt("playerDay");

            playerGiftData.put(playerID,playerDay);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {

        ListNBT list = new ListNBT();

        for (UUID playerID : playerGiftData.keySet()) {
            CompoundNBT element = new CompoundNBT();
            element.putUniqueId("playerID", playerID);
            element.putInt("playerDay", playerGiftData.get(playerID));
            list.add(element);
        }

        compound.put("list", list);

        return compound;
    }

    @Override
    public Object get() {
        return this;
    }

    public static PresentWorldSavedData get(ServerWorld world) {

        DimensionSavedDataManager storage = world.getSavedData();
        Supplier<PresentWorldSavedData> supplier = new PresentWorldSavedData();
        PresentWorldSavedData instance = storage.getOrCreate(supplier, DATA_NAME);

        if (instance == null) {
            instance = new PresentWorldSavedData();
            storage.set(instance);
        }

        return instance;
    }
}
