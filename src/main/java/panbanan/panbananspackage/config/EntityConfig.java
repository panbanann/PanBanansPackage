package panbanan.panbananspackage.config;

import com.google.gson.*;
import panbanan.panbananspackage.entity.EntityIDs;
//import sun.security.util.Debug;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EntityConfig {

    public static Map<String, Boolean> newMap = new HashMap<>();

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static String debug = "(debug.string)";

    public static String readFile(File file) {
        String output = "";
        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter("\\Z");
            output = scanner.next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static JsonObject getJsonObject(String json) {
        try {
            return new JsonParser().parse(json).getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void configInit() throws IOException {
        File configFile = new File("config/entity_config.json");
        Map<Object, Boolean> map = new HashMap<>();
        if (!configFile.exists()) {
            for (Object item : EntityIDs.entitySet()) {
                map.put(item, true);
            }
            String itemsJson = gson.toJson(map);
            Files.write(configFile.toPath(), itemsJson.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }
        if (configFile.exists()) {
            JsonObject json = getJsonObject(readFile(new File("config/entity_config.json")));
            String itemToString = "";
            if(json.size() < EntityIDs.entitySet().size()){
                for (Object item : EntityIDs.entitySet()) {
                    itemToString = item.toString();
                        if (!json.has(itemToString)) {
                            newMap.put(itemToString, true);
                            map.put(itemToString, true);
                        }else {
                            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                                newMap.put(entry.getKey(), entry.getValue().getAsBoolean());
                            }
                        }
                }
            } else {
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    newMap.put(entry.getKey(), entry.getValue().getAsBoolean());
                }
            }
            String oldItemsJson = gson.toJson(map);
            Files.write(configFile.toPath(), oldItemsJson.getBytes(), StandardOpenOption.DELETE_ON_CLOSE, StandardOpenOption.WRITE);
            String itemsJson = gson.toJson(newMap);
            Files.write(configFile.toPath(), itemsJson.getBytes(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        }
    }
}







