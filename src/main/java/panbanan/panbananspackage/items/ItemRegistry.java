package panbanan.panbananspackage.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import panbanan.panbananspackage.PanBanansPackage;
import panbanan.panbananspackage.config.ItemsConfig;
import panbanan.panbananspackage.items.armors.potatArmorSet;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ItemRegistry {

    //Armor pieces
    public static final Item POTAT_HELMET = new potatArmorSet(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item POTAT_CHEST = new potatArmorSet(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item POTAT_LEGGINGS = new potatArmorSet(ArmorMaterials.LEATHER, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item POTAT_BOOTS = new potatArmorSet(ArmorMaterials.LEATHER, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

    //Weapons
    //public static final Item EAGLE_SPEAR = new Items(ToolMaterials.NETHERITE, 5, 0.1F,new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item LUPUS_BOREALIS = new MyItems(ToolMaterials.NETHERITE, 3, 0.1F,new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item MARE_TRANQUILITIS = new MyItems(ToolMaterials.NETHERITE, 5, 0.1F,new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item MORNINGSTAR = new MyItems(ToolMaterials.NETHERITE, 5, 0.1F,new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item RIFTS_ECLIPSE = new MyItems(ToolMaterials.NETHERITE, 5, 0.1F,new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item RISKY_MANUEVER = new MyItems(ToolMaterials.NETHERITE, 5, 0.1F,new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item UMBRAL_VALKYRIE = new MyItems(ToolMaterials.NETHERITE, 6, 0.1F,new Item.Settings().group(ItemGroup.COMBAT));

    public static void registerItems(){
        Map<String, Boolean> newMap;
        newMap = ItemsConfig.map;
        for (String key : newMap.keySet()) {
            String itemID = key;
            String path = key.toLowerCase();
            for (Boolean value : newMap.values()) {
                Boolean isEnabled = value;
                if (isEnabled){
                    Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, path), itemID);
                }
            }
        }


//        Iterator<Map.Entry<String, Boolean>> iterator = newMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Boolean> entry = iterator.next();
//            String itemID = get(entry);
//            String path = entry.getKey().toLowerCase();
//            Boolean isEnabled = entry.getValue();
//            if (isEnabled) {
//                Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, path), itemID);
//            }
//        }
        /*Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, "potat_helmet"), POTAT_HELMET);
        Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, "potat_chest"), POTAT_CHEST);
        Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, "potat_leggings"), POTAT_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, "potat_boots"), POTAT_BOOTS);

        //Weapons
        //Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, "eagle_spear"), EAGLE_SPEAR);
        Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, "lupus_borealis"), LUPUS_BOREALIS);
        Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, "mare_tranquilitis"), MARE_TRANQUILITIS);
        Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, "morningstar"), MORNINGSTAR);
        Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, "rifts_eclipse"), RIFTS_ECLIPSE);
        Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, "risky_manuever"), RISKY_MANUEVER);
        Registry.register(Registry.ITEM, new Identifier(PanBanansPackage.MOD_ID, "umbral_valkyrie"), UMBRAL_VALKYRIE);*/
    }
}
