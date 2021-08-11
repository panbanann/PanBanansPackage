package panbanan.panbananspackage.config;

import com.google.gson.*;
import panbanan.panbananspackage.items.ItemsIds;
import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ItemsConfig {

    public static Map<String, Boolean> newMap = new HashMap<>();

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
        File configFile = new File("config/config.json");
        JsonParser parser = new JsonParser();

        Map<Object, Boolean> map = new HashMap<Object, Boolean>();
        if (!configFile.exists()) {
            for (Object item : ItemsIds.itemsSets()) {
                map.put(item, true);

                String itemsJson = gson.toJson(map);
                Files.write(configFile.toPath(), itemsJson.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            }
        }
        if (configFile.exists()) {
            JsonObject json = getJsonObject(readFile(new File("config/config.json")));
            //JsonObject json = McdwBaseConfig.getJsonObject(McdwBaseConfig.readFile(new File("config/minecraft_dungeon_weapons/enchants_config.json5")));
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                newMap.put(entry.getKey(), entry.getValue().getAsBoolean());
            }

            //map = gson.fromJson(new FileReader(configFile), new HashMap<String, Boolean>().getClass());

                /*try (Reader reader = new FileReader(configFile)){
                    map = gson.fromJson(reader, HashMap.class);

                } catch (IOException e) {
                    e.printStackTrace();
                }*/

        }

    }

}







