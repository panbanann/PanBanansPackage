package panbanan.panbananspackage.items.models;

import net.minecraft.util.Identifier;
import panbanan.panbananspackage.PanBanansPackage;
import panbanan.panbananspackage.items.armors.ArmorSet;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderer.geo.GeoArmorRenderer;

public class PotatoRenderer extends GeoArmorRenderer<ArmorSet> {

    public PotatoRenderer(){
        super(new AnimatedGeoModel<ArmorSet>() {
            @Override
            public Identifier getModelLocation(ArmorSet ArmorSet) {
                return new Identifier(PanBanansPackage.MOD_ID, "geo/potat.geo.json");
            }

            @Override
            public Identifier getTextureLocation(ArmorSet ArmorSet) {
                return new Identifier(PanBanansPackage.MOD_ID, "textures/armor/potat_texture.png");
            }

            @Override
            public Identifier getAnimationFileLocation(ArmorSet ArmorSet) {
                return null;
            }
        });

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
