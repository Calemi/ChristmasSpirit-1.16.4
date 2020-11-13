package com.tm.cspirit.data;

import com.google.gson.reflect.TypeToken;
import com.tm.cspirit.util.helper.FileHelper;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NaughtyListFile {

    public static List<UUID> naughtyList;

    public static void init() {
        naughtyList = FileHelper.createFile("NaughtyList", new ArrayList<>(), new TypeToken<List<UUID>>(){});
    }

    public static void addToNaughtyList(PlayerEntity player) {
        naughtyList.add(player.getUniqueID());
        FileHelper.saveToFile("NaughtyList", (ArrayList<UUID>)naughtyList);
    }

    public static void removeFromNaughtyList(PlayerEntity player) {
        naughtyList.remove(player.getUniqueID());
        FileHelper.saveToFile("NaughtyList", (ArrayList<UUID>)naughtyList);
    }

    public static boolean isOnNaughtyList(PlayerEntity player) {

        for (UUID playerID : naughtyList) {

            if (player.getUniqueID().equals(playerID)) {
                return true;
            }
        }

        return false;
    }
}