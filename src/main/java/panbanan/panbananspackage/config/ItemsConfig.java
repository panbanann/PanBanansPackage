package panbanan.panbananspackage.config;

import com.google.gson.*;
import org.lwjgl.system.CallbackI;
import panbanan.panbananspackage.items.ItemsIds;
import sun.security.util.Debug;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class ItemsConfig {

    public static Map<String, Boolean> map = new HashMap<String, Boolean>();

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();



    public static void configInit() throws IOException {
        File configFile = new File("config/config.json");
        //JsonParser parser = new JsonParser();

        Map<ItemsIds, Boolean> itemsMap = new HashMap<ItemsIds, Boolean>();
        if (!configFile.exists()) {
            for (ItemsIds item : ItemsIds.values()) {
                itemsMap.put(item, true);

                String itemsJson = gson.toJson(itemsMap);
                Files.write(configFile.toPath(), itemsJson.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            }
        }
        if (configFile.exists()) {
            map = gson.fromJson(new FileReader(configFile), new HashMap<String, Boolean>().getClass());

                /*try (Reader reader = new FileReader(configFile)){
                    map = gson.fromJson(reader, HashMap.class);

                } catch (IOException e) {
                    e.printStackTrace();
                }*/

        }

    }

}







