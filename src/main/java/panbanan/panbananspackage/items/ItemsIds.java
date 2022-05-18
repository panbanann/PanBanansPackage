package panbanan.panbananspackage.items;

import java.util.*;

public class ItemsIds {

   /* LUPUS_BOREALIS,
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
    }*/

    public static Set itemsSets(){
        Set<String> itemSet = new HashSet<String>();
        itemSet.add("armor_sattes_helmet");
        itemSet.add("weapon_lupus_borealis");
        itemSet.add("weapon_mare_tranquilitis");
        itemSet.add("weapon_morningstar");
        itemSet.add("armor_potat_boots");
        itemSet.add("armor_potat_chest");
        itemSet.add("armor_potat_helmet");
        itemSet.add("armor_potat_leggings");
        itemSet.add("weapon_rifts_eclipse");
        itemSet.add("weapon_risky_manuever");
        itemSet.add("weapon_umbral_valkyrie");
        return itemSet;
    }


}

