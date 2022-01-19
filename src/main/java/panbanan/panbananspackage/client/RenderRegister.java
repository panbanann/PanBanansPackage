package panbanan.panbananspackage.client;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.entity.mobs.FieryGolemEntityRenderer;
import panbanan.panbananspackage.entity.mobs.GhoulEntityRenderer;
import panbanan.panbananspackage.entity.mobs.MimicEntityRenderer;
import panbanan.panbananspackage.entity.mobs.NiddhogEntityRenderer;
import panbanan.panbananspackage.items.armors.ArmorSet;
import panbanan.panbananspackage.items.models.PotatoRenderer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderRegister {

    @SuppressWarnings("deprecation")
    public static void onInitialize(){
        //Entities render registry



        //Armors render registry
        //GeoArmorRenderer.registerArmorRenderer(ArmorSet.class, new PotatoRenderer());
    }
}
