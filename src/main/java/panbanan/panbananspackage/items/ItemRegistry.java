package panbanan.panbananspackage.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;
import panbanan.panbananspackage.PanBanansPackage;
import panbanan.panbananspackage.config.ItemsConfig;
import panbanan.panbananspackage.items.armors.ArmorSet;
import java.util.*;

public class ItemRegistry {

    public static final HashMap<String, Item> ITEMS = new HashMap<>();
    //Not used maybe never will idk.
    public static Item getItemId(String id){
        return ITEMS.getOrDefault(id, Items.AIR);
    }

    public static void registerItems() {
        //Map read from ItemsConfig. Looping through each item in the map and registration.
        final Map<String, Boolean> itemsMap = ItemsConfig.newMap;
        for (Map.Entry<String, Boolean> entry : itemsMap.entrySet()){
            String itemID = entry.getKey().toUpperCase();
            Boolean isEnabled = entry.getValue();
            //Check if item has true in it's config and put this item in the map for registration.
            if(isEnabled){
                //Weapons and Armors need their checks.
                if(itemID.contains("WEAPON")) {
                    ITEMS.put(itemID, new MyItems(ToolMaterials.NETHERITE, 0, -2.4F, new Item.Settings().group(ItemGroup.COMBAT)));
                }
                if(itemID.contains("ARMOR") & itemID.contains("BOOTS")){
                    ITEMS.put(itemID, new ArmorSet(ArmorMaterials.NETHERITE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
                }
                if(itemID.contains("ARMOR") & itemID.contains("CHEST")){
                    ITEMS.put(itemID, new ArmorSet(ArmorMaterials.NETHERITE, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
                }
                if(itemID.contains("ARMOR") & itemID.contains("LEGGINGS")){
                    ITEMS.put(itemID, new ArmorSet(ArmorMaterials.NETHERITE, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
                }
                if(itemID.contains("ARMOR") & itemID.contains("HELMET")){
                    ITEMS.put(itemID, new ArmorSet(ArmorMaterials.NETHERITE, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
                }
            }
        }
        //Save map as arraylist and register each item.
        ArrayList<String> IDs = new ArrayList<>(ITEMS.keySet());
        Collections.sort(IDs);
        for(String id : IDs){
            String path = id.toLowerCase();
            Registry.register(Registry.ITEM, PanBanansPackage.ID(path), ITEMS.get(id));
        }

    }

}
//Old register
        /*
    //Armor pieces
    public static final Item POTAT_HELMET = new potatArmorSet(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item POTAT_CHEST = new potatArmorSet(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item POTAT_LEGGINGS = new potatArmorSet(ArmorMaterials.LEATHER, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item POTAT_BOOTS = new potatArmorSet(ArmorMaterials.LEATHER, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));
    //Weapons
    public static final Item EAGLE_SPEAR = new Items(ToolMaterials.NETHERITE, 5, 0.1F,new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item LUPUS_BOREALIS = new MyItems(ToolMaterials.NETHERITE, 3, 0.1F, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item MARE_TRANQUILITIS = new MyItems(ToolMaterials.NETHERITE, 5, 0.1F, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item MORNINGSTAR = new MyItems(ToolMaterials.NETHERITE, 5, 0.1F, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item RIFTS_ECLIPSE = new MyItems(ToolMaterials.NETHERITE, 5, 0.1F, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item RISKY_MANUEVER = new MyItems(ToolMaterials.NETHERITE, 5, 0.1F, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item UMBRAL_VALKYRIE = new MyItems(ToolMaterials.NETHERITE, 6, 0.1F, new Item.Settings().group(ItemGroup.COMBAT));*/

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