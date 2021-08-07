package panbanan.panbananspackage.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum ItemsIds implements ItemConvertible {
    LUPUS_BOREALIS,
    MARE_TRANQUILITIS,
    MORNINGSTAR,
    POTAT_BOOTS,
    POTAT_CHEST,
    POTAT_HELMET,
    POTAT_LEGGINGS,
    RIFTS_ECLIPSE,
    RISKY_MANUEVER,
    UMBRAL_VALKYRIE;

    public Item asItem(){
        return ItemRegistry.items.get(this);
    }




    /*public static Map itemsMaps(){
        Map<String, Boolean> itemsMap = new HashMap<String, Boolean>();
        itemsMap.put("LUPUS_BOREALIS", true);
        itemsMap.put("MARE_TRANQUILITIS", true);
        itemsMap.put("MORNINGSTAR", true);
        itemsMap.put("POTAT_BOOTS", true);
        itemsMap.put("POTAT_CHEST", true);
        itemsMap.put("POTAT_HELMET", true);
        itemsMap.put("POTAT_LEGGINGS", true);
        itemsMap.put("RIFTS_ECLIPSE", true);
        itemsMap.put("RISKY_MANUEVER", true);
        itemsMap.put("UMBRAL_VALKYRIE", true);
        return itemsMap;
    }*/

}

