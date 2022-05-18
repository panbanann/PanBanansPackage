package panbanan.panbananspackage.items.models;

import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import panbanan.panbananspackage.items.ItemRegistry;
import panbanan.panbananspackage.items.armors.ArmorSet;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.io.File;
import java.util.ArrayList;

public class ArmorsModel extends AnimatedGeoModel<ArmorSet>{

    //private Identifier id;
    private Identifier ANIMATION;
    private Identifier MODEL;
    private Identifier TEXTURE;
    private String itemId;

    //smolID needs to match only stuff in geo folder
    public ArmorsModel() {
        System.out.println("I am inside of armor model.");

        ArrayList<String> IDs = new ArrayList<>(ItemRegistry.ITEMS.keySet());
        for(String id : IDs){
            System.out.println("I am in loop inside ArmorModel with id: "+ id);
            String smolID = id.toLowerCase();
            if (smolID.contains("armor")){
                ANIMATION = null;
                MODEL = new Identifier(PanBanansPackage.MOD_ID, String.format("geo/%s.geo.json", smolID));
                TEXTURE = new Identifier(PanBanansPackage.MOD_ID, String.format("textures/armor/%s.png", smolID));
                System.out.println("In ArmorsModel for id: " + smolID + " the model is: " + MODEL + " and texture is: " + TEXTURE);}
        }
    }

    @Override
    public Identifier getAnimationFileLocation(ArmorSet ArmorSet) {
        return ANIMATION;
    }

    @Override
    public Identifier getModelLocation(ArmorSet ArmorSet) {
        return MODEL;
    }

    @Override
    public Identifier getTextureLocation(ArmorSet ArmorSet) {
        return TEXTURE;
    }

}
