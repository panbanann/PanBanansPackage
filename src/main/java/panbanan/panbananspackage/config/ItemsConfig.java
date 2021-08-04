package panbanan.panbananspackage.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.item.Item;
import panbanan.panbananspackage.items.ItemsIds;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class ItemsConfig {

    public static Map<String, Boolean> map = new HashMap<String, Boolean>();

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void configInit() throws IOException {
        File configFile = new File("config/config.json");

        //Map<ItemsIds, Boolean> itemsMap = new HashMap<ItemsIds, Boolean>();
        if(!configFile.exists()) {
            /*for (ItemsIds item : ItemsIds.values()) {
                itemsMap.put(item, true);
            }*/
            String itemsJson = gson.toJson(ItemsIds.itemsMaps());
            Files.write(configFile.toPath(), itemsJson.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }
        if(configFile.exists()){
            map = gson.fromJson(new FileReader(configFile), (Type) HashMap.class);
        }
    }




}
