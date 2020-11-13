package com.tm.cspirit.util.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileHelper {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();

    public static <T> T createFile(String fileName, T defaultItemsList, TypeToken<?> token) {

        File jsonConfig = new File(FMLPaths.CONFIGDIR.get().toString(), fileName + ".json");

        try {

            // Create the config if it doesn't already exist.
            if (!jsonConfig.exists() && jsonConfig.createNewFile()) {

                // Get a default map of blocks. You could just use a blank map, however.
                // Convert the map to JSON format. There is a built in (de)serializer for it already.
                String json = GSON.toJson(defaultItemsList, token.getType());
                FileWriter writer = new FileWriter(jsonConfig);
                // Write to the file you passed
                writer.write(json);
                // Always close when done.
                writer.close();
            }

            // If the file exists (or we just made one exist), convert it from JSON format to a populated Map object
            return GSON.fromJson(new FileReader(jsonConfig), token.getType());
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> void saveToFile(String fileName, T data) {

        try {
            File jsonConfig = new File(FMLPaths.CONFIGDIR.get().toString(), fileName + ".json");
            String json = GSON.toJson(data, new TypeToken<T>() {}.getType());
            FileWriter writer = new FileWriter(jsonConfig);
            writer.write(json);
            writer.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
