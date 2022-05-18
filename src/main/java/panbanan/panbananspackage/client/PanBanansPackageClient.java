package panbanan.panbananspackage.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.item.Item;
import panbanan.panbananspackage.entity.EntityRegister;
import panbanan.panbananspackage.entity.mobs.FieryGolemEntityRenderer;
import panbanan.panbananspackage.entity.mobs.GhoulEntityRenderer;
import panbanan.panbananspackage.entity.mobs.MimicEntityRenderer;
import panbanan.panbananspackage.entity.mobs.NiddhogEntityRenderer;
import panbanan.panbananspackage.entity.projectile.TestProjectileCopyPasteRenderer;
import panbanan.panbananspackage.items.ItemRegistry;
import panbanan.panbananspackage.items.ItemsIds;
import panbanan.panbananspackage.items.models.PistolRender;
import panbanan.panbananspackage.items.models.PotatoRenderer;
import panbanan.panbananspackage.items.models.ArmorsRenderer;
import software.bernie.example.client.renderer.entity.RocketRender;
import software.bernie.example.registry.EntityRegistry;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

import java.util.ArrayList;


@SuppressWarnings("deprecation")
@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class PanBanansPackageClient implements ClientModInitializer {

    @SuppressWarnings({ "unchecked" })
    @Override
    public void onInitializeClient(){
        EntityRendererRegistry.INSTANCE.register(EntityRegister.FIERY_GOLEM, (context) -> new FieryGolemEntityRenderer(context));
        EntityRendererRegistry.INSTANCE.register(EntityRegister.MIMIC, (context) -> new MimicEntityRenderer(context));
        EntityRendererRegistry.INSTANCE.register(EntityRegister.NIDDHOG, (context) -> new NiddhogEntityRenderer(context));
        EntityRendererRegistry.INSTANCE.register(EntityRegister.GHOUL, (context) -> new GhoulEntityRenderer(context));

        GeoItemRenderer.registerItemRenderer(ItemRegistry.PISTOL, new PistolRender());

        EntityRendererRegistry.INSTANCE.register(EntityRegister.TEST_PROJECTILE, (ctx) -> new TestProjectileCopyPasteRenderer(ctx));

        //GeoArmorRenderer.registerArmorRenderer(new PotatoRenderer(), ItemRegistry.POTAT_HELMET, ItemRegistry.POTAT_CHEST, ItemRegistry.POTAT_LEGGINGS, ItemRegistry.POTAT_BOOTS);
        ArrayList<Item> IDs = new ArrayList<>(ItemRegistry.ITEMS.values());
        for(Item id : IDs){
            String strID = id.toString();
            String bigID = id.toString().toUpperCase();
            if (strID.contains("armor")){
                GeoArmorRenderer.registerArmorRenderer(new ArmorsRenderer(), ItemRegistry.ITEMS.get(bigID));
                //System.out.println("In client init the geoArmorRender Register, registered: " + bigID);
            }
        }




    }
}
