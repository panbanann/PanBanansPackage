package panbanan.panbananspackage.items.models;

import panbanan.panbananspackage.items.armors.ArmorSet;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class PotatoRenderer extends GeoArmorRenderer<ArmorSet> {

    public PotatoRenderer(){
        super(new PotatoModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";

    }

    /*public static void onInitializeClient()
    {
        GeoArmorRenderer.registerArmorRenderer(potatHelmet.class, new PotatoRenderer());
    }*/


}
