package panbanan.panbananspackage.config;

import com.google.gson.*;
import panbanan.panbananspackage.entity.EntityIDs;
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

                String itemsJson = gson.toJson(map);
                Files.write(configFile.toPath(), itemsJson.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            }
        }
        if (configFile.exists()) {
            JsonObject json = getJsonObject(readFile(new File("config/entity_config.json")));
            if (json.size() <= EntityIDs.entitySet().size()) {
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    if (!json.has(String.valueOf(entry))) {
                        for (Object item : EntityIDs.entitySet()){
                            map.put(item, true);
                            String itemsJson = gson.toJson(map);
                            Files.write(configFile.toPath(), itemsJson.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                        }
                    }
                    newMap.put(entry.getKey(), entry.getValue().getAsBoolean());
                }
            }

        }

    }

}







